package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

inline fun <reified T> KoComplexDeclaration.representsTypeOf() = T::class.qualifiedName == fullyQualifiedName
