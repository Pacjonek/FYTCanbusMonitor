package com.aoe.fytcanbusmonitor

data class ModuleUpdate(
    val updateCode: Int,
    val ints: IntArray?,
    val floats: FloatArray?,
    val strings: Array<String?>?
)
