package com.aoe.fytcanbusmonitor

import android.os.Handler
import android.os.Looper
import android.os.RemoteException

class ModuleConnection(
    private val moduleId: Int,
    updateCodes: Iterable<Int>,
    private val onUpdate: (ModuleUpdate) -> Unit
) : ConnectionObserver {

    val proxy = RemoteModuleProxy()

    private val updateCodes = updateCodes.toList()
    private val mainHandler = Handler(Looper.getMainLooper())
    private var callbacksRegistered = false
    private val callback = object : IModuleCallback.Stub() {
        override fun update(
            updatedCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            mainHandler.post {
                onUpdate(ModuleUpdate(updatedCode, intArray, floatArray, strArray))
            }
        }
    }

    init {
        MsToolkitConnection.instance.addObserver(this)
        FytLibrary.context?.let { MsToolkitConnection.instance.connect(it) }
        MsToolkitConnection.instance.remoteToolkit?.let { toolkit ->
            mainHandler.post {
                if (!callbacksRegistered) {
                    onConnected(toolkit)
                }
            }
        }
    }

    override fun onConnected(toolkit: IRemoteToolkit) {
        if (callbacksRegistered) {
            onDisconnected()
        }
        try {
            proxy.remoteModule = toolkit.getRemoteModule(moduleId)
        } catch (e: RemoteException) {
            e.printStackTrace()
            return
        }
        updateCodes.forEach { proxy.register(callback, it, 1) }
        callbacksRegistered = true
    }

    override fun onDisconnected() {
        if (callbacksRegistered) {
            updateCodes.forEach { proxy.unregister(callback, it) }
            callbacksRegistered = false
        }
        proxy.remoteModule = null
    }

    fun close() {
        onDisconnected()
        MsToolkitConnection.instance.removeObserver(this)
    }
}
