package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationImpl
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.kdoc.psi.api.KDoc

internal interface KoKDocProviderCore : KoKDocProvider, KoTextProviderCore, KoBaseProviderCore {
    override val kDoc: KoKDocDeclaration?
        get() {
            val kDocElement = psiElement
                .children
                .filterIsInstance<KDoc>()
                .firstOrNull()

            return kDocElement?.let { KoKDocDeclarationImpl(kDocElement) }
        }

    fun hasValidDescription(enabled: Boolean): Boolean = TagUtil.hasValidDescription(enabled, kDoc)

    fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters = null, kDoc)

    fun hasValidReturnTag(enabled: Boolean): Boolean = TagUtil.hasValidReturnTag(enabled, type = null, kDoc)

    fun hasValidConstructorTag(enabled: Boolean): Boolean = TagUtil.hasValidConstructorTag(enabled, kDoc)

    fun hasValidReceiverTag(enabled: Boolean): Boolean = TagUtil.hasValidReceiverTag(enabled, kDoc)

    fun hasValidPropertyTag(enabled: Boolean): Boolean = TagUtil.hasValidPropertyTag(enabled, properties = null, kDoc)

    fun hasValidThrowsTag(enabled: Boolean): Boolean = TagUtil.hasValidThrowsTag(enabled, kDoc)

    fun hasValidExceptionTag(enabled: Boolean): Boolean = TagUtil.hasValidExceptionTag(enabled, kDoc)

    fun hasValidSampleTag(enabled: Boolean): Boolean = TagUtil.hasValidSampleTag(enabled, kDoc)

    fun hasValidSeeTag(enabled: Boolean): Boolean = TagUtil.hasValidSeeTag(enabled, kDoc)

    fun hasValidAuthorTag(enabled: Boolean): Boolean = TagUtil.hasValidAuthorTag(enabled, kDoc)

    fun hasValidSinceTag(enabled: Boolean): Boolean = TagUtil.hasValidSinceTag(enabled, kDoc)

    fun hasValidSuppressTag(enabled: Boolean): Boolean = TagUtil.hasValidSuppressTag(enabled, kDoc)

    fun hasValidVersionTag(enabled: Boolean): Boolean = TagUtil.hasValidVersionTag(enabled, kDoc)

    fun hasValidPropertySetterTag(enabled: Boolean): Boolean = TagUtil.hasValidPropertySetterTag(enabled, kDoc)

    fun hasValidPropertyGetterTag(enabled: Boolean): Boolean = TagUtil.hasValidPropertyGetterTag(enabled, kDoc)

    @Suppress("detekt.CyclomaticComplexMethod")
    override fun hasValidKDoc(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        verifyConstructorTag: Boolean,
        verifyReceiverTag: Boolean,
        verifyPropertyTag: Boolean,
        verifyThrowsTag: Boolean,
        verifyExceptionTag: Boolean,
        verifySampleTag: Boolean,
        verifySeeTag: Boolean,
        verifyAuthorTag: Boolean,
        verifySinceTag: Boolean,
        verifySuppressTag: Boolean,
        verifyVersionTag: Boolean,
        verifyPropertySetterTag: Boolean,
        verifyPropertyGetterTag: Boolean,
    ): Boolean = hasKDoc() &&
        hasValidDescription(verifyDescription) &&
        hasValidParamTag(verifyParamTag) &&
        hasValidReturnTag(verifyReturnTag) &&
        hasValidConstructorTag(verifyConstructorTag) &&
        hasValidReceiverTag(verifyReceiverTag) &&
        hasValidPropertyTag(verifyPropertyTag) &&
        hasValidThrowsTag(verifyThrowsTag) &&
        hasValidExceptionTag(verifyExceptionTag) &&
        hasValidSampleTag(verifySampleTag) &&
        hasValidSeeTag(verifySeeTag) &&
        hasValidAuthorTag(verifyAuthorTag) &&
        hasValidSinceTag(verifySinceTag) &&
        hasValidSuppressTag(verifySuppressTag) &&
        hasValidVersionTag(verifyVersionTag) &&
        hasValidPropertySetterTag(verifyPropertySetterTag) &&
        hasValidPropertyGetterTag(verifyPropertyGetterTag)

    override fun hasKDoc(): Boolean = kDoc != null
}
