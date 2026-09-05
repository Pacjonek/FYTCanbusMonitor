package com.aoe.canbusmonitor

import android.os.RemoteException
import com.aoe.fytcanbusmonitor.ConnectionObserver
import com.aoe.fytcanbusmonitor.IModuleCallback
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
    private val callback: IModuleCallback,
    updateCodes: Iterable<Int>
) : ConnectionObserver {

    private val updateCodes = updateCodes.toList()
    private var callbacksRegistered = false

    init {
        MsToolkitConnection.instance.addObserver(this)
    }

    override fun onConnected(toolkit: IRemoteToolkit) {
        if (callbacksRegistered) {
            onDisconnected()
        }
        try {
            remoteProxy.remoteModule = toolkit.getRemoteModule(moduleId)
        } catch (e: RemoteException) {
            e.printStackTrace()
            return
        }
        updateCodes.forEach { remoteProxy.register(callback, it, 1) }
        callbacksRegistered = true
    }

    override fun onDisconnected() {
        if (callbacksRegistered) {
            updateCodes.forEach { remoteProxy.unregister(callback, it) }
            callbacksRegistered = false
        }
        remoteProxy.remoteModule = null
    }

    fun close() {
        onDisconnected()
        MsToolkitConnection.instance.removeObserver(this)
    }
}
