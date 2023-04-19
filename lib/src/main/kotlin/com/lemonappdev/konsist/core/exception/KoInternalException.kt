package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

class KoInternalException(message: String? = null, cause: Throwable? = null, koBaseDeclaration: KoBaseDeclaration? = null) :
    KoException("$message, declaration:\n${koBaseDeclaration?.text}", cause)
