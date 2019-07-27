package com.aranirahan.moviecatalogue.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

internal class DiskIOThreadExecutor : Executor {

    private val mDiskIO: Executor

    init {
        mDiskIO = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }
}
