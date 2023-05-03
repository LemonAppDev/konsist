package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

inline fun <reified T> KoParameterDeclaration.hasTypeOf() = T::class.simpleName == type.name
