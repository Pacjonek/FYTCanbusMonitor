package com.aoe.canbusmonitor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.IModuleCallback
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import java.util.ArrayDeque
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val lastPayloads = ConcurrentHashMap<String, String>()
    private val logLines = ArrayDeque<String>()
    private val pendingLogMessages = ArrayDeque<String>()
    private val logQueueLock = Any()
    private val payloadLock = Any()
    private val connections = mutableListOf<IPCConnection>()
    private lateinit var logView: TextView
    private lateinit var scrollView: ScrollView
    private var isLogDrainPosted = false
    private val scrollBottomTolerancePx by lazy {
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            SCROLL_BOTTOM_TOLERANCE_DP.toFloat(),
            resources.displayMetrics
        ).roundToInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scroll_view)
        logView = findViewById(R.id.text_view)
        logLines += "Started..."
        renderLog()
    }

    override fun onStart() {
        super.onStart()
        connections += IPCConnection(
            MODULE_CODE_MAIN,
            DataProxy.mainProxy,
            loggingCallback("MAIN"),
            (0..76) + (78..200)
        )
        connections += IPCConnection(MODULE_CODE_BT, DataProxy.btProxy, loggingCallback("BT"), 0..100)
        connections += IPCConnection(
            MODULE_CODE_CANBUS,
            DataProxy.canbusProxy,
            loggingCallback("CANBUS"),
            (0..200) + (500..600) + (1000..1200)
        )

        MsToolkitConnection.instance.connect(this)
    }

    override fun onStop() {
        connections.forEach { it.close() }
        connections.clear()
        super.onStop()
    }

    private fun loggingCallback(tag: String) = object : IModuleCallback.Stub() {
        override fun update(
            updatedCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            val values = formatPayloadValues(intArray, floatArray, strArray)
            logIfChanged(tag, updatedCode, values)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun log(message: String) {
        val shouldPostDrain = synchronized(logQueueLock) {
            pendingLogMessages += message
            if (isLogDrainPosted) {
                false
            } else {
                isLogDrainPosted = true
                true
            }
        }
        if (shouldPostDrain) {
            logView.post { drainLogQueue() }
        }
    }

    private fun formatPayloadValues(
        intArray: IntArray?,
        floatArray: FloatArray?,
        strArray: Array<String?>?
    ): String {
        val intBitwiseArray = if (intArray?.any { it != (it and 255) } == true) {
            intArray.map { it and 255 }.toIntArray()
        } else {
            null
        }
        val combined = buildList<Any?> {
            intArray?.forEach { add(it) }
            if (intBitwiseArray != null) {
                add(" //b")
                intBitwiseArray.forEach { add(it) }
            }
            floatArray?.forEach { add(it) }
            strArray?.forEach { add(it) }
        }
        return combined.joinToString(", ", "[", "]")
    }

    private fun logIfChanged(tag: String, updatedCode: Int, values: String) {
        val messageKey = "$tag:$updatedCode"
        val shouldLog = synchronized(payloadLock) {
            val previousValues = lastPayloads.put(messageKey, values)
            previousValues != values
        }
        if (shouldLog) {
            log("$tag:$updatedCode: $values")
        }
    }

    private fun drainLogQueue() {
        val wasNearBottomBeforeDrain = isNearBottom()
        while (true) {
            val message = synchronized(logQueueLock) {
                if (pendingLogMessages.isEmpty()) {
                    null
                } else {
                    pendingLogMessages.removeFirst()
                }
            } ?: break

            Log.i("[FYT Module]", message)
            appendLogLine(message)
        }
        val shouldPostDrainAgain = synchronized(logQueueLock) {
            if (pendingLogMessages.isEmpty()) {
                isLogDrainPosted = false
                false
            } else {
                true
            }
        }
        if (shouldPostDrainAgain) {
            logView.post { drainLogQueue() }
            return
        }
        if (wasNearBottomBeforeDrain) {
            scrollView.post { scrollToBottom() }
        }
    }

    private fun appendLogLine(message: String) {
        logLines += message
        if (logLines.size > MAX_LOG_LINES) {
            logLines.removeFirst()
            renderLog()
        } else {
            if (logView.text.isEmpty()) {
                logView.append(message)
            } else {
                logView.append("\n$message")
            }
        }
    }

    private fun renderLog() {
        logView.text = logLines.joinToString(separator = "\n")
    }

    private fun isNearBottom(): Boolean {
        val contentHeight = scrollView.getChildAt(0)?.height ?: logView.height
        val distanceFromBottom = maxOf(contentHeight - (scrollView.scrollY + scrollView.height), 0)
        val contentFitsViewport = contentHeight <= scrollView.height
        return contentFitsViewport || distanceFromBottom <= scrollBottomTolerancePx
    }

    private fun scrollToBottom() {
        scrollView.fullScroll(ScrollView.FOCUS_DOWN)
    }

    private companion object {
        const val MAX_LOG_LINES = 500
        const val SCROLL_BOTTOM_TOLERANCE_DP = 48
    }
}
