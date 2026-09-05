package com.aoe.fytcanbusmonitor

/**
 * A single update pushed by a remote FYT module.
 * Each array is nullable because the service sends any subset of the three.
 */
class ModuleUpdate(
    val updateCode: Int,
    val ints: IntArray?,
    val floats: FloatArray?,
    val strings: Array<String?>?
)
