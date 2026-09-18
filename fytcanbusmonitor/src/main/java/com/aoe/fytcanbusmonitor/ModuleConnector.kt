package com.aoe.fytcanbusmonitor

import android.content.Context
import android.os.RemoteException

/**
 * Connects [moduleHandler] to the remote module [targetModule], registering
 * [updateListener] for every update code in [listenedUpdateCodes].
 *
 * Self-registers using [MsToolkitConnection] and keep that until
 * [close] is called. Reconnection is handled by [MsToolkitConnection].
 */
class ModuleConnector(
    private val targetModule: Module,
    listenedUpdateCodes: Iterable<Int>?,
    private val updateListener: IModuleCallback?,
    ) : IConnectionObserver {
    private val moduleHandler = ModuleProxy()
    private val listenedUpdateCodes = listenedUpdateCodes?.toList() ?: emptyList()
    private var listenersRegistered = false

    enum class Module(val code: Int) {
        MAIN(0),
        BT(2),
        SOUND(4),
        CANBUS(7);

        companion object {
            private val map = entries.associateBy(Module::code)

            fun fromCode(code: Int): Module? = map[code]
        }
    }

    init {
        MsToolkitConnection.instance.addObserver(this)
    }

    override fun onConnected(toolkit: IRemoteToolkit) {
        if (listenersRegistered) {
            onDisconnected()
        }
        try {
            val module = toolkit.getRemoteModule(targetModule.code)
                ?: throw RemoteException("Module $targetModule not found")
            moduleHandler.module = module
            moduleHandler.moduleId = targetModule.code
        } catch (e: RemoteException) {
            e.printStackTrace()
            return
        }
        if(updateListener != null){
            listenedUpdateCodes.forEach { moduleHandler.register(updateListener, it, 1) }
            listenersRegistered = true
        }

    }

    override fun onDisconnected() {
        if (updateListener != null && listenersRegistered) {
            listenedUpdateCodes.forEach { moduleHandler.unregister(updateListener, it) }
            listenersRegistered = false
        }
        moduleHandler.module = null
    }

    fun close() {
        onDisconnected()
        MsToolkitConnection.instance.removeObserver(this)
    }

    companion object{
        fun connect(context: Context){
            MsToolkitConnection.instance.connect(context)
        }

    }
}