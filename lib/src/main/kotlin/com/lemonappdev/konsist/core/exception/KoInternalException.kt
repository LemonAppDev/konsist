package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

class KoInternalException(
    message: String,
    cause: Throwable? = null,
    koBaseProvider: KoBaseProvider? = null,
) : KoException(getMessage(message, koBaseProvider), cause) {

    constructor(
        message: String,
        cause: Throwable? = null,
        koFile: KoFile?,
    ) : this(getMessage(message, koFile), cause)
}
private fun getMessage(message: String, koFile: KoFile?) = getMessage(message, koFile?.text, "file")

private fun getMessage(message: String, koBaseProvider: KoBaseProvider?) =
    getMessage(message, (koBaseProvider as KoTextProvider?)?.text, "declaration")

private fun getMessage(message: String, suffix: String?, type: String) = if (suffix == null) {
    message
} else {
    "$message, $type:\n$suffix"
}
