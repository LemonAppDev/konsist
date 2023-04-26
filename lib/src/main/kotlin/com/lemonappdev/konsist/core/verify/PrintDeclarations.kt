package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.core.declaration.KoDeclaration

fun <T : KoDeclaration> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.getDeclarationText(false)) }
    return this
}
