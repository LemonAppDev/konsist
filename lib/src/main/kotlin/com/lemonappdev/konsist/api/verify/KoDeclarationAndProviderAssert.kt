package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.verify.assert

fun <E : KoBaseProvider> List<E>.assert(function: (E) -> Boolean?) {
    assert(function, positiveCheck = true)
}

fun <E : KoBaseProvider> List<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, positiveCheck = false)
}

fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?) {
    this.toList().assert(function, true)
}

fun <E : KoBaseProvider> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    this.toList().assert(function, false)
}
