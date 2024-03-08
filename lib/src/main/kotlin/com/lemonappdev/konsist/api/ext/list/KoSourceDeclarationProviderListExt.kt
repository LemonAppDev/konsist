@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import kotlin.reflect.KClass

/**
 * List containing source declarations associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceDeclarations: List<KoBaseTypeDeclaration>
    get() = mapNotNull { it.sourceDeclaration }

/**
 * List containing source classes associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceClasses: List<KoClassDeclaration>
    get() = mapNotNull { it.sourceClass }

/**
 * List containing source objects associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceObjects: List<KoObjectDeclaration>
    get() = mapNotNull { it.sourceObject }

/**
 * List containing source interfaces associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceInterfaces: List<KoInterfaceDeclaration>
    get() = mapNotNull { it.sourceInterface }

/**
 * List containing source type aliases associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceTypeAliases: List<KoTypeAliasDeclaration>
    get() = mapNotNull { it.sourceTypeAlias }

/**
 * List containing source import aliases associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceImportAliases: List<KoImportAliasDeclaration>
    get() = mapNotNull { it.sourceImportAlias }

/**
 * List containing source kotlin types associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceKotlinTypes: List<KoKotlinTypeDeclaration>
    get() = mapNotNull { it.sourceKotlinType }

/**
 * List containing source function types associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceFunctionTypes: List<KoFunctionTypeDeclaration>
    get() = mapNotNull { it.sourceFunctionType }

/**
 * List containing source external types associated with types.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceExternalTypes: List<KoExternalDeclaration>
    get() = mapNotNull { it.sourceExternalType }

/**
 * List containing declarations with the specified source declaration.
 *
 * @param predicate The predicate function to determine if a source declaration satisfies a condition.
 * @return A list containing declarations with the specified source declaration.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.sourceDeclaration) }

/**
 * List containing declarations without the specified source declaration.
 *
 * @param predicate The predicate function to determine if a source declaration satisfies a condition.
 * @return A list containing declarations without the specified source declaration.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.sourceDeclaration) }

/**
 * List containing declarations with source declaration of.
 *
 * @param kClass The Kotlin class representing the source declaration to include.
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to include.
 * @return A list containing declarations with the source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.hasSourceDeclarationOf(kClass) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
            } else {
                false
            }
    }

/**
 * List containing declarations without source declaration of.
 *
 * @param kClass The Kotlin class representing the source declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to exclude.
 * @return A list containing declarations without source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasMatchingSourceDeclaration = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        } else {
            false
        }

        it.hasSourceDeclarationOf(kClass) || hasMatchingSourceDeclaration
    }

/**
 * List containing declarations with the specified source class.
 *
 * @param predicate The predicate function to determine if a source class satisfies a condition.
 * @return A list containing declarations with the specified source class.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceClass()
            else -> it.sourceClass?.let { sourceClass -> predicate(sourceClass) } ?: false
        }
    }

/**
 * List containing declarations without the specified source class.
 *
 * @param predicate The predicate function to determine if a source class satisfies a condition.
 * @return A list containing declarations without the specified source class.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceClass()
            else -> it.sourceClass?.let { sourceClass -> predicate(sourceClass) } ?: false
        }
    }

/**
 * List containing declarations with source class of.
 *
 * @param kClass The Kotlin class representing the source class to include.
 * @param kClasses The Kotlin class(es) representing the source class(es) to include.
 * @return A list containing declarations with the source class of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasAllSourceClasses = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceClassOf(kClass) }
        } else {
            false
        }

        it.hasSourceClassOf(kClass) || hasAllSourceClasses
    }

/**
 * List containing declarations without source class of.
 *
 * @param kClass The Kotlin class representing the source class to exclude.
 * @param kClasses The Kotlin class(es) representing the source class(s) to exclude.
 * @return A list containing declarations without source class of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasAllSourceClasses = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceClassOf(kClass) }
        } else {
            false
        }

        it.hasSourceClassOf(kClass) || hasAllSourceClasses
    }

/**
 * List containing declarations with the specified source object.
 *
 * @param predicate The predicate function to determine if a source object satisfies a condition.
 * @return A list containing declarations with the specified source object.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceObject(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceObject()
            else -> it.sourceObject?.let { sourceObject -> predicate(sourceObject) } ?: false
        }
    }

/**
 * List containing declarations without the specified source object.
 *
 * @param predicate The predicate function to determine if a source object satisfies a condition.
 * @return A list containing declarations without the specified source object.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceObject(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceObject()
            else -> it.sourceObject?.let { sourceObject -> predicate(sourceObject) } ?: false
        }
    }

/**
 * List containing declarations with source object of.
 *
 * @param kClass The Kotlin class representing the source object to include.
 * @param kClasses The Kotlin class(es) representing the source object(s) to include.
 * @return A list containing declarations with the source object of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceObjectOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasAllSourceObjects = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceObjectOf(kClass) }
        } else {
            false
        }

        it.hasSourceObjectOf(kClass) || hasAllSourceObjects
    }

/**
 * List containing declarations without source object of.
 *
 * @param kClass The Kotlin class representing the source object to exclude.
 * @param kClasses The Kotlin class(es) representing the source object(s) to exclude.
 * @return A list containing declarations without source object of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceObjectOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasAllSourceObjects = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceObjectOf(kClass) }
        } else {
            false
        }

        it.hasSourceObjectOf(kClass) || hasAllSourceObjects
    }

/**
 * List containing declarations with the specified source interface.
 *
 * @param predicate The predicate function to determine if a source interface satisfies a condition.
 * @return A list containing declarations with the specified source interface.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceInterface(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceInterface()
            else -> it.sourceInterface?.let { sourceInterface -> predicate(sourceInterface) } ?: false
        }
    }

/**
 * List containing declarations without the specified source interface.
 *
 * @param predicate The predicate function to determine if a source interface satisfies a condition.
 * @return A list containing declarations without the specified source interface.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceInterface(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceInterface()
            else -> it.sourceInterface?.let { sourceInterface -> predicate(sourceInterface) } ?: false
        }
    }

/**
 * List containing declarations with source interface of.
 *
 * @param kClass The Kotlin class representing the source interface to include.
 * @param kClasses The Kotlin class(es) representing the source interface(s) to include.
 * @return A list containing declarations with the source interface of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceInterfaceOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasAllSourceInterfaces = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceInterfaceOf(kClass) }
        } else {
            false
        }

        it.hasSourceInterfaceOf(kClass) || hasAllSourceInterfaces
    }

/**
 * List containing declarations without source interface of.
 *
 * @param kClass The Kotlin class representing the source interface to exclude.
 * @param kClasses The Kotlin class(es) representing the source interface(s) to exclude.
 * @return A list containing declarations without source interface of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceInterfaceOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasAllSourceInterfaces = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceInterfaceOf(kClass) }
        } else {
            false
        }

        it.hasSourceInterfaceOf(kClass) || hasAllSourceInterfaces
    }

/**
 * List containing declarations with the specified source type alias.
 *
 * @param predicate The predicate function to determine if a source type alias satisfies a condition.
 * @return A list containing declarations with the specified source type alias.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceTypeAlias(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceTypeAlias()
            else -> it.sourceTypeAlias?.let { sourceTypeAlias -> predicate(sourceTypeAlias) } ?: false
        }
    }

/**
 * List containing declarations without the specified source type alias.
 *
 * @param predicate The predicate function to determine if a source type alias satisfies a condition.
 * @return A list containing declarations without the specified source type alias.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceTypeAlias(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceTypeAlias()
            else -> it.sourceTypeAlias?.let { sourceTypeAlias -> predicate(sourceTypeAlias) } ?: false
        }
    }

/**
 * List containing declarations with the specified source import alias.
 *
 * @param predicate The predicate function to determine if a source import alias satisfies a condition.
 * @return A list containing declarations with the specified source import alias.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceImportAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceImportAlias()
            else -> it.sourceImportAlias?.let { sourceImportAlias -> predicate(sourceImportAlias) } ?: false
        }
    }

/**
 * List containing declarations without the specified source import alias.
 *
 * @param predicate The predicate function to determine if a source import alias satisfies a condition.
 * @return A list containing declarations without the specified source import alias.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceImportAlias(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceImportAlias()
            else -> it.sourceImportAlias?.let { sourceImportAlias -> predicate(sourceImportAlias) } ?: false
        }
    }

/**
 * List containing declarations with the specified source kotlin type.
 *
 * @param predicate The predicate function to determine if a source kotlin type satisfies a condition.
 * @return A list containing declarations with the specified source kotlin type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceKotlinType(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceKotlinType()
            else -> it.sourceKotlinType?.let { sourceKotlinType -> predicate(sourceKotlinType) } ?: false
        }
    }

/**
 * List containing declarations without the specified source kotlin type.
 *
 * @param predicate The predicate function to determine if a source kotlin type satisfies a condition.
 * @return A list containing declarations without the specified source kotlin type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceKotlinType(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceKotlinType()
            else -> it.sourceKotlinType?.let { sourceKotlinType -> predicate(sourceKotlinType) } ?: false
        }
    }

/**
 * List containing declarations with source kotlin type of.
 *
 * @param kClass The Kotlin class representing the source kotlin type to include.
 * @param kClasses The Kotlin class(es) representing the source kotlin type(s) to include.
 * @return A list containing declarations with the source kotlin type of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceKotlinTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasAllSourceKotlinTypes = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceKotlinTypeOf(kClass) }
        } else {
            false
        }

        it.hasSourceKotlinTypeOf(kClass) || hasAllSourceKotlinTypes
    }

/**
 * List containing declarations without source kotlin type of.
 *
 * @param kClass The Kotlin class representing the source kotlin type to exclude.
 * @param kClasses The Kotlin class(es) representing the source kotlin type(s) to exclude.
 * @return A list containing declarations without source kotlin type of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceKotlinTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasAllSourceKotlinTypes = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceKotlinTypeOf(kClass) }
        } else {
            false
        }

        it.hasSourceKotlinTypeOf(kClass) || hasAllSourceKotlinTypes
    }

/**
 * List containing declarations with the specified source function type.
 *
 * @param predicate The predicate function to determine if a source function type satisfies a condition.
 * @return A list containing declarations with the specified source function type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceFunctionType(predicate: ((KoFunctionTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceFunctionType()
            else -> it.sourceFunctionType?.let { sourceFunctionType -> predicate(sourceFunctionType) } ?: false
        }
    }

/**
 * List containing declarations without the specified source function type.
 *
 * @param predicate The predicate function to determine if a source function type satisfies a condition.
 * @return A list containing declarations without the specified source function type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceFunctionType(
    predicate: ((KoFunctionTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceFunctionType()
            else -> it.sourceFunctionType?.let { sourceFunctionType -> predicate(sourceFunctionType) } ?: false
        }
    }

/**
 * List containing declarations with the specified source external type.
 *
 * @param predicate The predicate function to determine if a source external type satisfies a condition.
 * @return A list containing declarations with the specified source external type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceExternalType(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasSourceExternalType()
            else -> it.sourceExternalType?.let { sourceExternalType -> predicate(sourceExternalType) } ?: false
        }
    }

/**
 * List containing declarations without the specified source external type.
 *
 * @param predicate The predicate function to determine if a source external type satisfies a condition.
 * @return A list containing declarations without the specified source external type.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceExternalType(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasSourceExternalType()
            else -> it.sourceExternalType?.let { sourceExternalType -> predicate(sourceExternalType) } ?: false
        }
    }

/**
 * List containing declarations with source external type of.
 *
 * @param kClass The Kotlin class representing the source external type to include.
 * @param kClasses The Kotlin class(es) representing the source external type(s) to include.
 * @return A list containing declarations with the source external type of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceExternalTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasAllSourceExternalTypes = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceExternalTypeOf(kClass) }
        } else {
            false
        }

        it.hasSourceExternalTypeOf(kClass) || hasAllSourceExternalTypes
    }

/**
 * List containing declarations without source external type of.
 *
 * @param kClass The Kotlin class representing the source external type to exclude.
 * @param kClasses The Kotlin class(es) representing the source external type(s) to exclude.
 * @return A list containing declarations without source external type of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceExternalTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val hasAllSourceExternalTypes = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasSourceExternalTypeOf(kClass) }
        } else {
            false
        }

        it.hasSourceExternalTypeOf(kClass) || hasAllSourceExternalTypes
    }
