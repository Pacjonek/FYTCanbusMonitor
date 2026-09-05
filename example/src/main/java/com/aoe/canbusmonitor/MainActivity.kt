package com.aoe.canbusmonitor

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.MsToolkitConnection

class MainActivity : AppCompatActivity() {
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ModuleCallback.init(this)
        connectMain()
        connectBt()
        connectCanbus()
        MsToolkitConnection.instance.connect(this)

    }

    private fun connectMain() {
        val callback = ModuleCallback("MAIN", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_MAIN, DataProxy.mainProxy)
        for (i in 0..76) {
            connection.addCallback(callback, i)
        }
        for (i in 78..200) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectCanbus() {
        val callback = ModuleCallback("CANBUS", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_CANBUS, DataProxy.canbusProxy)
        
        for (i in 0..200) {
            connection.addCallback(callback, i)
        }
        for (i in 500..600) {
            connection.addCallback(callback, i)
        }
        for (i in 1000..1200) {
            connection.addCallback(callback, i)
        }
        
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectBt() {
        val callback = ModuleCallback("BT", findViewById(R.id.text_view))
        val connection = IPCConnection(MODULE_CODE_BT, DataProxy.btProxy)
        for (i in 0..100) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }
}
