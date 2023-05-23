package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

object TagHelper {
    fun hasValidDescription(verifyDescription: Boolean, kDoc: KoKDocDeclaration?) = if (verifyDescription) {
        kDoc?.description?.isNotBlank() ?: false
    } else {
        true
    }

    fun hasValidParamTag(verifyParamTag: Boolean, parameters: List<KoParameterDeclaration>?, kDoc: KoKDocDeclaration?) =
        if (verifyParamTag && parameters != null) {
            val kDocParameters = kDoc?.paramTags?.map { it.value }?.sorted()
            val givenParameters = parameters.map { it.name }.sorted()

            givenParameters == kDocParameters
        } else {
            true
        }

    fun hasValidReturnTag(verifyReturnTag: Boolean, type: String?, kDoc: KoKDocDeclaration?) = if (verifyReturnTag && type != "Unit") {
        kDoc?.returnTag != null
    } else {
        true
    }
}
