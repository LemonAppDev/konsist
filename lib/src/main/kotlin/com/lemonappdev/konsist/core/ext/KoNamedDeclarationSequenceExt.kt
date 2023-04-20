package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withName(name: String) = filter { it.name == name }

fun Sequence<KoNamedDeclaration>.withoutName(name: String) = filterNot { it.name == name }

fun Sequence<KoNamedDeclaration>.withNamePrefix(prefix: String) = filter { it.hasNameWithPrefix(prefix) }

fun Sequence<KoNamedDeclaration>.withoutNamePrefix(prefix: String) = filterNot { it.hasNameWithPrefix(prefix) }

fun Sequence<KoNamedDeclaration>.withNameSuffix(suffix: String) = filter { it.hasNameWithSuffix(suffix) }

fun Sequence<KoNamedDeclaration>.withoutNameSuffix(suffix: String) = filterNot { it.hasNameWithSuffix(suffix) }

fun Sequence<KoNamedDeclaration>.withNameContaining(text: String) = filter { it.hasNameContaining(text) }

fun Sequence<KoNamedDeclaration>.withoutNameContaining(text: String) = filterNot { it.hasNameContaining(text) }
