package com.aoe.fytcanbusmonitor

import android.os.Handler
import android.os.Looper
import android.os.RemoteException

/**
 * Connects [proxy] to the remote FYT module [moduleId] and delivers updates
 * for every code in [updateCodes] to [onUpdate] on the main thread.
 *
 * Self-registers with [MsToolkitConnection]; re-registers all update codes
 * after every (re)connect. Stays active until [close] is called.
 */
class ModuleConnection(
    private val moduleId: Int,
    private val proxy: RemoteModuleProxy,
    updateCodes: Iterable<Int>,
    private val onUpdate: (ModuleUpdate) -> Unit
) : ConnectionObserver {

    private val updateCodes = updateCodes.toList()
    private val mainHandler = Handler(Looper.getMainLooper())

    /** Binder stub hidden from clients; marshals updates onto the main thread. */
    private val callback = object : IModuleCallback.Stub() {
        override fun update(
            updatedCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            val update = ModuleUpdate(updatedCode, intArray, floatArray, strArray)
            mainHandler.post { onUpdate(update) }
        }
    }

    init {
        MsToolkitConnection.instance.addObserver(this)
    }

    override fun onConnected(toolkit: IRemoteToolkit) {
        try {
            proxy.remoteModule = toolkit.getRemoteModule(moduleId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        updateCodes.forEach { proxy.register(callback, it, 1) }
    }

    override fun onDisconnected() {
        updateCodes.forEach { proxy.unregister(callback, it) }
        proxy.remoteModule = null
    }

    fun close() = MsToolkitConnection.instance.removeObserver(this)
}
