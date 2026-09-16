package com.aoe.canbusmonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.IModuleCallback
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import java.util.concurrent.ConcurrentHashMap

class MainActivity : AppCompatActivity() {

    private val lastPayloads = ConcurrentHashMap<String, String>()
    private val payloadLock = Any()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        IPCConnection(
            MODULE_CODE_MAIN,
            DataProxy.mainProxy,
            loggingCallback(MODULE_CODE_MAIN, "MAIN"),
            (0..76) + (78..200)
        )
        IPCConnection(MODULE_CODE_BT, DataProxy.btProxy, loggingCallback(MODULE_CODE_BT, "BT"), 0..30)
        IPCConnection(
            MODULE_CODE_CANBUS,
            DataProxy.canbusProxy,
            loggingCallback(MODULE_CODE_CANBUS, "CANBUS"),
            (0..10) + (94..200) + (500..700) + (1000..1200)
        )

        MsToolkitConnection.instance.connect(this)
    }

    private fun loggingCallback(moduleCode: Int, moduleLabel: String) = object : IModuleCallback.Stub() {
        override fun update(
            updatedCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            val values = formatPayloadValues(intArray, floatArray, strArray)
            logIfChanged(moduleCode, moduleLabel, updatedCode, values)
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

    private fun logIfChanged(
        moduleCode: Int,
        moduleLabel: String,
        updatedCode: Int,
        message: String
    ) {
        val messageKey = "$moduleLabel:$updatedCode"
        val shouldLog = synchronized(payloadLock) {
            val previousValues = lastPayloads.put(messageKey, message)
            previousValues != message
        }
        if (shouldLog) {
            val codeLabel = UpdateCodeNameResolver.resolveOrFallback(moduleCode, updatedCode)
            Log.w("[FYT Module][$moduleLabel][$codeLabel]", message)
        }
    }
}
