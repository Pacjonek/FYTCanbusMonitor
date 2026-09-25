package com.aoe.canbusmonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.RemoteModuleProxy
import com.aoe.fytcanbusmonitor.IModuleCallback
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import java.util.concurrent.ConcurrentHashMap

class MainActivity : AppCompatActivity() {

    private val lastPayloads = ConcurrentHashMap<String, String>()
    private val payloadLock = Any()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        IPCConnection(
            MODULE_CODE_MAIN,
            RemoteModuleProxy(),
            loggingCallback(MODULE_CODE_MAIN.toLong(), "MAIN"),
            (0..76) + (78..256)
        )
        IPCConnection(
            MODULE_CODE_BT,
            RemoteModuleProxy(),
            loggingCallback(MODULE_CODE_BT.toLong(), "BT"),
            (0..100)
        )
        IPCConnection(
            MODULE_CODE_CANBUS,
            RemoteModuleProxy(),
            loggingCallback(MODULE_CODE_CANBUS.toLong(), "CANBUS"),
            (0..300) + (500..700) + (1000..1200)
        )
        // IPCConnection(MODULE_CODE_OBD, RemoteModuleProxy(), loggingCallback(MODULE_CODE_OBD.toLong(), "OBD"), 1000..1200)
        // IPCConnection(MODULE_CODE_BT, DataProxy.btProxy, loggingCallback(MODULE_CODE_BT.toLong(), "BT"), 0..30)

        MsToolkitConnection.instance.connect(this)
    }

    private fun loggingCallback(moduleCode: Long, moduleLabel: String) = object : IModuleCallback.Stub() {
        override fun update(
            ints: IntArray?,
            flts: FloatArray?,
            strs: Array<String?>?,
            updatedCode: Int
            ) {
            val values = formatPayloadValues(ints, flts, strs)
            logIfChanged(moduleCode, moduleLabel, updatedCode, values)
        }
    }

    private fun formatPayloadValues(
        intArray: IntArray?,
        floatArray: FloatArray?,
        strArray: Array<String?>?
    ): String {
        val intBitwiseArray = if (intArray?.any { it > 0 && it != (it and 255) } == true) {
            intArray.map { it and 255 }
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
        moduleCode: Long,
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
            Log.w("FYT/$moduleLabel", "[$codeLabel] $message")
        }
    }
}
