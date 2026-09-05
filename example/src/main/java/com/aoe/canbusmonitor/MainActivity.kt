package com.aoe.canbusmonitor

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import com.aoe.fytcanbusmonitor.MsToolkitConnection

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ModuleCallback.init(this)

        val view = findViewById<TextView>(R.id.text_view)
        IPCConnection(MODULE_CODE_MAIN, DataProxy.mainProxy, ModuleCallback("MAIN", view), (0..76) + (78..200))
        IPCConnection(MODULE_CODE_BT, DataProxy.canUpProxy, ModuleCallback("BT", view), 0..100)
        IPCConnection(
            MODULE_CODE_CANBUS,
            DataProxy.canbusProxy,
            ModuleCallback("CANBUS", view),
            (0..200) + (500..600) + (1000..1200)
        )

        MsToolkitConnection.instance.connect(this)
    }
}
