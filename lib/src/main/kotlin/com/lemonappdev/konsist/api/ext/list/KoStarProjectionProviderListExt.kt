package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider

fun <T : KoStarProjectionProvider> List<T>.withStarProjection(): List<T> = filter { it.isStarProjection }

fun <T : KoStarProjectionProvider> List<T>.withoutStarProjection(): List<T> = filterNot { it.isStarProjection }
