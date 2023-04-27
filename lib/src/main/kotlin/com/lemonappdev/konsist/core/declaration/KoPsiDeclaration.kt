package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import java.io.File

open class KoPsiDeclaration(private val psiElement: PsiElement) {
    /**
     * File path of the declaration
     */
    val filePath by lazy {
        psiElement
            .containingFile
            .name
    }

    val projectFilePath by lazy {
        val mainPath = File("")
            .absoluteFile
            .path
            .substringBeforeLast('/')

        filePath.removePrefix(mainPath)
    }

    /**
     * Location of the declaration containing the file path, line and column
     */
    val location by lazy {
        val lineAndColumn = psiElement
            .getTextWithLocation()
            .substringAfterLast("' at (")
            .substringBefore(") in")
            .split(",")
            .toMutableList()
            .also { it.removeIf { string -> string.isBlank() } }

        val line = lineAndColumn[0]
        val column = lineAndColumn[1]
        "$filePath:$line:$column"
    }

    /**
     * Text of the declaration
     */
    open val text by lazy {
        psiElement
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    val locationWithText by lazy { "Location: $location \nDeclaration:\n$text" }

    fun resideInFilePath(text: String) = PackageHelper.resideInPackage(text, filePath, '/')

    fun resideInProjectFilePath(text: String) = PackageHelper.resideInPackage(text, projectFilePath, '/')

    fun print() {
        print(toString())
    }

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    override fun toString() = locationWithText
}
