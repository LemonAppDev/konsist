package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoTypeDeclaration
import kotlin.reflect.KClass

inline fun <reified T> Sequence<KoTypeDeclaration>.withSourceTypeOf() = filter { it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoTypeDeclaration>.withoutSourceTypeOf() = filterNot { it.sourceType == T::class.simpleName }

fun Sequence<KoTypeDeclaration>.withSourceTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclaration>.withoutSourceTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclaration>.withSourceType(vararg types: String) = filter {
    types.any { type -> it.sourceType == type }
}

fun Sequence<KoTypeDeclaration>.withoutSourceType(vararg types: String) = filter {
    types.none { type -> it.sourceType == type }
}

inline fun <reified T> Sequence<KoTypeDeclaration>.withImportAliasOf() =
    filter { it.isImportAlias() && it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoTypeDeclaration>.withoutImportAliasOf() =
    filterNot { it.isImportAlias() && it.sourceType == T::class.simpleName }

fun Sequence<KoTypeDeclaration>.withImportAliasOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclaration>.withoutImportAliasOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclaration>.withImportAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.isImportAlias()
        else -> names.any { name -> it.importAliasName == name }
    }
}

fun Sequence<KoTypeDeclaration>.withoutImportAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.isImportAlias()
        else -> names.none { name -> it.importAliasName == name }
    }
}

fun Sequence<KoTypeDeclaration>.withFullyQualifiedName(vararg names: String) = filter {
    names.any { name -> it.fullyQualifiedName == name }
}

fun Sequence<KoTypeDeclaration>.withoutFullyQualifiedName(vararg names: String) = filter {
    names.none { name -> it.fullyQualifiedName == name }
}
