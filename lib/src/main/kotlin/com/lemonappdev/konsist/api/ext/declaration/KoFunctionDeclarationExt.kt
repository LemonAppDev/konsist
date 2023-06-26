package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

inline fun <reified T> KoFunctionDeclaration.hasReceiverOf(): Boolean = T::class.simpleName == receiver?.name
