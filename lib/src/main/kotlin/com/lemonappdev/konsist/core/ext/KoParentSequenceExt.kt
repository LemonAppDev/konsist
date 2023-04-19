package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParent

fun Sequence<KoParent>.withDelegate(name: String? = null) = filter { it.hasDelegate(name) }

fun Sequence<KoParent>.withoutDelegate(name: String? = null) = filterNot { it.hasDelegate(name) }
