package com.aoe.canbusmonitor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.os.Environment
import android.os.RemoteException
// import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.aoe.fytcanbusmonitor.IModuleCallback
// import java.io.OutputStream
// import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.ConcurrentHashMap

class ModuleCallback(private val name: String, private val view: TextView?) : IModuleCallback.Stub() {
    
    @Throws(RemoteException::class)
    override fun update(
        updatedCode: Int,
        intArray: IntArray?,
        floatArray: FloatArray?,
        strArray: Array<String?>?
    ) {
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
        val values = combined.joinToString(separator = ", ", prefix = "[", postfix = "]")
        val messageKey = "$name:$updatedCode"

        val previousValues = lastPayloads.put(messageKey, values)
        if (previousValues == values) {
            return
        }

        logMessage("$name:$updatedCode: $values")
    }

    init {
        if (false) {
            Thread {
                while (true) {
                    logMessage("$name: ${System.currentTimeMillis() / 1000}")
                    Thread.sleep(5000L)
                }
            }.start()
        }
    }

    companion object {
        private lateinit var activity: MainActivity
        private var view: TextView? = null
        private val lastPayloads = ConcurrentHashMap<String, String>()

        @RequiresApi(Build.VERSION_CODES.O)
        fun init(mainActivity: MainActivity) {
            activity = mainActivity
            view = activity.findViewById(R.id.text_view)
            view!!.text = "Started..."
        }

        @SuppressLint("SetTextI18n")
        @Synchronized
        private fun logMessage(message: String) {
            Log.i("[FYT Module]", message)
        }
    }
}
