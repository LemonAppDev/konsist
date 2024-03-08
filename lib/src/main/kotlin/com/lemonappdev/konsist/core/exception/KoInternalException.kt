package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
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
        koFileDeclaration: KoFileDeclaration?,
    ) : this(getMessage(message, koFileDeclaration), cause)
}

private fun getMessage(
    message: String,
    koFileDeclaration: KoFileDeclaration?,
) = getMessage(message, koFileDeclaration?.text, "file")

private fun getMessage(
    message: String,
    koBaseProvider: KoBaseProvider?,
) = getMessage(message, (koBaseProvider as? KoTextProvider?)?.text, "declaration")

private fun getMessage(
    message: String,
    suffix: String?,
    type: String,
) = if (suffix == null) {
    message
} else {
    "$message, $type:\n$suffix"
}
