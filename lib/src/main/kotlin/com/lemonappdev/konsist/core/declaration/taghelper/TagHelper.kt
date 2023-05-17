package com.lemonappdev.konsist.core.declaration.taghelper

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

object TagHelper {
    fun verifyDescription(verifyDescription: Boolean, kDoc: KoKDocDeclaration?) = if (verifyDescription) {
        kDoc?.description?.isNotBlank() ?: false
    } else {
        true
    }

    fun verifyParamTag(verifyParamTag: Boolean, parameters: List<KoParameterDeclaration>?, kDoc: KoKDocDeclaration?) =
        if (verifyParamTag && parameters != null) {
            parameters
                .all {
                    kDoc
                        ?.paramTags
                        ?.any { tag -> tag.value == it.name }
                        ?: false
                }
        } else {
            true
        }

    fun verifyReturnTag(verifyReturnTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyReturnTag) {
        kDoc?.returnTag != null
    } else {
        true
    }
}
