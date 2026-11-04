package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider

/**
 * Returns `true` if the declaration represents the type of [T].
 *
 * Due to type erasure, avoid passing generics as type arguments because they won't be accurately represented at runtime.
 * For generics use representsType method instead and pass the string representing the type:
 * No: representsTypeOf<List<String>>()
 * Yes: representsType("List<String>")
 *
 * Types are matched by fully qualified name, so a type with the same simple name declared in another package does not match.
 *
 * @return `true` if the declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoRepresentsTypeProvider.representsTypeOf(): Boolean = representsType(T::class.qualifiedName)
