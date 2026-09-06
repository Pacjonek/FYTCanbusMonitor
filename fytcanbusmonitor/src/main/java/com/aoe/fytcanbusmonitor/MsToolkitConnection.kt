package com.aoe.fytcanbusmonitor

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import java.util.Random

/**
 * Singleton [ServiceConnection] that maintains a connection to the FYT
 * toolkit service (`com.syu.ms`) and notifies [ConnectionObserver]s.
 * All observer callbacks are delivered on the main thread.
 */
class MsToolkitConnection private constructor() : ServiceConnection {

    var remoteToolkit: IRemoteToolkit? = null
        private set

    private var context: Context? = null
    private var connecting = false
    private val handler = Handler(Looper.getMainLooper())
    private val observers = ArrayList<ConnectionObserver>()

    private val reconnectRunnable = object : Runnable {
        override fun run() {
            if (remoteToolkit != null) {
                connecting = false
                return
            }
            val intent = Intent(TOOLKIT_ACTION).setComponent(TOOLKIT_COMPONENT)
            context?.bindService(intent, this@MsToolkitConnection, Context.BIND_AUTO_CREATE)
            handler.postDelayed(this, nextReconnectDelay())
        }
    }

    @Synchronized
    fun connect(context: Context?) = connect(context, 0L)

    private fun connect(context: Context?, delayMillis: Long) {
        if (connecting || remoteToolkit != null || context == null) return
        this.context = context.applicationContext
        connecting = true
        handler.postDelayed(reconnectRunnable, delayMillis)
    }

    @Synchronized
    fun addObserver(observer: ConnectionObserver) {
        if (observer in observers) return
        observers += observer
        remoteToolkit?.let { toolkit -> handler.post { observer.onConnected(toolkit) } }
    }

    @Synchronized
    fun removeObserver(observer: ConnectionObserver) {
        observers -= observer
        if (remoteToolkit != null) handler.post { observer.onDisconnected() }
    }

    @Synchronized
    fun clearObservers() {
        if (remoteToolkit != null) {
            observers.forEach { observer -> handler.post { observer.onDisconnected() } }
        }
        observers.clear()
    }

    @Synchronized
    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        remoteToolkit = IRemoteToolkit.Stub.asInterface(service)
        observers.forEach { observer -> handler.post { observer.onConnected(remoteToolkit) } }
    }

    @Synchronized
    override fun onServiceDisconnected(name: ComponentName) {
        remoteToolkit = null
        observers.forEach { observer -> handler.post { observer.onDisconnected() } }
        connect(context, nextReconnectDelay())
    }

    private fun nextReconnectDelay(): Long = RECONNECT_BASE_MS + Random().nextInt(RECONNECT_JITTER_MS).toLong()

    companion object {
        val instance = MsToolkitConnection()

        private const val TOOLKIT_ACTION = "com.syu.ms.toolkit"
        private val TOOLKIT_COMPONENT = ComponentName("com.syu.ms", "app.ToolkitService")
        private const val RECONNECT_BASE_MS = 1000
        private const val RECONNECT_JITTER_MS = 3000
    }
}
