package com.aoe.fytcanbusmonitor

import android.os.Handler
import android.os.Looper
import android.os.RemoteException
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

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
    private var callbacksRegistered = false
    private val closed = AtomicBoolean(false)
    private val activeGeneration = AtomicInteger(0)
    private val generationCounter = AtomicInteger(0)

    /** Binder stub hidden from clients; marshals updates onto the main thread. */
    private val callback = object : IModuleCallback.Stub() {
        override fun update(
            updatedCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            if (closed.get()) {
                return
            }
            val generation = activeGeneration.get()
            if (generation == 0) {
                return
            }
            val update = ModuleUpdate(updatedCode, intArray, floatArray, strArray)
            mainHandler.post {
                if (!closed.get() && activeGeneration.get() == generation) {
                    onUpdate(update)
                }
            }
        }
    }

    init {
        MsToolkitConnection.instance.addObserver(this)
    }

    private fun clearConnection(remoteModule: IRemoteModule?, registeredCodes: Iterable<Int>) {
        activeGeneration.set(0)
        registeredCodes.forEach {
            try {
                remoteModule?.unregister(callback, it)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        callbacksRegistered = false
        proxy.remoteModule = null
    }

    @Synchronized
    override fun onConnected(toolkit: IRemoteToolkit) {
        if (closed.get()) {
            return
        }
        if (callbacksRegistered) {
            onDisconnected()
        }
        try {
            val remoteModule = toolkit.getRemoteModule(moduleId)
            if (remoteModule == null) {
                onDisconnected()
                return
            }
            proxy.remoteModule = remoteModule
            val generation = generationCounter.incrementAndGet()
            val registeredCodes = ArrayList<Int>(updateCodes.size)
            try {
                updateCodes.forEach {
                    remoteModule.register(callback, it, 1)
                    registeredCodes += it
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
                clearConnection(remoteModule, registeredCodes)
                return
            }
            activeGeneration.set(generation)
            callbacksRegistered = true
        } catch (e: RemoteException) {
            e.printStackTrace()
            clearConnection(proxy.remoteModule, emptyList())
            return
        }
    }

    @Synchronized
    override fun onDisconnected() {
        clearConnection(proxy.remoteModule, if (callbacksRegistered) updateCodes else emptyList())
    }

    @Synchronized
    fun close() {
        closed.set(true)
        onDisconnected()
        MsToolkitConnection.instance.removeObserver(this)
    }
}
