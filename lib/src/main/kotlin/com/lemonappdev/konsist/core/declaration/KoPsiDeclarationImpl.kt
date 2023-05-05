package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration
import com.lemonappdev.konsist.core.util.LocationHelper
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import java.io.File

internal open class KoPsiDeclarationImpl(private val psiElement: PsiElement) : KoPsiDeclaration {
    override val filePath by lazy {
        psiElement
            .containingFile
            .name
    }

    override val projectFilePath by lazy {
        val mainPath = File("")
            .absoluteFile
            .path
            .substringBeforeLast('/')

        filePath.removePrefix(mainPath)
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

    override fun resideInFilePath(text: String) = LocationHelper.resideInLocation(text, filePath)

    override fun resideInProjectFilePath(text: String) = LocationHelper.resideInLocation(text, projectFilePath)

    override fun print() {
        print(toString())
    }

    override fun toString() = locationWithText
}
