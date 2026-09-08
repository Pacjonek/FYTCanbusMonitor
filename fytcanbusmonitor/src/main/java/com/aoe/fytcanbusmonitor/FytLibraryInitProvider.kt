package com.aoe.fytcanbusmonitor

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/** Auto-initializes the library with the application context at process start. */
internal class FytLibraryInitProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        FytLibrary.context = context?.applicationContext
        return true
    }

    override fun query(
        uri: Uri,
        p: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, s: String?, strings: Array<out String>?): Int = 0

    override fun update(uri: Uri, values: ContentValues?, s: String?, strings: Array<out String>?): Int = 0
}
