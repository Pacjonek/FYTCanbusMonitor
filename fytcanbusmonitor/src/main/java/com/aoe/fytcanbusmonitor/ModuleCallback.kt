package com.aoe.fytcanbusmonitor

open class ModuleCallback : IModuleCallback.Stub() {
    var moduleId: Int = -1

    override fun update(message: ModuleMessage) {
        message.moduleId = moduleId
        super.update(message)
    }
}