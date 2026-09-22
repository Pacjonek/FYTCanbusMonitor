package com.aoe.fytcanbusmonitor

import android.os.RemoteException

class RemoteModuleProxy : IRemoteModule.Stub() {
    var remoteModule: IRemoteModule? = null
    var moduleType = -1

    override fun cmd(cmdCode: Int, ints: IntArray?, flts: FloatArray?, strs: Array<String?>?) {
        val module = remoteModule
        if (module != null) {
            try {
                module.cmd(cmdCode, ints, flts, strs)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    fun cmd(cmdCode: Int) {
        val module = remoteModule
        if (module != null) {
            try {
                module.cmd(cmdCode, null, null, null)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    fun cmd(cmdCode: Int, value: Int) {
        val module = remoteModule
        if (module != null) {
            try {
                module.cmd(cmdCode, intArrayOf(value), null, null)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    fun cmd(cmdCode: Int, value1: Int, value2: Int) {
        val module = remoteModule
        if (module != null) {
            try {
                module.cmd(cmdCode, intArrayOf(value1, value2), null, null)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    override operator fun get(
        getCode: Int,
        ints: IntArray?,
        flts: FloatArray?,
        strs: Array<String?>?
    ): ModuleObject? {
        val module = remoteModule
        if (module != null) {
            try {
                return module[getCode, ints, flts, strs]
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        return null
    }

    operator fun get(getCode: Int, value: Int): ModuleObject? {
        val module = remoteModule
        return if (module != null) {
            try {
                module[getCode, intArrayOf(value), null, null]
            } catch (e: RemoteException) {
                e.printStackTrace()
                null
            }
        } else null
    }

    fun getI(getCode: Int, fallbackValue: Int): Int {
        val module = remoteModule
        return if (module != null) {
            try {
                val obj = module[getCode, null, null, null]
                if (obj?.ints != null && obj.ints!!.isNotEmpty()) {
                    obj.ints!![0]
                } else fallbackValue
            } catch (e: RemoteException) {
                e.printStackTrace()
                fallbackValue
            }
        } else fallbackValue
    }

    fun getS(getCode: Int, value: Int): String? {
        val module = remoteModule
        return if (module != null) {
            try {
                val obj = module[getCode, intArrayOf(value), null, null]
                if (obj?.strs != null && obj.strs!!.isNotEmpty()) {
                    obj.strs!![0]
                } else null
            } catch (e: RemoteException) {
                e.printStackTrace()
                null
            }
        } else null
    }

    fun getS(getCode: Int, value1: Int, value2: Int): String? {
        val module = remoteModule
        return if (module != null) {
            try {
                val obj = module[getCode, intArrayOf(value1, value2), null, null]
                if (obj?.strs != null && obj.strs!!.isNotEmpty()) {
                    obj.strs!![0]
                } else null
            } catch (e: RemoteException) {
                e.printStackTrace()
                null
            }
        } else null
    }

    override fun register(updateListener: IModuleCallback?, updateCode: Int, syncFlag: Int) {
        val module = remoteModule
        if (module != null) {
            try {
                module.register(updateListener, updateCode, syncFlag)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    override fun unregister(updateListener: IModuleCallback?, updateCode: Int) {
        val module = remoteModule
        if (module != null) {
            try {
                module.unregister(updateListener, updateCode)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }
}
