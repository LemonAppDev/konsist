package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider

/**
 * Returns `true` if declaration represents the type of [T].
 *
 * Due to type erasure, avoid passing generics as type arguments because they won't be accurately represented at runtime.
 * For generics use representsType method instead and pass the string representing the type:
 * No: representsTypeOf<List<String>>()
 * Yes: representsType("List<String>")
 *
 * @return `true` if declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoRepresentsTypeProvider.representsTypeOf(): Boolean =
    representsType(T::class.simpleName) || representsType(T::class.qualifiedName)
