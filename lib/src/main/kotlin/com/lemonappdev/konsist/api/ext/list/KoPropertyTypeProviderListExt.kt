package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider
import kotlin.reflect.KClass

/**
 * List containing type declarations.
 */
val <T : KoPropertyTypeProvider> List<T>.types: List<KoTypeDeclaration>
    get() = mapNotNull { it.type }

/**
 * List containing elements with type.
 *
 * @param names The type name(s) to include.
 * @return A list containing elements with the specified type (or any type if [names] is empty).
 */
fun <T : KoPropertyTypeProvider> List<T>.withType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasType()
        else -> names.any { type -> it.hasType(type) }
    }
}

/**
 * List containing elements without type.
 *
 * @param names The type name(s) to exclude.
 * @return A list containing elements without specified type (or none type if [names] is empty).
 */
fun <T : KoPropertyTypeProvider> List<T>.withoutType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasType()
        else -> names.none { type -> it.hasType(type) }
    }
}

/**
 * List containing elements with type of.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing elements with the type of the specified Kotlin class(es).
 */
fun <T : KoPropertyTypeProvider> List<T>.withTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.type?.name == kClass.simpleName ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.type?.name == kClass.simpleName }
            } else {
                false
            }
    }

/**
 * List containing elements without type of.
 *
 * @param kClass The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing elements without type of the specified Kotlin class(es).
 */
fun <T : KoPropertyTypeProvider> List<T>.withoutTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.type?.name != kClass.simpleName &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.type?.name == kClass.simpleName }
            } else {
                true
            }
    }
