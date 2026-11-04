package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import kotlin.reflect.typeOf

/**
 * Determines whether the declaration has a return type of type [T], including its type arguments.
 * For example, `List<String>` matches `hasReturnTypeOf<List<String>>()`, but not `hasReturnTypeOf<List<Int>>()`.
 *
 * @return `true` if the declaration has a return type matching [T], `false` otherwise.
 */
inline fun <reified T> KoReturnProvider.hasReturnTypeOf(): Boolean = hasReturnTypeOf(typeOf<T>())

/**
 * Determines whether the declaration has a valid KDoc with a RETURN tag.
 * A declaration with an explicitly declared non-`Unit` return type must have a RETURN tag. Otherwise, it must not have one.
 *
 * @return `true` if the declaration has a valid KDoc with the RETURN tag, `false` otherwise.
 */
fun <T : KoReturnProvider> T.hasValidKDocReturnTag(): Boolean =
    if (returnType != null && returnType?.name != "Unit") {
        (this as? KoKDocProvider)?.kDoc?.hasTag(KoKDocTag.RETURN) == true
    } else {
        (this as? KoKDocProvider)?.kDoc?.returnTag == null
    }
