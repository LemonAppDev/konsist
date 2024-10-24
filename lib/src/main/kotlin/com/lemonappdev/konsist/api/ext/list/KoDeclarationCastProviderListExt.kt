@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import kotlin.reflect.KClass

/**
 * List containing class declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class declaration.
 *                  If null, all class declarations are included.
 * @return A list of class declarations that match the provided predicate, or all class declarations if no predicate
 * is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.classDeclarations(
    predicate: ((KoClassDeclaration) -> Boolean)? = null,
): List<KoClassDeclaration> =
    filter { it.hasClassDeclaration(predicate) }
        .mapNotNull { it.asClassDeclaration() }

/**
 * List containing object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the object declaration.
 *                  If null, all object declarations are included.
 * @return A list of object declarations that match the provided predicate, or all object declarations if no predicate
 * is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.objectDeclarations(
    predicate: ((KoObjectDeclaration) -> Boolean)? = null,
): List<KoObjectDeclaration> =
    filter { it.hasObjectDeclaration(predicate) }
        .mapNotNull { it.asObjectDeclaration() }

/**
 * List containing interface declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the interface declaration.
 *                  If null, all interface declarations are included.
 * @return A list of interface declarations that match the provided predicate, or all interface declarations if no
 * predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.interfaceDeclarations(
    predicate: ((KoInterfaceDeclaration) -> Boolean)? = null,
): List<KoInterfaceDeclaration> =
    filter { it.hasInterfaceDeclaration(predicate) }
        .mapNotNull { it.asInterfaceDeclaration() }

/**
 * List containing type alias declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the type alias declaration.
 *                  If null, all type alias declarations are included.
 * @return A list of type alias declarations that match the provided predicate, or all type alias declarations if no
 * predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.typeAliasDeclarations(
    predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null,
): List<KoTypeAliasDeclaration> =
    filter { it.hasTypeAliasDeclaration(predicate) }
        .mapNotNull { it.asTypeAliasDeclaration() }

/**
 * List containing import alias declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the import alias declaration.
 *                  If null, all import alias declarations are included.
 * @return A list of import alias declarations that match the provided predicate, or all import alias declarations if
 * no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.importAliasDeclarations(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<KoImportAliasDeclaration> =
    filter { it.hasImportAliasDeclaration(predicate) }
        .mapNotNull { it.asImportAliasDeclaration() }

/**
 * List containing Kotlin type declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the Kotlin type declaration.
 *                  If null, all Kotlin type declarations are included.
 * @return A list of Kotlin type declarations that match the provided predicate, or all Kotlin type declarations if
 * no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.kotlinTypeDeclarations(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<KoKotlinTypeDeclaration> =
    filter { it.hasKotlinTypeDeclaration(predicate) }
        .mapNotNull { it.asKotlinTypeDeclaration() }

/**
 * List containing Kotlin basic type declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the Kotlin basic type declaration.
 *                  If null, all Kotlin basic type declarations are included.
 * @return A list of Kotlin basic type declarations that match the provided predicate, or all Kotlin basic type
 * declarations if no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.kotlinBasicTypeDeclarations(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<KoKotlinTypeDeclaration> =
    filter { it.hasKotlinBasicTypeDeclaration(predicate) }
        .mapNotNull { it.asKotlinBasicTypeDeclaration() }

/**
 * List containing Kotlin collection type declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the Kotlin collection type declaration.
 *                  If null, all Kotlin collection type declarations are included.
 * @return A list of Kotlin collection type declarations that match the provided predicate, or all Kotlin collection type
 * declarations if no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.kotlinCollectionTypeDeclarations(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<KoKotlinTypeDeclaration> =
    filter { it.hasKotlinCollectionTypeDeclaration(predicate) }
        .mapNotNull { it.asKotlinCollectionTypeDeclaration() }

/**
 * List containing type parameter declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the type parameter declaration.
 *                  If null, all type parameter declarations are included.
 * @return A list of type parameter declarations that match the provided predicate, or all type parameter declarations
 * if no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.typeParameterDeclarations(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<KoTypeParameterDeclaration> =
    filter { it.hasTypeParameterDeclaration(predicate) }
        .mapNotNull { it.asTypeParameterDeclaration() }

/**
 * List containing external declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the external declaration.
 *                  If null, all external declarations are included.
 * @return A list of external declarations that match the provided predicate, or all external declarations
 * if no predicate is provided.
 */
fun <T : KoDeclarationCastProvider> List<T>.externalTypeDeclarations(
    predicate: ((KoExternalDeclaration) -> Boolean)? = null,
): List<KoExternalDeclaration> =
    filter { it.hasExternalDeclaration(predicate) }
        .mapNotNull { it.asExternalDeclaration() }

/**
 * List containing declarations with the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations with the specified class declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations without the specified class declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with class declaration of.
 *
 * @param kClass The Kotlin class representing the class declaration to include.
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassDeclaration()
            else -> kClasses.any { kClass -> it.hasClassDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class declaration of.
 *
 * @param kClass The Kotlin class representing the class declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to exclude.
 * @return A list containing declarations without class declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to exclude.
 * @return A list containing declarations without class declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassDeclaration()
            else -> kClasses.any { kClass -> it.hasClassDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified object declaration.
 *
 * @param predicate The predicate function to determine if a object declaration satisfies a condition.
 * @return A list containing declarations with the specified object declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified object declaration.
 *
 * @param predicate The predicate function to determine if a object declaration satisfies a condition.
 * @return A list containing declarations without the specified object declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with object declaration of.
 *
 * @param kClass The Kotlin class representing the object declaration to include.
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without object declaration of.
 *
 * @param kClass The Kotlin class representing the object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to exclude.
 * @return A list containing declarations without object declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to exclude.
 * @return A list containing declarations without object declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified interface declaration.
 *
 * @param predicate The predicate function to determine if a interface declaration satisfies a condition.
 * @return A list containing declarations with the specified interface declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified interface declaration.
 *
 * @param predicate The predicate function to determine if a interface declaration satisfies a condition.
 * @return A list containing declarations without the specified interface declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClass The Kotlin class representing the interface declaration to include.
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without interface declaration of.
 *
 * @param kClass The Kotlin class representing the interface declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to exclude.
 * @return A list containing declarations without interface declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to exclude.
 * @return A list containing declarations without interface declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations with the specified type alias declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations without the specified type alias declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations with the specified import alias declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it.asImportAliasDeclaration()?.let { importAliasDeclaration -> predicate(importAliasDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations without the specified import alias declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it.asImportAliasDeclaration()?.let { importAliasDeclaration -> predicate(importAliasDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations with the specified kotlin type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasKotlinTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasKotlinTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without kotlin type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin type declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to exclude.
 * @return A list containing declarations without kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to exclude.
 * @return A list containing declarations without kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasKotlinTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasKotlinTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified kotlin basic type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin basic type declaration satisfies a condition.
 * @return A list containing declarations with the specified kotlin basic type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it.asKotlinBasicTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified kotlin basic type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin basic type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin basic type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it.asKotlinBasicTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with kotlin basic type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin basic type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to include.
 * @return A list containing declarations with the kotlin basic type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinBasicTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinBasicTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin basic type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to include.
 * @return A list containing declarations with the kotlin basic type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinBasicTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.isKotlinBasicType
            else -> kClasses.any { kClass -> it.hasKotlinBasicTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without kotlin basic type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin basic type declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to exclude.
 * @return A list containing declarations without kotlin basic type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinBasicTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinBasicTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin basic type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to exclude.
 * @return A list containing declarations without kotlin basic type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinBasicTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.isKotlinBasicType
            else -> kClasses.any { kClass -> it.hasKotlinBasicTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified kotlin collection type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin collection type declaration satisfies a condition.
 * @return A list containing declarations with the specified kotlin collection type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it.asKotlinCollectionTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations without the specified kotlin collection type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin collection type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin collection type declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it.asKotlinCollectionTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) }
                    ?: false
        }
    }

/**
 * List containing declarations with kotlin collection type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin collection type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to include.
 * @return A list containing declarations with the kotlin collection type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinCollectionTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinCollectionTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin collection type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to include.
 * @return A list containing declarations with the kotlin collection type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withKotlinCollectionTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.isKotlinCollectionType
            else -> kClasses.any { kClass -> it.hasKotlinCollectionTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without kotlin collection type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin collection type declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to exclude.
 * @return A list containing declarations without kotlin collection type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinCollectionTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinCollectionTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin collection type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to exclude.
 * @return A list containing declarations without kotlin collection type declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinCollectionTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.isKotlinCollectionType
            else -> kClasses.any { kClass -> it.hasKotlinCollectionTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified type parameter declaration.
 *
 * @param predicate The predicate function to determine if a type parameter declaration satisfies a condition.
 * @return A list containing declarations with the specified type parameter declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified type parameter declaration.
 *
 * @param predicate The predicate function to determine if a type parameter declaration satisfies a condition.
 * @return A list containing declarations without the specified type parameter declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with the specified external declaration.
 *
 * @param predicate The predicate function to determine if a external declaration satisfies a condition.
 * @return A list containing declarations with the specified external declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified external declaration.
 *
 * @param predicate The predicate function to determine if a external declaration satisfies a condition.
 * @return A list containing declarations without the specified external declaration.
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to include.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withExternalDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withExternalDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withExternalDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasExternalDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to exclude.
 * @return A list containing declarations without external declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutExternalDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to exclude.
 * @return A list containing declarations without external declaration of the specified Kotlin class(es).
 */
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasExternalDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalDeclarationOf(kClass) }
        }
    }
