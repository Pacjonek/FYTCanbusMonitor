package com.aoe.canbusmonitor

import com.aoe.fytcanbusmonitor.RemoteModuleProxy

object DataProxy {
    val mainProxy = RemoteModuleProxy()
    val btProxy = RemoteModuleProxy()
    val canbusProxy = RemoteModuleProxy()
    val canUpProxy = RemoteModuleProxy()
}