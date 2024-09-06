package com.lemonappdev.konsist.core.exception

class KoAssertionFailedException(
    message: String? = null,
    cause: Throwable? = null,
) : KoException(message, cause)
