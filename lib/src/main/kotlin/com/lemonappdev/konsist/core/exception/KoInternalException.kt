package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

class KoInternalException(
    message: String,
    cause: Throwable? = null,
    koBaseDeclaration: KoBaseDeclaration? = null,
) : KoException(getMessage(message, koBaseDeclaration), cause) {

    constructor(
        message: String,
        cause: Throwable? = null,
        koFile: KoFile?,
    ) : this(getMessage(message, koFile), cause)
}

private fun getMessage(message: String, koBaseDeclaration: KoBaseDeclaration?): String {
    return if (koBaseDeclaration == null) {
        message
    } else {
        "$message, declaration:\n${koBaseDeclaration.text}"
    }
}

private fun getMessage(message: String, koFile: KoFile?): String {
    return if (koFile == null) {
        message
    } else {
        "$message, file:\n${koFile.text}"
    }
}
