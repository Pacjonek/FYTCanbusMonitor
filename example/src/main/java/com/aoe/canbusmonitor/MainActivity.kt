package com.aoe.canbusmonitor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.IModuleCallback
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import java.util.concurrent.ConcurrentHashMap

class MainActivity : AppCompatActivity() {

    private val lastPayloads = ConcurrentHashMap<String, String>()
    private lateinit var logView: TextView
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scroll_view)
        logView = findViewById(R.id.text_view)
        logView.text = "Started...\n"

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
            val shouldAutoScroll =
                scrollView.scrollY + scrollView.height >= contentHeight - SCROLL_BOTTOM_TOLERANCE_PX
            logView.append(message + "\n")
            trimLogIfNeeded()
            if (shouldAutoScroll) {
                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            }
        }
    }

    private fun formatPayloadValues(
        intArray: IntArray?,
        floatArray: FloatArray?,
        strArray: Array<String?>?
    ): String {
        val intBitwiseArray = intArray?.map { it and 255 }?.toIntArray()
        val combined = buildList<Any?> {
            intArray?.forEach { add(it) }
            if (intBitwiseArray != null && !intBitwiseArray.contentEquals(intArray)) {
                add(" //b")
                intBitwiseArray.forEach { add(it) }
            }
            floatArray?.forEach { add(it) }
            strArray?.forEach { add(it) }
        }
        return combined.joinToString(", ", "[", "]")
    }

    private fun logIfChanged(tag: String, updatedCode: Int, values: String) {
        if (lastPayloads.put("$tag:$updatedCode", values) != values) {
            log("$tag:$updatedCode: $values")
        }
    }

    private fun trimLogIfNeeded() {
        val text = logView.text
        var newlineCount = 0
        var index = text.length - 1
        while (index >= 0) {
            if (text[index] == '\n') {
                newlineCount++
                if (newlineCount > MAX_LOG_LINES) {
                    break
                }
            }
            index--
        }
        if (index > 0) {
            logView.text = text.subSequence(index + 1, text.length)
        }
    }

    private companion object {
        const val MAX_LOG_LINES = 500
        const val SCROLL_BOTTOM_TOLERANCE_PX = 48
    }
}
