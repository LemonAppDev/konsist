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
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import kotlin.reflect.KClass

/**
 * List containing declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.declarations: List<KoBaseTypeDeclaration>
    get() = mapNotNull { it.declaration }

/**
 * List containing class declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.classDeclarations: List<KoClassDeclaration>
    get() = mapNotNull { it.asClassDeclaration() }

/**
 * List containing object declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.objectDeclarations: List<KoObjectDeclaration>
    get() = mapNotNull { it.asObjectDeclaration() }

/**
 * List containing interface declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.interfaceDeclarations: List<KoInterfaceDeclaration>
    get() = mapNotNull { it.asInterfaceDeclaration() }

/**
 * List containing type alias declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.typeAliasDeclarations: List<KoTypeAliasDeclaration>
    get() = mapNotNull { it.asTypeAliasDeclaration() }

/**
 * List containing import alias declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.importAliasDeclarations: List<KoImportAliasDeclaration>
    get() = mapNotNull { it.asImportAliasDeclaration() }

/**
 * List containing kotlin type declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.kotlinTypeDeclarations: List<KoKotlinTypeDeclaration>
    get() = mapNotNull { it.asKotlinTypeDeclaration() }

/**
 * List containing function type declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.functionTypeDeclarations: List<KoFunctionTypeDeclaration>
    get() = mapNotNull { it.asFunctionTypeDeclaration() }

/**
 * List containing generic type declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.genericTypeDeclarations: List<KoGenericTypeDeclaration>
    get() = mapNotNull { it.asGenericTypeDeclaration() }

/**
 * List containing type parameter declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.typeParameterDeclarations: List<KoTypeParameterDeclaration>
    get() = mapNotNull { it.asTypeParameterDeclaration() }

/**
 * List containing star projection declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.starProjectionDeclarations: List<KoStarProjectionDeclaration>
    get() = mapNotNull { it.asStarProjectionDeclaration() }

/**
 * List containing external type declarations associated with types.
 */
val <T : KoTypeDeclarationProvider> List<T>.externalTypeDeclarations: List<KoExternalDeclaration>
    get() = mapNotNull { it.asExternalTypeDeclaration() }

/**
 * List containing declarations with the specified declaration.
 *
 * @param predicate The predicate function to determine if a declaration satisfies a condition.
 * @return A list containing declarations with the specified declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.declaration) }

/**
 * List containing declarations without the specified declaration.
 *
 * @param predicate The predicate function to determine if a declaration satisfies a condition.
 * @return A list containing declarations without the specified declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.declaration) }

/**
 * List containing declarations with declaration of.
 *
 * @param kClass The Kotlin class representing the declaration to include.
 * @param kClasses The Kotlin class(es) representing the declaration(s) to include.
 * @return A list containing declarations with the declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the declaration(s) to include.
 * @return A list containing declarations with the declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without declaration of.
 *
 * @param kClass The Kotlin class representing the declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the declaration(s) to exclude.
 * @return A list containing declarations without declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the declaration(s) to exclude.
 * @return A list containing declarations without declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations with the specified class declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasClassDeclaration()
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations without the specified class declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasClassDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to exclude.
 * @return A list containing declarations without class declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasObjectDeclaration()
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified object declaration.
 *
 * @param predicate The predicate function to determine if a object declaration satisfies a condition.
 * @return A list containing declarations without the specified object declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasObjectDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to exclude.
 * @return A list containing declarations without object declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasInterfaceDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasInterfaceDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to exclude.
 * @return A list containing declarations without interface declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasTypeAliasDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasTypeAliasDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.hasImportAliasDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasImportAliasDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasKotlinTypeDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutKotlinTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasKotlinTypeDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to exclude.
 * @return A list containing declarations without kotlin type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasKotlinTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasKotlinTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified function type declaration.
 *
 * @param predicate The predicate function to determine if a function type declaration satisfies a condition.
 * @return A list containing declarations with the specified function type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withFunctionTypeDeclaration(
    predicate: ((KoFunctionTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.hasFunctionTypeDeclaration()
            else ->
                it
                    .asFunctionTypeDeclaration()
                    ?.let { functionTypeDeclaration -> predicate(functionTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified function type declaration.
 *
 * @param predicate The predicate function to determine if a function type declaration satisfies a condition.
 * @return A list containing declarations without the specified function type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutFunctionTypeDeclaration(
    predicate: ((KoFunctionTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasFunctionTypeDeclaration()
            else ->
                it
                    .asFunctionTypeDeclaration()
                    ?.let { functionTypeDeclaration -> predicate(functionTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with the specified generic type declaration.
 *
 * @param predicate The predicate function to determine if a generic type declaration satisfies a condition.
 * @return A list containing declarations with the specified generic type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withGenericTypeDeclaration(
    predicate: ((KoGenericTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.hasGenericTypeDeclaration()
            else ->
                it
                    .asGenericTypeDeclaration()
                    ?.let { genericTypeDeclaration -> predicate(genericTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified generic type declaration.
 *
 * @param predicate The predicate function to determine if a generic type declaration satisfies a condition.
 * @return A list containing declarations without the specified generic type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutGenericTypeDeclaration(
    predicate: ((KoGenericTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasGenericTypeDeclaration()
            else ->
                it
                    .asGenericTypeDeclaration()
                    ?.let { genericTypeDeclaration -> predicate(genericTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with the specified type parameter declaration.
 *
 * @param predicate The predicate function to determine if a type parameter declaration satisfies a condition.
 * @return A list containing declarations with the specified type parameter declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasTypeParameterDeclaration()
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
fun <T : KoTypeDeclarationProvider> List<T>.withoutTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasTypeParameterDeclaration()
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with the specified star projection declaration.
 *
 * @param predicate The predicate function to determine if a star projection declaration satisfies a condition.
 * @return A list containing declarations with the specified star projection declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withStarProjectionDeclaration(predicate: ((KoStarProjectionDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasStarProjectionDeclaration()
            else ->
                it
                    .asStarProjectionDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified star projection declaration.
 *
 * @param predicate The predicate function to determine if a star projection declaration satisfies a condition.
 * @return A list containing declarations without the specified star projection declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutStarProjectionDeclaration(
    predicate: ((KoStarProjectionDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasStarProjectionDeclaration()
            else ->
                it
                    .asStarProjectionDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with the specified external type declaration.
 *
 * @param predicate The predicate function to determine if a external type declaration satisfies a condition.
 * @return A list containing declarations with the specified external type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasExternalTypeDeclaration()
            else ->
                it
                    .asExternalTypeDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations without the specified external type declaration.
 *
 * @param predicate The predicate function to determine if a external type declaration satisfies a condition.
 * @return A list containing declarations without the specified external type declaration.
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutExternalTypeDeclaration(
    predicate: ((KoExternalDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasExternalTypeDeclaration()
            else ->
                it
                    .asExternalTypeDeclaration()
                    ?.let { externalTypeDeclaration -> predicate(externalTypeDeclaration) } ?: false
        }
    }

/**
 * List containing declarations with external type declaration of.
 *
 * @param kClass The Kotlin class representing the external type declaration to include.
 * @param kClasses The Kotlin class(es) representing the external type declaration(s) to include.
 * @return A list containing declarations with the external type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withExternalTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withExternalTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with external type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external type declaration(s) to include.
 * @return A list containing declarations with the external type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withExternalTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasExternalTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without external type declaration of.
 *
 * @param kClass The Kotlin class representing the external type declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the external type declaration(s) to exclude.
 * @return A list containing declarations without external type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutExternalTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutExternalTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without external type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external type declaration(s) to exclude.
 * @return A list containing declarations without external type declaration of the specified Kotlin class(es).
 */
fun <T : KoTypeDeclarationProvider> List<T>.withoutExternalTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasExternalTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalTypeDeclarationOf(kClass) }
        }
    }
