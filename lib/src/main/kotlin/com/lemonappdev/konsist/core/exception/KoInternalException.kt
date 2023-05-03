package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl

internal class KoInternalException(
    message: String? = null,
    cause: Throwable? = null,
    koBaseDeclarationImpl: KoBaseDeclarationImpl? = null,
) :
    KoException("$message, declaration:\n${koBaseDeclarationImpl?.text}", cause)
