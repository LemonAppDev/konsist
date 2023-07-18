package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing types with source type of.
 *
 * @return A sequence containing types with the source type matching the specified type.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withSourceTypeOf(): Sequence<KoTypeDeclaration> =
    filter { it.sourceType == T::class.simpleName }

/**
 * Sequence containing types without source type of.
 *
 * @return A sequence containing types without source type matching the specified type.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withoutSourceTypeOf(): Sequence<KoTypeDeclaration> =
    filterNot { it.sourceType == T::class.simpleName }

/**
 * Sequence containing types with source type of.
 *
 * @param types The Kotlin classes representing the source types to include.
 * @return A sequence containing types with the source type matching any of the specified types.
 */
fun Sequence<KoTypeDeclaration>.withSourceTypeOf(vararg types: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing types without source type of.
 *
 * @param types The Kotlin classes representing the source types to exclude.
 * @return A sequence containing types without source type matching any of the specified types.
 */
fun Sequence<KoTypeDeclaration>.withoutSourceTypeOf(vararg types: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing types with source type.
 *
 * @param type The source type to include.
 * @param types The source types to include.
 * @return A sequence containing types with the specified source types.
 */
fun Sequence<KoTypeDeclaration>.withSourceType(type: String, vararg types: String): Sequence<KoTypeDeclaration> = filter {
    it.sourceType == type || types.any { type -> it.sourceType == type }
}

/**
 * Sequence containing types without source type.
 *
 * @param type The source type to exclude.
 * @param types The source types to exclude.
 * @return A sequence containing types without specified source types.
 */
fun Sequence<KoTypeDeclaration>.withoutSourceType(type: String, vararg types: String): Sequence<KoTypeDeclaration> = filter {
    it.sourceType != type && types.none { type -> it.sourceType == type }
}

/**
 * Sequence containing types with import alias of.
 *
 * @return A sequence containing types with an import alias matching the specified type.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withImportAliasOf(): Sequence<KoTypeDeclaration> =
    filter { it.isAlias() && it.sourceType == T::class.simpleName }

/**
 * Sequence containing types without import alias of.
 *
 * @return A sequence containing types without an import alias matching the specified type.
 */
inline fun <reified T> Sequence<KoTypeDeclaration>.withoutImportAliasOf(): Sequence<KoTypeDeclaration> =
    filterNot { it.isAlias() && it.sourceType == T::class.simpleName }

/**
 * Sequence containing types with import alias of.
 *
 * @param names The Kotlin classes representing the import alias to include.
 * @return A sequence containing types with the import alias matching any of the specified types.
 */
fun Sequence<KoTypeDeclaration>.withImportAliasOf(vararg names: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    names.any { kClass -> it.isAlias() && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing types without import alias of.
 *
 * @param names The Kotlin classes representing the import alias to exclude.
 * @return A sequence containing types without import alias matching any of the specified types.
 */
fun Sequence<KoTypeDeclaration>.withoutImportAliasOf(vararg names: KClass<*>): Sequence<KoTypeDeclaration> = filter {
    names.none { kClass -> it.isAlias() && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing types with import alias.
 *
 * @param names The import alias names to include.
 * @return A sequence containing types with an import alias matching any of the specified names
 * (or any import alias if [names] is empty).
 */
fun Sequence<KoTypeDeclaration>.withImportAlias(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    when {
        names.isEmpty() -> it.isAlias()
        else -> names.any { name -> it.aliasType == name }
    }
}

/**
 * Sequence containing types without import alias.
 *
 * @param names The import alias names to exclude.
 * @return A sequence containing types without an import alias matching any of the specified names
 * (or none import alias if [names] is empty).
 */
fun Sequence<KoTypeDeclaration>.withoutImportAlias(vararg names: String): Sequence<KoTypeDeclaration> = filter {
    when {
        names.isEmpty() -> !it.isAlias()
        else -> names.none { name -> it.aliasType == name }
    }
}

/**
 * Sequence containing types with fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing types with the specified fully qualified names.
 */
fun Sequence<KoTypeDeclaration>.withFullyQualifiedName(name: String, vararg names: String): Sequence<KoTypeDeclaration> = filter {
    it.fullyQualifiedName == name || names.any { name -> it.fullyQualifiedName == name }
}

/**
 * Sequence containing types without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing types without the specified fully qualified names.
 */
fun Sequence<KoTypeDeclaration>.withoutFullyQualifiedName(name: String, vararg names: String): Sequence<KoTypeDeclaration> = filter {
    it.fullyQualifiedName != name && names.none { name -> it.fullyQualifiedName == name }
}

/**
 * Sequence containing types that have generic type.
 *
 * @return A sequence containing types with the generic types.
 */
fun Sequence<KoTypeDeclaration>.withGenericType(): Sequence<KoTypeDeclaration> = filter { it.isGenericType }

/**
 * Sequence containing types that don't have generic type.
 *
 * @return A sequence containing types without the generic types.
 */
fun Sequence<KoTypeDeclaration>.withoutGenericType(): Sequence<KoTypeDeclaration> = filterNot { it.isGenericType }
