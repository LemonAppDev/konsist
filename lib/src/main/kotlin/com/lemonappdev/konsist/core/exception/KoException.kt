package com.lemonappdev.konsist.core.exception

open class KoException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)
