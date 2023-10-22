package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReturnProvider

/**
 * Determines whatever declaration has return type.
 *
 * @return `true` if the declaration has return type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoReturnProvider.hasReturnTypeOf(): Boolean = hasReturnTypeOf(T::class)

/**
 * Determines whatever declaration has a valid KDoc with a RETURN tag.
 *
 * @return `true` if the declaration has a valid KDoc with the RETURN tag, `false` otherwise.
 */
fun <T : KoReturnProvider> T.hasValidKDocReturnTag(): Boolean =
    if (returnType != null && returnType?.name != "Unit") {
        (this as? KoKDocProvider)?.kDoc?.hasTags(KoKDocTag.RETURN) == true
    } else {
        (this as? KoKDocProvider)?.kDoc?.returnTag == null
    }
