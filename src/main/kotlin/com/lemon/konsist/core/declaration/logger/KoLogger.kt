package com.lemon.konsist.core.declaration.logger

import com.lemon.konsist.core.config.BuildConfig
import com.lemon.konsist.exception.KonsistUnsupportedOperationException

object KoLogger {
    fun logError(message: String) {
        if (BuildConfig.DEBUG) {
            throw KonsistUnsupportedOperationException(message)
        } else {
            println(message)
        }
    }
}
