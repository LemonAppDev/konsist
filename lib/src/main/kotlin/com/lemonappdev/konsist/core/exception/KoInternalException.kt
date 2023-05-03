package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

class KoInternalException(
    message: String? = null,
    cause: Throwable? = null,
    koBaseDeclarationImpl: KoBaseDeclaration? = null,
) : KoException("$message, declaration:\n${koBaseDeclarationImpl?.text}", cause)
