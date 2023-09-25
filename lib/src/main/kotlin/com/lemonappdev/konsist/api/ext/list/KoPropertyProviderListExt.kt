package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * List containing property declarations.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing property declarations.
 */
fun <T : KoPropertyProvider> List<T>.properties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoPropertyDeclaration> = flatMap { it.properties(includeNested, includeLocal) }

/**
 * List containing declarations with any property.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations with any property.
 */
fun <T : KoPropertyProvider> List<T>.withProperties(includeNested: Boolean = true, includeLocal: Boolean = true): List<T> =
    filter { it.hasProperties(includeNested, includeLocal) }

/**
 * List containing declarations with no properties.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations with no properties.
 */
fun <T : KoPropertyProvider> List<T>.withoutProperties(includeNested: Boolean = true, includeLocal: Boolean = true): List<T> =
    filterNot { it.hasProperties(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one property with the specified name(s).
 *
 * @param name The name of the property to include.
 * @param names The names of additional properties to include.
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations with at least one of the specified property(s).
 */
fun <T : KoPropertyProvider> List<T>.withPropertyNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter {
    it.hasPropertyWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations without any of specified properties.
 *
 * @param name The name of the property to exclude.
 * @param names The names of additional properties to exclude.
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations without any of specified properties.
 */
fun <T : KoPropertyProvider> List<T>.withoutPropertyNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot {
    it.hasPropertyWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations that have all specified properties.
 *
 * @param name The name of the property to include.
 * @param names The name(s) of the property(s) to include.
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations with all specified property(s).
 */
fun <T : KoPropertyProvider> List<T>.withAllPropertiesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter {
    it.hasPropertiesWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations without all specified properties.
 *
 * @param name The name of the property to exclude.
 * @param names The name(s) of the property(s) to exclude.
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @return A list containing declarations without all specified property(s).
 */
fun <T : KoPropertyProvider> List<T>.withoutAllPropertiesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        it.hasPropertiesWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations that have at least one property satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by a property declaration.
 * @return A list containing declarations with at least one property satisfying the predicate.
 */
fun <T : KoPropertyProvider> List<T>.withProperty(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoPropertyDeclaration) -> Boolean,
): List<T> = filter {
    it.hasProperty(includeNested, includeLocal, predicate)
}

/**
 * List containing declarations that not have property satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by a property declaration.
 * @return A list containing declarations without property satisfying the provided predicate.
 */
fun <T : KoPropertyProvider> List<T>.withoutProperty(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoPropertyDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasProperty(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all properties satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by all property declarations.
 * @return A filtered list containing declarations with all properties satisfying the predicate.
 */
fun <T : KoPropertyProvider> List<T>.withAllProperties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoPropertyDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllProperties(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one property not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by all property declarations.
 * @return A list containing declarations that have at least one property not satisfying the provided predicate.
 */
fun <T : KoPropertyProvider> List<T>.withoutAllProperties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoPropertyDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasAllProperties(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with property declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by the list of property declarations.
 * @return A list containing declarations with property declarations satisfying the predicate.
 */
fun <T : KoPropertyProvider> List<T>.withProperties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoPropertyDeclaration>) -> Boolean,
): List<T> =
    filter { predicate(it.properties(includeNested, includeLocal)) }

/**
 * List containing declarations without property declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested properties.
 * @param includeLocal Whether to include local properties.
 * @param predicate A function that defines the condition to be met by the list of property declarations.
 * @return A list containing declarations without property declarations satisfying the predicate.
 */
fun <T : KoPropertyProvider> List<T>.withoutProperties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoPropertyDeclaration>) -> Boolean,
): List<T> =
    filterNot { predicate(it.properties(includeNested, includeLocal)) }
