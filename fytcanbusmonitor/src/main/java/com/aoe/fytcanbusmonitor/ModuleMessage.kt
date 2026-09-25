package com.aoe.fytcanbusmonitor

class ModuleMessage(
    var ints: IntArray? = null,
    var flts: FloatArray? = null,
    var strs: Array<String?>? = null,
    val code: Int = -1,
    var moduleId: Int = -1
)