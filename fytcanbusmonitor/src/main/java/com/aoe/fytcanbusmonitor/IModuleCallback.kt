package com.aoe.fytcanbusmonitor

import android.os.*

interface IModuleCallback : IInterface {
    @Throws(RemoteException::class)
    fun update(updatedCode: Int, intArray: IntArray?, floatArray: FloatArray?, strArray: Array<String?>?)

    abstract class Stub : Binder(), IModuleCallback {
        // android.os.IInterface
        override fun asBinder(): IBinder {
            return this
        }

        @Throws(RemoteException::class)  // android.os.Binder
        public override fun onTransact(
            code: Int,
            data: Parcel,
            reply: Parcel?,
            flags: Int
        ): Boolean {
            return when (code) {
                TRANSACTION_update -> {
                    data.enforceInterface(DESCRIPTOR)
                    val updatedCode = data.readInt()
                    val ints = data.createIntArray()
                    val flts = data.createFloatArray()
                    val strs = data.createStringArray()
                    update(updatedCode, ints, flts, strs)
                    true
                }
                TRANSACTION_getDescriptor -> {
                    reply!!.writeString(DESCRIPTOR)
                    true
                }
                else -> super.onTransact(code, data, reply, flags)
            }
        }

        private class Proxy internal constructor(private val mRemote: IBinder) : IModuleCallback {
            // android.os.IInterface
            override fun asBinder(): IBinder {
                return mRemote
            }

            @Throws(RemoteException::class)  // com.syu.ipc.IModuleCallback
            override fun update(
                updatedCode: Int,
                intArray: IntArray?,
                floatArray: FloatArray?,
                strArray: Array<String?>?
            ) {
                val data = Parcel.obtain()
                try {
                    data.writeInterfaceToken(DESCRIPTOR)
                    data.writeInt(updatedCode)
                    data.writeIntArray(intArray)
                    data.writeFloatArray(floatArray)
                    data.writeStringArray(strArray)
                    mRemote.transact(TRANSACTION_update, data, null, FLAG_ONEWAY)
                } finally {
                    data.recycle()
                }
            }
        }

        companion object {
            private const val DESCRIPTOR = "com.syu.ipc.IModuleCallback" //  "com.aoe.canbusmonitor.IModuleCallback"
            const val TRANSACTION_update = 1
            const val TRANSACTION_getDescriptor = 1598968902;

            fun asInterface(obj: IBinder?): IModuleCallback? {
                if (obj == null) {
                    return null
                }
                val iin = obj.queryLocalInterface(DESCRIPTOR)
                return if (iin != null && iin is IModuleCallback) {
                    iin
                } else Proxy(obj)
            }
        }

        init {
            attachInterface(this, DESCRIPTOR)
        }
    }
}
