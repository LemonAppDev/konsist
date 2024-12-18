package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration

/**
 * List containing local declarations.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("declarations()"))
val <T : KoEnumConstantDeclaration> List<T>.localDeclarations: List<KoBaseDeclaration>
    get() = flatMap { it.localDeclarations }

/**
 * List containing declarations with any local declaration.
 *
 * @return A list containing declarations with any local declaration.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withLocalDeclarations(): List<T> = filter { it.hasLocalDeclarations() }

/**
 * List containing declarations with no local declarations.
 *
 * @return A list containing declarations with no local declarations.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withoutDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withoutLocalDeclarations(): List<T> = filterNot { it.hasLocalDeclarations() }

/**
 * List containing declarations that have at least one local declaration satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local declaration.
 * @return A list containing declarations with at least one local declaration satisfying the predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withDeclaration()"))
fun <T : KoEnumConstantDeclaration> List<T>.withLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filter {
        it.hasLocalDeclaration(predicate)
    }

/**
 * List containing declarations that not have local declaration satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local declaration.
 * @return A list containing declarations without local declaration satisfying the provided predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withoutDeclaration()"))
fun <T : KoEnumConstantDeclaration> List<T>.withoutLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filterNot { it.hasLocalDeclaration(predicate) }

/**
 * List containing declarations that have all local declarations satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local declarations.
 * @return A filtered list containing declarations with all local declarations satisfying the predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withAllDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllLocalDeclarations(predicate)
    }

/**
 * List containing declarations that have at least one local declaration not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local declarations.
 * @return A list containing declarations that have at least one local declaration not satisfying the provided predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withoutAllDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withoutAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllLocalDeclarations(predicate) }

/**
 * List containing declarations with local declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local declarations.
 * @return A list containing declarations with local declarations satisfying the predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withLocalDeclarations(predicate: (List<KoBaseDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.localDeclarations) }

/**
 * List containing declarations without local declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local declarations.
 * @return A list containing declarations without local declarations satisfying the predicate.
 */
@Deprecated("Will be removed in version 0.20.0", ReplaceWith("withoutDeclarations()"))
fun <T : KoEnumConstantDeclaration> List<T>.withoutLocalDeclarations(predicate: (List<KoBaseDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.localDeclarations) }
