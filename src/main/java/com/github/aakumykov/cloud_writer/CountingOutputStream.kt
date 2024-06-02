package com.github.aakumykov.cloud_writer

import java.io.FileOutputStream
import java.io.OutputStream

class CountingOutputStream(
    private val fileOutputStream: FileOutputStream,
    private val callback: Callback
) : OutputStream() {

    private var _count: Long = 0
    val count: Long get() = _count

    override fun write(b: Int) {
        fileOutputStream.write(b)
        _count += 1
        callback.onCountChanged(count)
    }

    interface Callback {
        fun onCountChanged(count: Long)
    }
}