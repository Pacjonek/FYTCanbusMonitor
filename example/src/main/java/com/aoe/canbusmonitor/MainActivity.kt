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
    private val payloadLock = Any()
    private lateinit var logView: TextView
    private lateinit var scrollView: ScrollView
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

        IPCConnection(MODULE_CODE_MAIN, DataProxy.mainProxy, loggingCallback("MAIN"), (0..76) + (78..200))
        IPCConnection(MODULE_CODE_BT, DataProxy.btProxy, loggingCallback("BT"), 0..100)
        IPCConnection(
            MODULE_CODE_CANBUS,
            DataProxy.canbusProxy,
            loggingCallback("CANBUS"),
            (0..200) + (500..600) + (1000..1200)
        )

        MsToolkitConnection.instance.connect(this)
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
        Log.i("[FYT Module]", message)
        logView.post {
            val contentHeight = scrollView.getChildAt(0)?.height ?: logView.height
            val distanceFromBottom = maxOf(contentHeight - (scrollView.scrollY + scrollView.height), 0)
            val contentFitsViewport = contentHeight <= scrollView.height
            val incomingLineHeight = maxOf(logView.lineHeight, 0)
            val projectedDistanceFromBottom = maxOf(distanceFromBottom - incomingLineHeight, 0)
            val wasNearBottom = contentFitsViewport || projectedDistanceFromBottom <= scrollBottomTolerancePx
            appendLogLine(message)
            if (wasNearBottom) {
                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            }
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

    private fun appendLogLine(message: String) {
        logLines += message
        if (logLines.size > MAX_LOG_LINES) {
            logLines.removeFirst()
            renderLog()
        } else {
            logView.append(message + "\n")
        }
    }

    private fun renderLog() {
        logView.text = logLines.joinToString(separator = "\n", postfix = "\n")
    }

    private companion object {
        const val MAX_LOG_LINES = 500
        const val SCROLL_BOTTOM_TOLERANCE_DP = 48
    }
}
