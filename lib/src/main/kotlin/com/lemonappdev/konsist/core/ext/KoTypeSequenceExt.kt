package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoType

inline fun <reified T> Sequence<KoType>.withSourceType() = filter { it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoType>.withoutSourceType() = filterNot { it.sourceType == T::class.simpleName }

fun Sequence<KoType>.withSourceType(type: String) = filter { it.sourceType == type }

fun Sequence<KoType>.withoutSourceType(type: String) = filterNot { it.sourceType == type }

inline fun <reified T> Sequence<KoType>.withImportAliasName() =
    filter { it.isImportAlias() && it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoType>.withoutImportAliasName() =
    filterNot { it.isImportAlias() && it.sourceType == T::class.simpleName }

fun Sequence<KoType>.withImportAliasName(name: String) = filter { it.importAliasName == name }

fun Sequence<KoType>.withoutImportAliasName(name: String) = filterNot { it.importAliasName == name }

fun Sequence<KoType>.withFullyQualifiedName(name: String) = filter { it.fullyQualifiedName == name }

fun Sequence<KoType>.withoutFullyQualifiedName(name: String) = filterNot { it.fullyQualifiedName == name }

fun Sequence<KoType>.withImportAlias() = filter { it.isImportAlias() }

fun Sequence<KoType>.withoutImportAlias() = filterNot { it.isImportAlias() }
