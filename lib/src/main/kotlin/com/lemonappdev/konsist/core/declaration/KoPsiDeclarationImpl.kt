package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration
import com.lemonappdev.konsist.core.declaration.taghelper.TagHelper
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.LocationHelper
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

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

    protected open fun verifyDescription(value: Boolean) = TagHelper.verifyDescription(value, kDoc)

    protected open fun verifyParamTag(value: Boolean) = true

    protected open fun verifyReturnTag(value: Boolean) = true

    protected open fun verifyConstructorTag(value: Boolean) = true

    protected open fun verifyReceiverTag(value: Boolean) = true

    protected open fun verifyPropertyTag(value: Boolean) = true

    protected open fun verifyThrowsTag(value: Boolean) = true

    protected open fun verifyExceptionTag(value: Boolean) = true

    protected open fun verifySampleTag(value: Boolean) = true

    protected open fun verifySeeTag(value: Boolean) = true

    protected open fun verifyAuthorTag(value: Boolean) = true

    protected open fun verifySinceTag(value: Boolean) = true

    protected open fun verifySuppressTag(value: Boolean) = true

    protected open fun verifyVersionTag(value: Boolean) = true

    protected open fun verifyPropertySetterTag(value: Boolean) = true

    protected open fun verifyPropertyGetterTag(value: Boolean) = true

    override fun hasCompleteKDoc(
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
    ): Boolean {
        val descriptionValue = verifyDescription(verifyDescription)
        val paramValue = verifyParamTag(verifyParamTag)
        val returnValue = verifyReturnTag(verifyReturnTag)
        val constructorValue = verifyConstructorTag(verifyConstructorTag)
        val receiverValue = verifyReceiverTag(verifyReceiverTag)
        val propertyValue = verifyPropertyTag(verifyPropertyTag)
        val throwsValue = verifyThrowsTag(verifyThrowsTag)
        val exceptionValue = verifyExceptionTag(verifyExceptionTag)
        val sampleValue = verifySampleTag(verifySampleTag)
        val seeValue = verifySeeTag(verifySeeTag)
        val authorValue = verifyAuthorTag(verifyAuthorTag)
        val sinceValue = verifySinceTag(verifySinceTag)
        val suppressValue = verifySuppressTag(verifySuppressTag)
        val versionValue = verifyVersionTag(verifyVersionTag)
        val propertySetterValue = verifyPropertySetterTag(verifyPropertySetterTag)
        val propertyGetterValue = verifyPropertyGetterTag(verifyPropertyGetterTag)

        return hasKDoc() && descriptionValue && paramValue && returnValue && constructorValue && receiverValue && propertyValue &&
                throwsValue && exceptionValue && sampleValue && seeValue && authorValue && sinceValue && suppressValue && versionValue
                && propertySetterValue && propertyGetterValue
    }

    override fun resideInFilePath(path: String) = LocationHelper.resideInLocation(path, filePath)

    override fun resideInProjectFilePath(path: String) = LocationHelper.resideInLocation(path, projectFilePath)

    override fun print() {
        print(toString())
    }

    override fun toString() = locationWithText
}
