package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withName(name: String) = filter { it.name == name }

fun Sequence<KoNamedDeclaration>.withoutName(name: String) = filterNot { it.name == name }

fun Sequence<KoNamedDeclaration>.withNameWithPrefix(prefix: String) = filter { it.name.startsWith(prefix) }

fun Sequence<KoNamedDeclaration>.withoutNameWithPrefix(prefix: String) = filterNot { it.name.startsWith(prefix) }

fun Sequence<KoNamedDeclaration>.withNameWithSuffix(suffix: String) = filter { it.name.endsWith(suffix) }

fun Sequence<KoNamedDeclaration>.withoutNameWithSuffix(suffix: String) = filterNot { it.name.endsWith(suffix) }

fun Sequence<KoNamedDeclaration>.withNameContaining(text: String) = filter { it.name.contains(text) }

fun Sequence<KoNamedDeclaration>.withoutNameContaining(text: String) = filterNot { it.name.contains(text) }
