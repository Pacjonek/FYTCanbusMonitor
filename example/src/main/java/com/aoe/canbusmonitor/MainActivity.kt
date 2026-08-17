package com.aoe.canbusmonitor

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_OBD
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_AMP
import com.aoe.fytcanbusmonitor.MsToolkitConnection

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ModuleCallback.init(this)
        connectMain()
        connectCanbus()
        connectObd()
        connectAmp()
        MsToolkitConnection.instance.connect(this)

    }

    private fun connectMain() {
        val callback = ModuleCallback("Main", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_MAIN)
        for (i in 0..119) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectCanbus() {
        val callback = ModuleCallback("Canbus", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_CANBUS)
        for (i in 0..50) {
            connection.addCallback(callback, i)
        }
        for (i in 1000..1036) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectAmp() {
        val callback = ModuleCallback("Amp", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_AMP)
        for (i in 0..49) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectObd() {
        val callback = ModuleCallback("CanUp", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_OBD)
        connection.addCallback(callback, 100)
        MsToolkitConnection.instance.addObserver(connection)
    }
}