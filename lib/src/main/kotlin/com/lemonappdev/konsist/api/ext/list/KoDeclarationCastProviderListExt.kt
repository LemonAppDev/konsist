@file:Suppress("detekt.TooManyFunctions")
@file:RemoveInVersion("0.19.0 - all @JvmName annotations")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import kotlin.reflect.KClass

/**
 * List containing class declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("classDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.classDeclarations: List<KoClassDeclaration>
    get() = mapNotNull { it.asClassDeclaration() }

/**
 * List containing object declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("objectDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.objectDeclarations: List<KoObjectDeclaration>
    get() = mapNotNull { it.asObjectDeclaration() }

/**
 * List containing interface declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("interfaceDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.interfaceDeclarations: List<KoInterfaceDeclaration>
    get() = mapNotNull { it.asInterfaceDeclaration() }

/**
 * List containing type alias declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("typeAliasDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.typeAliasDeclarations: List<KoTypeAliasDeclaration>
    get() = mapNotNull { it.asTypeAliasDeclaration() }

/**
 * List containing import alias declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("importAliasDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.importAliasDeclarations: List<KoImportAliasDeclaration>
    get() = mapNotNull { it.asImportAliasDeclaration() }

/**
 * List containing kotlin type declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("kotlinTypeDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.kotlinTypeDeclarations: List<KoKotlinTypeDeclaration>
    get() = mapNotNull { it.asKotlinTypeDeclaration() }

/**
 * List containing external type declarations associated with types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("externalDeclarations()"))
val <T : KoDeclarationCastProvider> List<T>.externalTypeDeclarations: List<KoExternalDeclaration>
    get() = mapNotNull { it.asExternalDeclaration() }

/**
 * List containing class declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class declaration.
 *                  If null, all class declarations are included.
 * @return A list of class declarations that match the provided predicate, or all class declarations if no predicate
 * is provided.
 */
@JvmName("classDeclarationsForProvider")
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
@JvmName("objectDeclarationsForProvider")
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
@JvmName("interfaceDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.interfaceDeclarations(
    predicate: ((KoInterfaceDeclaration) -> Boolean)? = null,
): List<KoInterfaceDeclaration> =
    filter { it.hasInterfaceDeclaration(predicate) }
        .mapNotNull { it.asInterfaceDeclaration() }

/**
 * List containing class and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class and object declaration.
 *                  If null, all class and object declarations are included.
 * @return A list of class and object declarations that match the provided predicate, or all class and object
 * declarations if no predicate is provided.
 */
@JvmName("classAndObjectDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.classAndObjectDeclarations(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<KoClassAndObjectDeclaration> =
    filter { it.hasClassOrObjectDeclaration(predicate) }
        .mapNotNull { it.asClassOrObjectDeclaration() }

/**
 * List containing class and interface declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class and interface declaration.
 *                  If null, all class and interface declarations are included.
 * @return A list of class and interface declarations that match the provided predicate, or all class and interface
 * declarations if no predicate is provided.
 */
@JvmName("classAndInterfaceDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.classAndInterfaceDeclarations(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<KoClassAndInterfaceDeclaration> =
    filter { it.hasClassOrInterfaceDeclaration(predicate) }
        .mapNotNull { it.asClassOrInterfaceDeclaration() }

/**
 * List containing interface and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the interface and object declaration.
 *                  If null, all interface and object declarations are included.
 * @return A list of interface and object declarations that match the provided predicate, or all interface and object
 * declarations if no predicate is provided.
 */
@JvmName("interfaceAndObjectDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.interfaceAndObjectDeclarations(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<KoInterfaceAndObjectDeclaration> =
    filter { it.hasInterfaceOrObjectDeclaration(predicate) }
        .mapNotNull { it.asInterfaceOrObjectDeclaration() }

/**
 * List containing class, interface and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class, interface and object declaration.
 *                  If null, all class, interface and object declarations are included.
 * @return A list of class, interface and object declarations that match the provided predicate, or all class,
 * interface and object declarations if no predicate
 * is provided.
 */
@JvmName("classAndInterfaceAndObjectDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.classAndInterfaceAndObjectDeclarations(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<KoClassAndInterfaceAndObjectDeclaration> =
    filter { it.hasClassOrInterfaceOrObjectDeclaration(predicate) }
        .mapNotNull { it.asClassOrInterfaceOrObjectDeclaration() }

/**
 * List containing type alias declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the type alias declaration.
 *                  If null, all type alias declarations are included.
 * @return A list of type alias declarations that match the provided predicate, or all type alias declarations if no
 * predicate is provided.
 */
@JvmName("typeAliasDeclarationsForProvider")
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
@JvmName("importAliasDeclarationsForProvider")
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
@JvmName("kotlinTypeDeclarationsForProvider")
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
@JvmName("kotlinBasicTypeDeclarationsForProvider")
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
@JvmName("kotlinCollectionTypeDeclarationsForProvider")
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
@JvmName("typeParameterDeclarationsForProvider")
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
@JvmName("externalDeclarationsForProvider")
fun <T : KoDeclarationCastProvider> List<T>.externalDeclarations(
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
@JvmName("withClassDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations without the specified class declaration.
 */
@JvmName("withoutClassDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } == true
        }
    }

/**
 * List containing declarations with class declaration of.
 *
 * @param kClass The Kotlin class representing the class declaration to include.
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
@JvmName("withClassDeclarationOfForProvider")
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
@JvmName("withClassDeclarationOfForProvider")
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
@JvmName("withoutClassDeclarationOfForProvider")
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
@JvmName("withoutClassDeclarationOfForProvider")
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
@JvmName("withObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified object declaration.
 *
 * @param predicate The predicate function to determine if a object declaration satisfies a condition.
 * @return A list containing declarations without the specified object declaration.
 */
@JvmName("withoutObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } == true
        }
    }

/**
 * List containing declarations with object declaration of.
 *
 * @param kClass The Kotlin class representing the object declaration to include.
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
@JvmName("withObjectDeclarationOfForProvider")
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
@JvmName("withObjectDeclarationOfForProvider")
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
@JvmName("withoutObjectDeclarationOfForProvider")
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
@JvmName("withoutObjectDeclarationOfForProvider")
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
@JvmName("withInterfaceDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified interface declaration.
 *
 * @param predicate The predicate function to determine if a interface declaration satisfies a condition.
 * @return A list containing declarations without the specified interface declaration.
 */
@JvmName("withoutInterfaceDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) } == true
        }
    }

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClass The Kotlin class representing the interface declaration to include.
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
@JvmName("withInterfaceDeclarationOfForProvider")
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
@JvmName("withInterfaceDeclarationOfForProvider")
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
@JvmName("withoutInterfaceDeclarationOfForProvider")
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
@JvmName("withoutInterfaceDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class or object declaration.
 *
 * @param predicate The predicate function to determine if a class or object declaration satisfies a condition.
 * @return A list containing declarations with the specified class or object declaration.
 */
@JvmName("withClassOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrObjectDeclaration(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrObject
            else -> it.asClassOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class or object declaration.
 *
 * @param predicate The predicate function to determine if a class or object declaration satisfies a condition.
 * @return A list containing declarations without the specified class or object declaration.
 */
@JvmName("withoutClassOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrObjectDeclaration(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrObject
            else -> it.asClassOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class or object declaration of.
 *
 * @param kClass The Kotlin class representing the class or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to include.
 * @return A list containing declarations with the class or object declaration of the specified Kotlin class(es).
 */

fun <T : KoDeclarationCastProvider> List<T>.withClassOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to include.
 * @return A list containing declarations with the class or object declaration of the specified Kotlin class(es).
 */
@JvmName("withClassOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class or object declaration of.
 *
 * @param kClass The Kotlin class representing the class or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to exclude.
 * @return A list containing declarations without class or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to exclude.
 * @return A list containing declarations without class or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class or interface declaration.
 *
 * @param predicate The predicate function to determine if a class or interface declaration satisfies a condition.
 * @return A list containing declarations with the specified class or interface declaration.
 */
@JvmName("withClassOrInterfaceDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceDeclaration(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrInterface
            else -> it.asClassOrInterfaceDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class or interface declaration.
 *
 * @param predicate The predicate function to determine if a class or interface declaration satisfies a condition.
 * @return A list containing declarations without the specified class or interface declaration.
 */
@JvmName("withoutClassOrInterfaceDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceDeclaration(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrInterface
            else -> it.asClassOrInterfaceDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class or interface declaration of.
 *
 * @param kClass The Kotlin class representing the class or interface declaration to include.
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to include.
 * @return A list containing declarations with the class or interface declaration of the specified Kotlin class(es).
 */

fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class or interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to include.
 * @return A list containing declarations with the class or interface declaration of the specified Kotlin class(es).
 */
@JvmName("withClassOrInterfaceDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class or interface declaration of.
 *
 * @param kClass The Kotlin class representing the class or interface declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to exclude.
 * @return A list containing declarations without class or interface declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrInterfaceDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class or interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to exclude.
 * @return A list containing declarations without class or interface declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrInterfaceDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified interface or object declaration.
 *
 * @param predicate The predicate function to determine if a interface or object declaration satisfies a condition.
 * @return A list containing declarations with the specified interface or object declaration.
 */
@JvmName("withInterfaceOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceOrObjectDeclaration(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isInterfaceOrObject
            else -> it.asInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified interface or object declaration.
 *
 * @param predicate The predicate function to determine if a interface or object declaration satisfies a condition.
 * @return A list containing declarations without the specified interface or object declaration.
 */
@JvmName("withoutInterfaceOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceOrObjectDeclaration(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isInterfaceOrObject
            else -> it.asInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the interface or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to include.
 * @return A list containing declarations with the interface or object declaration of the specified Kotlin class(es).
 */

fun <T : KoDeclarationCastProvider> List<T>.withInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to include.
 * @return A list containing declarations with the interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the interface or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to exclude.
 * @return A list containing declarations without interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to exclude.
 * @return A list containing declarations without interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class, interface or object declaration.
 *
 * @param predicate The predicate function to determine if an class, interface or object declaration satisfies a condition.
 * @return A list containing declarations with the specified class, interface or object declaration.
 */
@JvmName("withClassOrInterfaceOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceOrObjectDeclaration(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrInterfaceOrObject
            else -> it.asClassOrInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class, interface or object declaration.
 *
 * @param predicate The predicate function to determine if a class, interface or object declaration satisfies a condition.
 * @return A list containing declarations without the specified class, interface or object declaration.
 */
@JvmName("withoutClassOrInterfaceOrObjectDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceOrObjectDeclaration(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrInterfaceOrObject
            else -> it.asClassOrInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class, interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the class, interface or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to include.
 * @return A list containing declarations with the class, interface or object declaration of the specified Kotlin class(es).
 */

fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class, interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to include.
 * @return A list containing declarations with the class, interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withClassOrInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withClassOrInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class, interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the class, interface or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to exclude.
 * @return A list containing declarations without class, interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class, interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to exclude.
 * @return A list containing declarations without class, interface or object declaration of the specified Kotlin class(es).
 */
@JvmName("withoutClassOrInterfaceOrObjectDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutClassOrInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations with the specified type alias declaration.
 */
@JvmName("withTypeAliasDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations without the specified type alias declaration.
 */
@JvmName("withoutTypeAliasDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations with the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations with the specified import alias declaration.
 */
@JvmName("withImportAliasDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it
                    .asImportAliasDeclaration()
                    ?.let { importAliasDeclaration -> predicate(importAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations without the specified import alias declaration.
 */
@JvmName("withoutImportAliasDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it
                    .asImportAliasDeclaration()
                    ?.let { importAliasDeclaration -> predicate(importAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations with the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations with the specified kotlin type declaration.
 */
@JvmName("withKotlinTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin type declaration.
 */
@JvmName("withoutKotlinTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
@JvmName("withKotlinTypeDeclarationOfForProvider")
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
@JvmName("withKotlinTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinTypeDeclarationOfForProvider")
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
@JvmName("withKotlinBasicTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it
                    .asKotlinBasicTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin basic type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin basic type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin basic type declaration.
 */
@JvmName("withoutKotlinBasicTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it
                    .asKotlinBasicTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin basic type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin basic type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to include.
 * @return A list containing declarations with the kotlin basic type declaration of the specified Kotlin class(es).
 */
@JvmName("withKotlinBasicTypeDeclarationOfForProvider")
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
@JvmName("withKotlinBasicTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinBasicTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinBasicTypeDeclarationOfForProvider")
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
@JvmName("withKotlinCollectionTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it
                    .asKotlinCollectionTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin collection type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin collection type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin collection type declaration.
 */
@JvmName("withoutKotlinCollectionTypeDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it
                    .asKotlinCollectionTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin collection type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin collection type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to include.
 * @return A list containing declarations with the kotlin collection type declaration of the specified Kotlin class(es).
 */
@JvmName("withKotlinCollectionTypeDeclarationOfForProvider")
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
@JvmName("withKotlinCollectionTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinCollectionTypeDeclarationOfForProvider")
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
@JvmName("withoutKotlinCollectionTypeDeclarationOfForProvider")
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
@JvmName("withTypeParameterDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { typeParameter -> predicate(typeParameter) } == true
        }
    }

/**
 * List containing declarations without the specified type parameter declaration.
 *
 * @param predicate The predicate function to determine if a type parameter declaration satisfies a condition.
 * @return A list containing declarations without the specified type parameter declaration.
 */
@JvmName("withoutTypeParameterDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { typeParameter -> predicate(typeParameter) } == true
        }
    }

/**
 * List containing declarations with the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations with the specified external declaration.
 */
@JvmName("withExternalDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations without the specified external declaration.
 */
@JvmName("withoutExternalDeclarationForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations with external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to include.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
@JvmName("withExternalDeclarationOfForProvider")
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
@JvmName("withExternalDeclarationOfForProvider")
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
@JvmName("withoutExternalDeclarationOfForProvider")
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
@JvmName("withoutExternalDeclarationOfForProvider")
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasExternalDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations with the specified external declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withExternalDeclaration"))
fun <T : KoDeclarationCastProvider> List<T>.withExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isExternalType
            else ->
                it
                    .asExternalTypeDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations without the specified external declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutExternalDeclaration"))
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalTypeDeclaration(
    predicate: ((KoExternalDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isExternalType
            else ->
                it
                    .asExternalTypeDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations with external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to include.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withExternalDeclarationOf"))
fun <T : KoDeclarationCastProvider> List<T>.withExternalTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withExternalTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withExternalDeclarationOf"))
fun <T : KoDeclarationCastProvider> List<T>.withExternalTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasExternalTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to exclude.
 * @return A list containing declarations without external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutExternalDeclarationOf"))
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutExternalTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to exclude.
 * @return A list containing declarations without external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutExternalDeclarationOf"))
fun <T : KoDeclarationCastProvider> List<T>.withoutExternalTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasExternalTypeDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalTypeDeclarationOf(kClass) }
        }
    }

/**
 * List containing class declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class declaration.
 *                  If null, all class declarations are included.
 * @return A list of class declarations that match the provided predicate, or all class declarations if no predicate
 * is provided.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().classDeclarations()"))
@JvmName("classDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.classDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().objectDeclarations()"))
@JvmName("objectDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.objectDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().interfaceDeclarations()"))
@JvmName("interfaceDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.interfaceDeclarations(
    predicate: ((KoInterfaceDeclaration) -> Boolean)? = null,
): List<KoInterfaceDeclaration> =
    filter { it.hasInterfaceDeclaration(predicate) }
        .mapNotNull { it.asInterfaceDeclaration() }

/**
 * List containing class and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class and object declaration.
 *                  If null, all class and object declarations are included.
 * @return A list of class and object declarations that match the provided predicate, or all class and object
 * declarations if no predicate is provided.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().classAndObjectDeclarations()"))
@JvmName("classAndObjectDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.classAndObjectDeclarations(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<KoClassAndObjectDeclaration> =
    filter { it.hasClassOrObjectDeclaration(predicate) }
        .mapNotNull { it.asClassOrObjectDeclaration() }

/**
 * List containing class and interface declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class and interface declaration.
 *                  If null, all class and interface declarations are included.
 * @return A list of class and interface declarations that match the provided predicate, or all class and interface
 * declarations if no predicate is provided.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().classAndInterfaceDeclarations()"))
@JvmName("classAndInterfaceDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.classAndInterfaceDeclarations(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<KoClassAndInterfaceDeclaration> =
    filter { it.hasClassOrInterfaceDeclaration(predicate) }
        .mapNotNull { it.asClassOrInterfaceDeclaration() }

/**
 * List containing interface and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the interface and object declaration.
 *                  If null, all interface and object declarations are included.
 * @return A list of interface and object declarations that match the provided predicate, or all interface and object
 * declarations if no predicate is provided.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().interfaceAndObjectDeclarations()"))
@JvmName("interfaceAndObjectDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.interfaceAndObjectDeclarations(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<KoInterfaceAndObjectDeclaration> =
    filter { it.hasInterfaceOrObjectDeclaration(predicate) }
        .mapNotNull { it.asInterfaceOrObjectDeclaration() }

/**
 * List containing class, interface and object declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the class, interface and object declaration.
 *                  If null, all class, interface and object declarations are included.
 * @return A list of class, interface and object declarations that match the provided predicate, or all class,
 * interface and object declarations if no predicate
 * is provided.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().classAndInterfaceAndObjectDeclarations()")
)
@JvmName("classAndInterfaceAndObjectDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.classAndInterfaceAndObjectDeclarations(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<KoClassAndInterfaceAndObjectDeclaration> =
    filter { it.hasClassOrInterfaceOrObjectDeclaration(predicate) }
        .mapNotNull { it.asClassOrInterfaceOrObjectDeclaration() }

/**
 * List containing type alias declarations associated with declarations.
 *
 * @param predicate A function that defines the condition to be met by the type alias declaration.
 *                  If null, all type alias declarations are included.
 * @return A list of type alias declarations that match the provided predicate, or all type alias declarations if no
 * predicate is provided.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().typeAliasDeclarations()"))
@JvmName("typeAliasDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.typeAliasDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().importAliasDeclarations()"))
@JvmName("importAliasDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.importAliasDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().kotlinTypeDeclarations()"))
@JvmName("kotlinTypeDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.kotlinTypeDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().kotlinBasicTypeDeclarations()"))
@JvmName("kotlinBasicTypeDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.kotlinBasicTypeDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().kotlinCollectionTypeDeclarations()"))
@JvmName("kotlinCollectionTypeDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.kotlinCollectionTypeDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().typeParameterDeclarations()"))
@JvmName("typeParameterDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.typeParameterDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().externalDeclarations()"))
@JvmName("externalDeclarationsForType")
fun <T : KoTypeDeclaration> List<T>.externalDeclarations(
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassDeclaration()"))
@JvmName("withClassDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified class declaration.
 *
 * @param predicate The predicate function to determine if a class declaration satisfies a condition.
 * @return A list containing declarations without the specified class declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutClassDeclaration()"))
@JvmName("withoutClassDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClass
            else -> it.asClassDeclaration()?.let { classDeclaration -> predicate(classDeclaration) } == true
        }
    }

/**
 * List containing declarations with class declaration of.
 *
 * @param kClass The Kotlin class representing the class declaration to include.
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassDeclarationOf()"))
@JvmName("withClassDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to include.
 * @return A list containing declarations with the class declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassDeclarationOf()"))
@JvmName("withClassDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutClassDeclarationOf()"))
@JvmName("withoutClassDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class declaration(s) to exclude.
 * @return A list containing declarations without class declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutClassDeclarationOf()"))
@JvmName("withoutClassDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withObjectDeclaration()"))
@JvmName("withObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified object declaration.
 *
 * @param predicate The predicate function to determine if a object declaration satisfies a condition.
 * @return A list containing declarations without the specified object declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutObjectDeclaration()"))
@JvmName("withoutObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isObject
            else -> it.asObjectDeclaration()?.let { objectDeclaration -> predicate(objectDeclaration) } == true
        }
    }

/**
 * List containing declarations with object declaration of.
 *
 * @param kClass The Kotlin class representing the object declaration to include.
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withObjectDeclarationOf()"))
@JvmName("withObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to include.
 * @return A list containing declarations with the object declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withObjectDeclarationOf()"))
@JvmName("withObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutObjectDeclarationOf()"))
@JvmName("withoutObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the object declaration(s) to exclude.
 * @return A list containing declarations without object declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutObjectDeclarationOf()"))
@JvmName("withoutObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withInterfaceDeclaration()"))
@JvmName("withInterfaceDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified interface declaration.
 *
 * @param predicate The predicate function to determine if a interface declaration satisfies a condition.
 * @return A list containing declarations without the specified interface declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutInterfaceDeclaration()"))
@JvmName("withoutInterfaceDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isInterface
            else ->
                it.asInterfaceDeclaration()?.let { interfaceDeclaration -> predicate(interfaceDeclaration) } == true
        }
    }

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClass The Kotlin class representing the interface declaration to include.
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withInterfaceDeclarationOf()"))
@JvmName("withInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to include.
 * @return A list containing declarations with the interface declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withInterfaceDeclarationOf()"))
@JvmName("withInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutInterfaceDeclarationOf()"))
@JvmName("withoutInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface declaration(s) to exclude.
 * @return A list containing declarations without interface declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutInterfaceDeclarationOf()"))
@JvmName("withoutInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class or object declaration.
 *
 * @param predicate The predicate function to determine if a class or object declaration satisfies a condition.
 * @return A list containing declarations with the specified class or object declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassOrObjectDeclaration()"))
@JvmName("withClassOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrObjectDeclaration(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrObject
            else -> it.asClassOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class or object declaration.
 *
 * @param predicate The predicate function to determine if a class or object declaration satisfies a condition.
 * @return A list containing declarations without the specified class or object declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutClassOrObjectDeclaration()"))
@JvmName("withoutClassOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrObjectDeclaration(
    predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrObject
            else -> it.asClassOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class or object declaration of.
 *
 * @param kClass The Kotlin class representing the class or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to include.
 * @return A list containing declarations with the class or object declaration of the specified Kotlin class(es).
 */

@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassOrObjectDeclarationOf()"))
@JvmName("withClassOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to include.
 * @return A list containing declarations with the class or object declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassOrObjectDeclarationOf()"))
@JvmName("withClassOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class or object declaration of.
 *
 * @param kClass The Kotlin class representing the class or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to exclude.
 * @return A list containing declarations without class or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrObjectDeclarationOf()")
)
@JvmName("withoutClassOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or object declaration(s) to exclude.
 * @return A list containing declarations without class or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrObjectDeclarationOf()")
)
@JvmName("withoutClassOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class or interface declaration.
 *
 * @param predicate The predicate function to determine if a class or interface declaration satisfies a condition.
 * @return A list containing declarations with the specified class or interface declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withClassOrInterfaceDeclaration()"))
@JvmName("withClassOrInterfaceDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceDeclaration(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrInterface
            else -> it.asClassOrInterfaceDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class or interface declaration.
 *
 * @param predicate The predicate function to determine if a class or interface declaration satisfies a condition.
 * @return A list containing declarations without the specified class or interface declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceDeclaration()")
)
@JvmName("withoutClassOrInterfaceDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceDeclaration(
    predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrInterface
            else -> it.asClassOrInterfaceDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class or interface declaration of.
 *
 * @param kClass The Kotlin class representing the class or interface declaration to include.
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to include.
 * @return A list containing declarations with the class or interface declaration of the specified Kotlin class(es).
 */

@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withClassOrInterfaceDeclarationOf()")
)
@JvmName("withClassOrInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class or interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to include.
 * @return A list containing declarations with the class or interface declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withClassOrInterfaceDeclarationOf()")
)
@JvmName("withClassOrInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class or interface declaration of.
 *
 * @param kClass The Kotlin class representing the class or interface declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to exclude.
 * @return A list containing declarations without class or interface declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceDeclarationOf()")
)
@JvmName("withoutClassOrInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrInterfaceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class or interface declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class or interface declaration(s) to exclude.
 * @return A list containing declarations without class or interface declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceDeclarationOf()")
)
@JvmName("withoutClassOrInterfaceDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified interface or object declaration.
 *
 * @param predicate The predicate function to determine if a interface or object declaration satisfies a condition.
 * @return A list containing declarations with the specified interface or object declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withInterfaceOrObjectDeclaration()"))
@JvmName("withInterfaceOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceOrObjectDeclaration(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isInterfaceOrObject
            else -> it.asInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified interface or object declaration.
 *
 * @param predicate The predicate function to determine if a interface or object declaration satisfies a condition.
 * @return A list containing declarations without the specified interface or object declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutInterfaceOrObjectDeclaration()")
)
@JvmName("withoutInterfaceOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceOrObjectDeclaration(
    predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isInterfaceOrObject
            else -> it.asInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the interface or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to include.
 * @return A list containing declarations with the interface or object declaration of the specified Kotlin class(es).
 */

@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withInterfaceOrObjectDeclarationOf()")
)
@JvmName("withInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to include.
 * @return A list containing declarations with the interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withInterfaceOrObjectDeclarationOf()")
)
@JvmName("withInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the interface or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to exclude.
 * @return A list containing declarations without interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutInterfaceOrObjectDeclarationOf()")
)
@JvmName("withoutInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the interface or object declaration(s) to exclude.
 * @return A list containing declarations without interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutInterfaceOrObjectDeclarationOf()")
)
@JvmName("withoutInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified class, interface or object declaration.
 *
 * @param predicate The predicate function to determine if an class, interface or object declaration satisfies a condition.
 * @return A list containing declarations with the specified class, interface or object declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withClassOrInterfaceOrObjectDeclaration()")
)
@JvmName("withClassOrInterfaceOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceOrObjectDeclaration(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isClassOrInterfaceOrObject
            else -> it.asClassOrInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations without the specified class, interface or object declaration.
 *
 * @param predicate The predicate function to determine if a class, interface or object declaration satisfies a condition.
 * @return A list containing declarations without the specified class, interface or object declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceOrObjectDeclaration()")
)
@JvmName("withoutClassOrInterfaceOrObjectDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceOrObjectDeclaration(
    predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isClassOrInterfaceOrObject
            else -> it.asClassOrInterfaceOrObjectDeclaration()?.let { declaration -> predicate(declaration) } == true
        }
    }

/**
 * List containing declarations with class, interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the class, interface or object declaration to include.
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to include.
 * @return A list containing declarations with the class, interface or object declaration of the specified Kotlin class(es).
 */

@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withClassOrInterfaceOrObjectDeclarationOf()")
)
@JvmName("withClassOrInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withClassOrInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with class, interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to include.
 * @return A list containing declarations with the class, interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withClassOrInterfaceOrObjectDeclarationOf()")
)
@JvmName("withClassOrInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withClassOrInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without class, interface or object declaration of.
 *
 * @param kClass The Kotlin class representing the class, interface or object declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to exclude.
 * @return A list containing declarations without class, interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceOrObjectDeclarationOf()")
)
@JvmName("withoutClassOrInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceOrObjectDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutClassOrInterfaceOrObjectDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without class, interface or object declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the class, interface or object declaration(s) to exclude.
 * @return A list containing declarations without class, interface or object declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutClassOrInterfaceOrObjectDeclarationOf()")
)
@JvmName("withoutClassOrInterfaceOrObjectDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutClassOrInterfaceOrObjectDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasClassOrInterfaceOrObjectDeclaration()
            else -> kClasses.any { kClass -> it.hasClassOrInterfaceOrObjectDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations with the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations with the specified type alias declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withTypeAliasDeclaration()"))
@JvmName("withTypeAliasDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified type alias declaration.
 *
 * @param predicate The predicate function to determine if a type alias declaration satisfies a condition.
 * @return A list containing declarations without the specified type alias declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutTypeAliasDeclaration()"))
@JvmName("withoutTypeAliasDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeAlias
            else ->
                it.asTypeAliasDeclaration()?.let { typeAliasDeclaration -> predicate(typeAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations with the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations with the specified import alias declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withImportAliasDeclaration()"))
@JvmName("withImportAliasDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it
                    .asImportAliasDeclaration()
                    ?.let { importAliasDeclaration -> predicate(importAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified import alias declaration.
 *
 * @param predicate The predicate function to determine if a import alias declaration satisfies a condition.
 * @return A list containing declarations without the specified import alias declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutImportAliasDeclaration()"))
@JvmName("withoutImportAliasDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutImportAliasDeclaration(
    predicate: ((KoImportAliasDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isImportAlias
            else ->
                it
                    .asImportAliasDeclaration()
                    ?.let { importAliasDeclaration -> predicate(importAliasDeclaration) } == true
        }
    }

/**
 * List containing declarations with the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations with the specified kotlin type declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinTypeDeclaration()"))
@JvmName("withKotlinTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin type declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutKotlinTypeDeclaration()"))
@JvmName("withoutKotlinTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinType
            else ->
                it.asKotlinTypeDeclaration()?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinTypeDeclarationOf()"))
@JvmName("withKotlinTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to include.
 * @return A list containing declarations with the kotlin type declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinTypeDeclarationOf()"))
@JvmName("withKotlinTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutKotlinTypeDeclarationOf()"))
@JvmName("withoutKotlinTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin type declaration(s) to exclude.
 * @return A list containing declarations without kotlin type declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutKotlinTypeDeclarationOf()"))
@JvmName("withoutKotlinTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinBasicTypeDeclaration()"))
@JvmName("withKotlinBasicTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it
                    .asKotlinBasicTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin basic type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin basic type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin basic type declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinBasicTypeDeclaration()")
)
@JvmName("withoutKotlinBasicTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinBasicTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinBasicType
            else ->
                it
                    .asKotlinBasicTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin basic type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin basic type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to include.
 * @return A list containing declarations with the kotlin basic type declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinBasicTypeDeclarationOf()"))
@JvmName("withKotlinBasicTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinBasicTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinBasicTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin basic type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to include.
 * @return A list containing declarations with the kotlin basic type declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withKotlinBasicTypeDeclarationOf()"))
@JvmName("withKotlinBasicTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinBasicTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinBasicTypeDeclarationOf()")
)
@JvmName("withoutKotlinBasicTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinBasicTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinBasicTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin basic type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin basic type declaration(s) to exclude.
 * @return A list containing declarations without kotlin basic type declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinBasicTypeDeclarationOf()")
)
@JvmName("withoutKotlinBasicTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinBasicTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withKotlinCollectionTypeDeclaration()")
)
@JvmName("withKotlinCollectionTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it
                    .asKotlinCollectionTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified kotlin collection type declaration.
 *
 * @param predicate The predicate function to determine if a kotlin collection type declaration satisfies a condition.
 * @return A list containing declarations without the specified kotlin collection type declaration.
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinCollectionTypeDeclaration()")
)
@JvmName("withoutKotlinCollectionTypeDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinCollectionTypeDeclaration(
    predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isKotlinCollectionType
            else ->
                it
                    .asKotlinCollectionTypeDeclaration()
                    ?.let { kotlinTypeDeclaration -> predicate(kotlinTypeDeclaration) } == true
        }
    }

/**
 * List containing declarations with kotlin collection type declaration of.
 *
 * @param kClass The Kotlin class representing the kotlin collection type declaration to include.
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to include.
 * @return A list containing declarations with the kotlin collection type declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withKotlinCollectionTypeDeclarationOf()")
)
@JvmName("withKotlinCollectionTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinCollectionTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withKotlinCollectionTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with kotlin collection type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to include.
 * @return A list containing declarations with the kotlin collection type declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withKotlinCollectionTypeDeclarationOf()")
)
@JvmName("withKotlinCollectionTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withKotlinCollectionTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinCollectionTypeDeclarationOf()")
)
@JvmName("withoutKotlinCollectionTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinCollectionTypeDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutKotlinCollectionTypeDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without kotlin collection type declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the kotlin collection type declaration(s) to exclude.
 * @return A list containing declarations without kotlin collection type declaration of the specified Kotlin class(es).
 */
@Deprecated(
    "Will be removed in version 0.19.0",
    ReplaceWith("sourceDeclarations().withoutKotlinCollectionTypeDeclarationOf()")
)
@JvmName("withoutKotlinCollectionTypeDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutKotlinCollectionTypeDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withTypeParameterDeclaration()"))
@JvmName("withTypeParameterDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filter {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { typeParameter -> predicate(typeParameter) } == true
        }
    }

/**
 * List containing declarations without the specified type parameter declaration.
 *
 * @param predicate The predicate function to determine if a type parameter declaration satisfies a condition.
 * @return A list containing declarations without the specified type parameter declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutTypeParameterDeclaration()"))
@JvmName("withoutTypeParameterDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutTypeParameterDeclaration(
    predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null,
): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isTypeParameter
            else ->
                it
                    .asTypeParameterDeclaration()
                    ?.let { typeParameter -> predicate(typeParameter) } == true
        }
    }

/**
 * List containing declarations with the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations with the specified external declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withExternalDeclaration()"))
@JvmName("withExternalDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations without the specified external declaration.
 *
 * @param predicate The predicate function to determine if an external declaration satisfies a condition.
 * @return A list containing declarations without the specified external declaration.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutExternalDeclaration()"))
@JvmName("withoutExternalDeclarationForType")
fun <T : KoTypeDeclaration> List<T>.withoutExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.isExternal
            else ->
                it
                    .asExternalDeclaration()
                    ?.let { externalDeclaration -> predicate(externalDeclaration) } == true
        }
    }

/**
 * List containing declarations with external declaration of.
 *
 * @param kClass The Kotlin class representing the external declaration to include.
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withExternalDeclarationOf()"))
@JvmName("withExternalDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withExternalDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withExternalDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to include.
 * @return A list containing declarations with the external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withExternalDeclarationOf()"))
@JvmName("withExternalDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withExternalDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutExternalDeclarationOf()"))
@JvmName("withoutExternalDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutExternalDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutExternalDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without external declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the external declaration(s) to exclude.
 * @return A list containing declarations without external declaration of the specified Kotlin class(es).
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclarations().withoutExternalDeclarationOf()"))
@JvmName("withoutExternalDeclarationOfForType")
fun <T : KoTypeDeclaration> List<T>.withoutExternalDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasExternalDeclaration()
            else -> kClasses.any { kClass -> it.hasExternalDeclarationOf(kClass) }
        }
    }
