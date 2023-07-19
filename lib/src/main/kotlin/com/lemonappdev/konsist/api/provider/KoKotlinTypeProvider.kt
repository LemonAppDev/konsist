package com.lemonappdev.konsist.api.provider

interface KoKotlinTypeProvider : KoProvider {
    /**
     * Whatever type is a build in Kotlin type. It can be a basic Kotlin type [Basic types](https://kotlinlang.org/docs/basic-types.html)
     * or collection type [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinType: Boolean
}
