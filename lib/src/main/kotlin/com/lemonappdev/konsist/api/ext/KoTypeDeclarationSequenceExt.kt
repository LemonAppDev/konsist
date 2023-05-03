package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import kotlin.reflect.KClass

inline fun <reified T> Sequence<KoTypeDeclarationImpl>.withSourceTypeOf() = filter { it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoTypeDeclarationImpl>.withoutSourceTypeOf() = filterNot { it.sourceType == T::class.simpleName }

fun Sequence<KoTypeDeclarationImpl>.withSourceTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclarationImpl>.withoutSourceTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclarationImpl>.withSourceType(vararg types: String) = filter {
    types.any { type -> it.sourceType == type }
}

fun Sequence<KoTypeDeclarationImpl>.withoutSourceType(vararg types: String) = filter {
    types.none { type -> it.sourceType == type }
}

inline fun <reified T> Sequence<KoTypeDeclarationImpl>.withImportAliasOf() =
    filter { it.isImportAlias() && it.sourceType == T::class.simpleName }

inline fun <reified T> Sequence<KoTypeDeclarationImpl>.withoutImportAliasOf() =
    filterNot { it.isImportAlias() && it.sourceType == T::class.simpleName }

fun Sequence<KoTypeDeclarationImpl>.withImportAliasOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclarationImpl>.withoutImportAliasOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

fun Sequence<KoTypeDeclarationImpl>.withImportAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.isImportAlias()
        else -> names.any { name -> it.importAliasName == name }
    }
}

fun Sequence<KoTypeDeclarationImpl>.withoutImportAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.isImportAlias()
        else -> names.none { name -> it.importAliasName == name }
    }
}

fun Sequence<KoTypeDeclarationImpl>.withFullyQualifiedName(vararg names: String) = filter {
    names.any { name -> it.fullyQualifiedName == name }
}

fun Sequence<KoTypeDeclarationImpl>.withoutFullyQualifiedName(vararg names: String) = filter {
    names.none { name -> it.fullyQualifiedName == name }
}
