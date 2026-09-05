package com.aoe.canbusmonitor

import com.aoe.fytcanbusmonitor.RemoteModuleProxy

object DataProxy {
    val btProxy = RemoteModuleProxy()
    val canbusProxy = RemoteModuleProxy()
    val canUpProxy = RemoteModuleProxy()
    val mainProxy = RemoteModuleProxy()
}