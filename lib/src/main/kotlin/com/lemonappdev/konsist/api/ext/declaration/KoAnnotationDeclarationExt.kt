package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration

inline fun <reified T> KoAnnotationDeclaration.representsTypeOf() = T::class.qualifiedName == fullyQualifiedName
