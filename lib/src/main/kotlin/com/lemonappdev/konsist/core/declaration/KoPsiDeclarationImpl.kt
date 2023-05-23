package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.LocationHelper
import com.lemonappdev.konsist.core.util.TagHelper
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

@Suppress("detekt.TooManyFunctions")
internal open class KoPsiDeclarationImpl(private val psiElement: PsiElement) : KoPsiDeclaration {
    override val filePath by lazy {
        psiElement
            .containingFile
            .name
    }

    override val projectFilePath by lazy {
        val rootPathProvider = PathProvider
            .getInstance()
            .rootProjectPath

        filePath.removePrefix(rootPathProvider)
    }

    override val location by lazy {
        val lineAndColumn = psiElement
            .getTextWithLocation()
            .substringAfterLast("' at (")
            .substringBefore(") in")
            .split(",")
            .toMutableList()
            .filterNot { it.isBlank() }

        val line = lineAndColumn[0]
        val column = lineAndColumn[1]
        "$filePath:$line:$column"
    }

    override val text by lazy {
        psiElement
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    override val locationWithText by lazy { "Location: $location \nDeclaration:\n$text" }

    override val kDoc by lazy {
        val kDocElement = psiElement
            .children
            .filterIsInstance<KDocElement>()
            .firstOrNull()

        kDocElement?.let { KoKDocDeclarationImpl(kDocElement) }
    }

    override fun hasKDoc() = kDoc != null

    protected open fun hasValidDescription(enabled: Boolean) = TagHelper.hasValidDescription(enabled, kDoc)

    protected open fun hasValidParamTag(enabled: Boolean) = TagHelper.hasValidParamTag(enabled, parameters = null, kDoc)

    protected open fun hasValidReturnTag(enabled: Boolean) = TagHelper.hasValidReturnTag(enabled, null, kDoc)

    protected open fun hasValidConstructorTag(enabled: Boolean) = TagHelper.hasValidConstructorTag(enabled, kDoc)

    protected open fun hasValidReceiverTag(enabled: Boolean) = TagHelper.hasValidReceiverTag(enabled, kDoc)

    protected open fun hasValidPropertyTag(enabled: Boolean) = TagHelper.hasValidPropertyTag(enabled, properties = null, kDoc)

    protected open fun hasValidThrowsTag(enabled: Boolean) = TagHelper.hasValidThrowsTag(enabled, kDoc)

    protected open fun hasValidExceptionTag(enabled: Boolean) = TagHelper.hasValidExceptionTag(enabled, kDoc)

    protected open fun hasValidSampleTag(enabled: Boolean) = TagHelper.hasValidSampleTag(enabled, kDoc)

    protected open fun hasValidSeeTag(enabled: Boolean) = TagHelper.hasValidSeeTag(enabled, kDoc)

    protected open fun hasValidAuthorTag(enabled: Boolean) = TagHelper.hasValidAuthorTag(enabled, kDoc)

    protected open fun hasValidSinceTag(enabled: Boolean) = TagHelper.hasValidSinceTag(enabled, kDoc)

    protected open fun hasValidSuppressTag(enabled: Boolean) = TagHelper.hasValidSuppressTag(enabled, kDoc)

    protected open fun hasValidVersionTag(enabled: Boolean) = TagHelper.hasValidVersionTag(enabled, kDoc)

    protected open fun hasValidPropertySetterTag(enabled: Boolean) = TagHelper.hasValidPropertySetterTag(enabled, kDoc)

    protected open fun hasValidPropertyGetterTag(enabled: Boolean) = TagHelper.hasValidPropertyGetterTag(enabled, kDoc)

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
    ) = hasKDoc() &&
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

    override fun resideInFilePath(path: String) = LocationHelper.resideInLocation(path, filePath)

    override fun resideInProjectFilePath(path: String) = LocationHelper.resideInLocation(path, projectFilePath)

    override fun print() {
        print(toString())
    }

    override fun toString() = locationWithText
}
