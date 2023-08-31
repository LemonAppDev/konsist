package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider

/**
 * Whether declaration has a valid KDoc with a RETURN tag.
 *
 * @return `true` if the declaration has a valid KDoc with the RETURN tag, `false` otherwise.
 */
fun <T : KoReturnTypeProvider> T.hasValidKDocReturnTag(): Boolean =
    if (returnType != null && returnType?.name != "Unit") {
        (this as? KoKDocProvider)?.kDoc?.hasTags(KoKDocTag.RETURN) == true
    } else {
        (this as? KoKDocProvider)?.kDoc?.returnTag == null
    }
