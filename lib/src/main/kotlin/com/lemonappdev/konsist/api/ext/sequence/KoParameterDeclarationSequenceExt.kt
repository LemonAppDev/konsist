package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.ext.provider.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all parameters with `vararg` modifier.
 *
 * @return A sequence containing parameters with the `vararg` modifier.
 */
fun Sequence<KoParameterDeclaration>.withVarargModifier(): Sequence<KoParameterDeclaration> = filter { it.hasVarargModifier() }

/**
 * Sequence containing all parameters without `vararg` modifier.
 *
 * @return A sequence containing parameters without the `vararg` modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutVarargModifier(): Sequence<KoParameterDeclaration> = filterNot { it.hasVarargModifier() }

/**
 * Sequence containing all parameters with `noinline` modifier.
 *
 * @return A sequence containing parameters with the `noinline` modifier.
 */
fun Sequence<KoParameterDeclaration>.withNoInlineModifier(): Sequence<KoParameterDeclaration> = filter { it.hasNoInlineModifier() }

/**
 * Sequence containing all parameters without `noinline` modifier.
 *
 * @return A sequence containing parameters without the `noinline` modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutNoInlineModifier(): Sequence<KoParameterDeclaration> = filterNot { it.hasNoInlineModifier() }

/**
 * Sequence containing all parameters with `crossinline` modifier.
 *
 * @return A sequence containing parameters with the `crossinline` modifier.
 */
fun Sequence<KoParameterDeclaration>.withCrossInlineModifier(): Sequence<KoParameterDeclaration> = filter { it.hasCrossInlineModifier() }

/**
 * Sequence containing all parameters without `crossinline` modifier.
 *
 * @return A sequence containing parameters without the `crossinline` modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutCrossInlineModifier(): Sequence<KoParameterDeclaration> =
    filterNot { it.hasCrossInlineModifier() }


/**
 * Sequence containing all parameters with type.
 *
 *  @param type The type to include.
 * @param types The types to include.
 * @return A sequence containing parameters with the specified types.
 */
fun Sequence<KoParameterDeclaration>.withRepresentedType(type: String, vararg types: String): Sequence<KoParameterDeclaration> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all parameters without type.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A sequence containing parameters without the specified types.
 */
fun Sequence<KoParameterDeclaration>.withoutRepresentedType(type: String, vararg types: String): Sequence<KoParameterDeclaration> = filter {
    !it.representsType(type) && types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all parameters with type of.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing parameters with types matching the specified Kotlin classes.
 */
fun Sequence<KoParameterDeclaration>.withRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoParameterDeclaration> = filter {
    types.any { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all parameters without type of.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing parameters without types matching the specified Kotlin classes.
 */
fun Sequence<KoParameterDeclaration>.withoutRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoParameterDeclaration> = filter {
    types.none { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all parameters with type of.
 *
 * @return A sequence containing parameters with types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withRepresentedTypeOf(): Sequence<KoParameterDeclaration> = filter {
    it.representsTypeOf<T>()
}

/**
 * Sequence containing all parameters without type of.
 *
 * @return A sequence containing parameters without types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withoutRepresentedTypeOf(): Sequence<KoParameterDeclaration> =
    filterNot { it.representsTypeOf<T>() }
