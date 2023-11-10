package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.exception.KoInternalException

internal fun KoBaseProvider.castToKoBaseDeclaration(): KoBaseDeclaration =
    (this as? KoBaseDeclaration) ?: throw KoInternalException("The cast to `KoBaseDeclaration` is invalid.")
