package com.github.aakumykov.cloud_writer.okhttp_progress;

public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
