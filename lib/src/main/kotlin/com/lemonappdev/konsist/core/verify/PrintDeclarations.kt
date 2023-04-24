package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration

fun <T : KoComplexDeclaration> Sequence<T>.printDeclarations(): Sequence<T> {
    forEach { println("[${it::class.simpleName}] ${it.name} ~${it.location}") }
    return this
}
