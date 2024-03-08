package com.lemonappdev.konsist.core.exception

@Deprecated("Will be removed in v0.16.0", ReplaceWith("KoAssertionFailedException()"))
class KoCheckFailedException(message: String? = null, cause: Throwable? = null) : KoException(message, cause)
