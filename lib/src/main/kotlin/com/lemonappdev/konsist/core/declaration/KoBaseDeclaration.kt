package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import java.io.File

/**
 * Base declaration
 */
open class KoBaseDeclaration(private val ktElement: KtElement) {
    /**
     * File path of the declaration
     */
    val path by lazy {
        ktElement
            .containingKtFile
            .virtualFilePath
            .replace("//", "/")
    }

    val projectPath by lazy {
        val mainPath = File("")
            .absoluteFile
            .path
            .substringBeforeLast('/')

        path.removePrefix(mainPath)
    }

    /**
     * KoFile containing the declaration
     */
    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }

    /**
     * Location of the declaration containing the file path, line and column
     */
    val location by lazy {
        val lineAndColumn = ktElement
            .getTextWithLocation()
            .substringAfterLast("' at (")
            .substringBefore(") in")
            .split(",")
            .toMutableList()
            .also { it.removeIf { string -> string.isBlank() } }

        val line = lineAndColumn[0]
        val column = lineAndColumn[1]
        "$path:$line:$column"
    }

    /**
     * Text of the declaration
     */
    val text by lazy {
        ktElement
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    val textWithLocation by lazy { "Location: $location \nDeclaration:\n$text" }

    fun hasFilePath(text: String) = PackageHelper.resideInPackage(text, path, '/')

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    override fun toString() = textWithLocation
}
