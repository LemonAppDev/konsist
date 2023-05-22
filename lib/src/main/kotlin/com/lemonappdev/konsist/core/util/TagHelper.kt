package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration

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

    fun hasValidReturnTag(verifyReturnTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyReturnTag) {
        kDoc?.returnTag != null
    } else {
        true
    }

    fun hasValidConstructorTag(verifyConstructorTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyConstructorTag) {
        kDoc?.constructorTag != null
    } else {
        true
    }

    fun hasValidReceiverTag(verifyReceiverTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyReceiverTag) {
        kDoc?.receiverTag != null
    } else {
        true
    }

    fun hasValidPropertyTag(verifyPropertyTag: Boolean, properties: List<KoPropertyDeclaration>?, kDoc: KoKDocDeclaration?) =
        if (verifyPropertyTag && properties != null) {
            val kDocProperties = kDoc?.propertyTags?.map { it.value }?.sorted()
            val givenProperties = properties.map { it.name }.sorted()

            givenProperties == kDocProperties
        } else {
            true
        }

    fun hasValidThrowsTag(verifyThrowsTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyThrowsTag) {
        kDoc?.throwsTags != emptyList<KoValuedKDocTagDeclaration>()
    } else {
        true
    }

    fun hasValidExceptionTag(verifyExceptionTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyExceptionTag) {
        kDoc?.exceptionTags != emptyList<KoValuedKDocTagDeclaration>()
    } else {
        true
    }

    fun hasValidSampleTag(verifySampleTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifySampleTag) {
        kDoc?.sampleTags != emptyList<KoValuedKDocTagDeclaration>()
    } else {
        true
    }

    fun hasValidSeeTag(verifySeeTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifySeeTag) {
        kDoc?.seeTags != emptyList<KoValuedKDocTagDeclaration>()
    } else {
        true
    }

    fun hasValidAuthorTag(verifyAuthorTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyAuthorTag) {
        kDoc?.authorTags != emptyList<KoKDocTagDeclaration>()
    } else {
        true
    }

    fun hasValidSinceTag(verifySinceTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifySinceTag) {
        kDoc?.sinceTag != null
    } else {
        true
    }

    fun hasValidSuppressTag(verifySuppressTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifySuppressTag) {
        kDoc?.suppressTag != null
    } else {
        true
    }

    fun hasValidVersionTag(verifyVersionTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyVersionTag) {
        kDoc?.versionTag != null
    } else {
        true
    }

    fun hasValidPropertySetterTag(verifyPropertySetterTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyPropertySetterTag) {
        kDoc?.propertySetterTag != null
    } else {
        true
    }

    fun hasValidPropertyGetterTag(verifyPropertyGetterTag: Boolean, kDoc: KoKDocDeclaration?) = if (verifyPropertyGetterTag) {
        kDoc?.propertyGetterTag != null
    } else {
        true
    }
}
