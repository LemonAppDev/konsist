package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

class KoInternalException(
    message: String? = null,
    cause: Throwable? = null,
    koBaseDeclaration: KoBaseDeclaration? = null,
) : KoException("${message?.prepare(koBaseDeclaration)}", cause)

private fun String.prepare(koBaseDeclaration: KoBaseDeclaration?): String {
    return if (koBaseDeclaration == null) {
        this
    } else {
        "$this, declaration:\n${koBaseDeclaration.text}"
    }
}
