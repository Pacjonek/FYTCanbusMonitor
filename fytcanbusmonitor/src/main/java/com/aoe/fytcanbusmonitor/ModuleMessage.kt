package com.aoe.fytcanbusmonitor

class ModuleMessage(
    val code: Int = -1,
    var ints: IntArray? = null,
    var flts: FloatArray? = null,
    var strs: Array<String?>? = null,
    var moduleId: Int = -1
)