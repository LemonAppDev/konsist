package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoProvider

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

    constructor(
        message: String,
        cause: Throwable? = null,
        koProvider: KoProvider?,
    ) : this(getMessage(message, koProvider), cause)
}

private fun getMessage(message: String, koBaseDeclaration: KoBaseDeclaration?) = getMessage(message, koBaseDeclaration?.text, "declaration")

private fun getMessage(message: String, koFile: KoFile?) = getMessage(message, koFile?.text, "file")

private fun getMessage(message: String, koProvider: KoProvider?) = getMessage(message, koProvider?.errorText, "provider")

private fun getMessage(message: String, suffix: String?, type: String) = if (suffix == null) {
    message
} else {
    "$message, $type:\n$suffix"
}
