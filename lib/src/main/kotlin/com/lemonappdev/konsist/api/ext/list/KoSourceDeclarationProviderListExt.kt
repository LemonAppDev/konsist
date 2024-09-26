package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import kotlin.reflect.KClass

/**
 * List of source declarations for each declaration in the list.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceDeclarations: List<KoBaseDeclaration>
    get() = map { it.sourceDeclaration }

fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filter { predicate(it.sourceDeclaration) }

fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.sourceDeclaration) }

fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withSourceDeclarationOf(listOf(kClass, *kClasses))

fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }

fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutSourceDeclarationOf(listOf(kClass, *kClasses))

fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }
