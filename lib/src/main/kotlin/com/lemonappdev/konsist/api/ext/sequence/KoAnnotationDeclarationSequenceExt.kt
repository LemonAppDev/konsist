package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that represents the type.
 *
 * @param types The types to include.
 * @return A sequence containing annotation declarations with the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @param types The types to exclude.
 * @return A sequence containing annotation declarations without the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withoutType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that represents the type.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing annotation declarations with types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing annotation declarations without types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withoutTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all declarations that represents the type.
 *
 * @return A sequence containing annotation declarations with types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withTypeOf(): Sequence<KoAnnotationDeclaration> =
    filter { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @return A sequence containing annotation declarations without types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withoutTypeOf(): Sequence<KoAnnotationDeclaration> =
    filterNot { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that have the name.
 *
 * @param names The names to include.
 * @return A sequence containing annotation declarations with the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing all declarations that don't have the name.
 *
 * @param names The names to exclude.
 * @return A sequence containing annotation declarations without the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing all declarations that have the fully qualified name.
 *
 * @param names The names to include.
 * @return A sequence containing annotation declarations with the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * Sequence containing all declarations that don't have the fully qualified name.
 *
 * @param names The names to exclude.
 * @return A sequence containing annotation declarations without the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
