package com.aoe.fytcanbusmonitor

interface IConnectionObserver {
    fun onConnected(toolkit: IRemoteToolkit)
    fun onDisconnected()
}

@Deprecated(
    message = "Name `ConnectionObserver` is misleading",
    replaceWith = ReplaceWith("IConnectionObserver")
)
typealias ConnectionObserver = IConnectionObserver
