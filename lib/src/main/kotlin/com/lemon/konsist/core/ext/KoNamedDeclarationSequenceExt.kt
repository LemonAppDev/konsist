package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withName(name: String) = filter { it.name == name }

fun Sequence<KoNamedDeclaration>.withoutName(name: String) = filterNot { it.name == name }

fun Sequence<KoNamedDeclaration>.withNamePrefix(prefix: String) = filter { it.name.startsWith(prefix) }

fun Sequence<KoNamedDeclaration>.withoutNamePrefix(prefix: String) = filterNot { it.name.startsWith(prefix) }

fun Sequence<KoNamedDeclaration>.withNameSuffix(suffix: String) = filter { it.name.endsWith(suffix) }

fun Sequence<KoNamedDeclaration>.withoutNameSuffix(suffix: String) = filterNot { it.name.endsWith(suffix) }

fun Sequence<KoNamedDeclaration>.withNameContaining(text: String) = filter { it.name.contains(text) }

fun Sequence<KoNamedDeclaration>.withoutNameContaining(text: String) = filterNot { it.name.contains(text) }
