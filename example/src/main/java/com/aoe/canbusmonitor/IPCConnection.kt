package com.aoe.canbusmonitor

import android.os.RemoteException
import com.aoe.fytcanbusmonitor.ConnectionObserver
import com.aoe.fytcanbusmonitor.IRemoteToolkit
import com.aoe.fytcanbusmonitor.RemoteModuleProxy

class IPCConnection(
    private val moduleId: Int,
    private val remoteProxy: RemoteModuleProxy
) : ConnectionObserver {
    private val callbacks = mutableListOf<Pair<ModuleCallback, Int>>()

    override fun onConnected(toolkit: IRemoteToolkit?) {
        try {
            remoteProxy.remoteModule = toolkit?.getRemoteModule(moduleId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (remoteProxy.remoteModule == null) return
        callbacks.forEach { (callback, id) -> remoteProxy.register(callback, id, 1) }
    }

     fun addCallback(callback: ModuleCallback, id: Int) {
         callbacks.add(Pair(callback, id))
     }
    override fun onDisconnected() {
        callbacks.forEach { (callback, id) -> remoteProxy.unregister(callback, id) }
        remoteProxy.remoteModule = null
    }
}
