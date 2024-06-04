package com.github.aakumykov.cloud_writer

import java.io.FileOutputStream
import java.io.OutputStream

class CountingOutputStream(
    private val fileOutputStream: FileOutputStream,
    private val callback: Callback
) : OutputStream() {

    private var _count: Long = 0
    val count: Long get() = _count

    override fun write(b: ByteArray?) {
        super.write(b)
        if (null != b)
            _count += b.size
        callback.onCountChanged(count)
    }

    override fun write(b: ByteArray?, off: Int, len: Int) {
        super.write(b, off, len)
        _count += len
        callback.onCountChanged(count)
    }

    override fun write(b: Int) {
        fileOutputStream.write(b)
        _count += 1
        callback.onCountChanged(count)
    }

    interface Callback {
        fun onCountChanged(count: Long)
    }

    companion object {
        val TAG: String = CountingOutputStream::class.java.simpleName
    }
}