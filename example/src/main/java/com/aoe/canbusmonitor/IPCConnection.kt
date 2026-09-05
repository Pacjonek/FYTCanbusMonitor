package com.aoe.canbusmonitor

import android.os.RemoteException
import com.aoe.fytcanbusmonitor.ConnectionObserver
import com.aoe.fytcanbusmonitor.IRemoteToolkit
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import com.aoe.fytcanbusmonitor.RemoteModuleProxy

/**
 * Connects [remoteProxy] to the remote module [moduleId], registering
 * [callback] for every update code in [updateCodes].
 *
 * Self-registers with [MsToolkitConnection] and stays registered until
 * [close] is called. Reconnection is handled by MsToolkitConnection.
 */
class IPCConnection(
    private val moduleId: Int,
    private val remoteProxy: RemoteModuleProxy,
    private val callback: ModuleCallback,
    updateCodes: Iterable<Int>
) : ConnectionObserver {

    private val updateCodes = updateCodes.toList()

    init {
        MsToolkitConnection.instance.addObserver(this)
    }

    override fun onConnected(toolkit: IRemoteToolkit) {
        try {
            remoteProxy.remoteModule = toolkit.getRemoteModule(moduleId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        updateCodes.forEach { remoteProxy.register(callback, it, 1) }
    }

    override fun onDisconnected() {
        updateCodes.forEach { remoteProxy.unregister(callback, it) }
        remoteProxy.remoteModule = null
    }

    fun close() {
        onDisconnected()
        MsToolkitConnection.instance.removeObserver(this)
    }
}
