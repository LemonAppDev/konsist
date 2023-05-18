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

    protected open fun hasValidParamTag(enabled: Boolean) = true

    protected open fun hasValidReturnTag(enabled: Boolean) = true

    protected open fun hasValidConstructorTag(enabled: Boolean) = true

    protected open fun hasValidReceiverTag(enabled: Boolean) = true

    protected open fun hasValidPropertyTag(enabled: Boolean) = true

    protected open fun hasValidThrowsTag(enabled: Boolean) = true

    protected open fun hasValidExceptionTag(enabled: Boolean) = true

    protected open fun hasValidSampleTag(enabled: Boolean) = true

    protected open fun hasValidSeeTag(enabled: Boolean) = true

    protected open fun hasValidAuthorTag(enabled: Boolean) = true

    protected open fun hasValidSinceTag(enabled: Boolean) = true

    protected open fun hasValidSuppressTag(enabled: Boolean) = true

    protected open fun hasValidVersionTag(enabled: Boolean) = true

    protected open fun hasValidPropertySetterTag(enabled: Boolean) = true

    protected open fun hasValidPropertyGetterTag(enabled: Boolean) = true

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
