package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have source type of.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withSourceTypeOf(): Sequence<KoTypeDeclaration> =
    filter { it.sourceType == T::class.simpleName }

/**
 * Sequence containing declarations that don't have source type of.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withoutSourceTypeOf(): Sequence<KoTypeDeclaration> =
    filterNot { it.sourceType == T::class.simpleName }

/**
 * Sequence containing declarations that have source type of.
 */
fun Sequence<KoTypeDeclaration>.withSourceTypeOf(vararg types: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have source type of.
 */
fun Sequence<KoTypeDeclaration>.withoutSourceTypeOf(vararg types: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations that have source type.
 */
fun Sequence<KoTypeDeclaration>.withSourceType(vararg types: String): Sequence<KoTypeDeclaration> = filter {
    types.any { type -> it.sourceType == type }
}

/**
 * Sequence containing declarations that don't have source type.
 */
fun Sequence<KoTypeDeclaration>.withoutSourceType(vararg types: String): Sequence<KoTypeDeclaration> = filter {
    types.none { type -> it.sourceType == type }
}

/**
 * Sequence containing declarations that have import alias of.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withImportAliasOf(): Sequence<KoTypeDeclaration> =
    filter { it.isImportAlias() && it.sourceType == T::class.simpleName }

/**
 * Sequence containing declarations that don't have import alias of.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withoutImportAliasOf(): Sequence<KoTypeDeclaration> =
    filterNot { it.isImportAlias() && it.sourceType == T::class.simpleName }

/**
 * Sequence containing declarations that have import alias of.
 */
fun Sequence<KoTypeDeclaration>.withImportAliasOf(vararg names: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    names.any { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have import alias of.
 */
fun Sequence<KoTypeDeclaration>.withoutImportAliasOf(vararg names: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    names.none { kClass -> it.isImportAlias() && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations that have import alias.
 */
fun Sequence<KoTypeDeclaration>.withImportAlias(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    when {
        names.isEmpty() -> it.isImportAlias()
        else -> names.any { name -> it.importAliasName == name }
    }
}

/**
 * Sequence containing declarations that don't have import alias.
 */
fun Sequence<KoTypeDeclaration>.withoutImportAlias(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    when {
        names.isEmpty() -> !it.isImportAlias()
        else -> names.none { name -> it.importAliasName == name }
    }
}

/**
 * Sequence containing declarations that have fully qualified name.
 */
fun Sequence<KoTypeDeclaration>.withFullyQualifiedName(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    names.any { name -> it.fullyQualifiedName == name }
}

/**
 * Sequence containing declarations that don't have fully qualified name.
 */
fun Sequence<KoTypeDeclaration>.withoutFullyQualifiedName(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    names.none { name -> it.fullyQualifiedName == name }
}
