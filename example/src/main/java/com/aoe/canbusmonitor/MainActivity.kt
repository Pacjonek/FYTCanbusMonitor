package com.aoe.canbusmonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CUSTOMER
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.ModuleConnection
import java.util.ArrayDeque
import java.util.concurrent.ConcurrentHashMap

class MainActivity : AppCompatActivity() {

    private val lastPayloads = ConcurrentHashMap<String, String>()
    private val moduleConnections = ArrayList<ModuleConnection>()
    private val pendingLogMessages = ArrayDeque<String>()
    private val logAdapter = LogAdapter(MAX_LOG_LINES)
    private val logQueueLock = Any()
    private val payloadLock = Any()
    private lateinit var recyclerView: RecyclerView
    private var isLogDrainPosted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.log_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this).apply { stackFromEnd = true }
        recyclerView.adapter = logAdapter
        logAdapter.add("Started...")

        moduleConnections += ModuleConnection(MODULE_CODE_MAIN, (0..76) + (78..200)) { update ->
            logIfChanged("MAIN", update.updateCode, formatPayloadValues(update.ints, update.floats, update.strings))
        }
        moduleConnections += ModuleConnection(MODULE_CODE_BT, 0..100) { update ->
            logIfChanged("BT", update.updateCode, formatPayloadValues(update.ints, update.floats, update.strings))
        }
        moduleConnections += ModuleConnection(MODULE_CODE_CUSTOMER, 0..100) { update ->
            logIfChanged(
                "Customer",
                update.updateCode,
                formatPayloadValues(update.ints, update.floats, update.strings)
            )
        }
        moduleConnections += ModuleConnection(
            MODULE_CODE_CANBUS,
            (0..200) + (500..600) + (1000..1200)
        ) { update ->
            logIfChanged(
                "CANBUS",
                update.updateCode,
                formatPayloadValues(update.ints, update.floats, update.strings)
            )
        }
    }

    override fun onDestroy() {
        moduleConnections.forEach { it.close() }
        moduleConnections.clear()
        super.onDestroy()
    }

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
            recyclerView.post { drainLogQueue() }
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
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        while (true) {
            val message = synchronized(logQueueLock) {
                if (pendingLogMessages.isEmpty()) {
                    isLogDrainPosted = false
                    null
                } else {
                    pendingLogMessages.removeFirst()
                }
            } ?: break

            Log.i("[FYT Module]", message)
            val shouldAutoScroll = layoutManager.findLastVisibleItemPosition() >= logAdapter.itemCount - 2
            val lastIndex = logAdapter.add(message)
            if (shouldAutoScroll && lastIndex >= 0) {
                recyclerView.scrollToPosition(lastIndex)
            }
        }
    }

    private companion object {
        const val MAX_LOG_LINES = 500
    }
}
