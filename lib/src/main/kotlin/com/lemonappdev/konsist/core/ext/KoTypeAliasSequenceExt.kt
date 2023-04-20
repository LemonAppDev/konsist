package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAlias

fun Sequence<KoTypeAlias>.withType(type: String) = filter { it.type == type }

fun Sequence<KoTypeAlias>.withoutType(type: String) = filterNot { it.type == type }

inline fun <reified T> Sequence<KoTypeAlias>.withTypeOf() = filter { T::class.simpleName == it.type }

inline fun <reified T> Sequence<KoTypeAlias>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type }

fun Sequence<KoTypeAlias>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoTypeAlias>.withoutActualModifier() = filterNot { it.hasActualModifier() }
