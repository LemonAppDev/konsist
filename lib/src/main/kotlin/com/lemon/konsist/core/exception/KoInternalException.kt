package com.lemon.konsist.core.exception

import com.lemon.konsist.core.declaration.KoBaseDeclaration

class KoInternalException(message: String? = null, cause: Throwable? = null, koBaseDeclaration: KoBaseDeclaration? = null) :
    KoException("$message, declaration:\n${koBaseDeclaration?.text}", cause)
