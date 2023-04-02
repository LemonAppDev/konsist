package com.lemon.konsist.core.declaration.logger

import com.lemon.konsist.core.config.BuildConfig
import com.lemon.konsist.exception.KoUnsupportedOperationException

object KoLogger {
    fun logError(message: String) {
        if (BuildConfig.DEBUG) {
            throw KoUnsupportedOperationException(message)
        } else {
            println(message)
        }
    }
}
