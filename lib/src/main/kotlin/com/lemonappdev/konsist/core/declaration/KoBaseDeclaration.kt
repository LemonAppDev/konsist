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
    val filePath by lazy {
        ktElement
            .containingKtFile
            .virtualFilePath
            .replace("//", "/")
    }

    val projectFilePath by lazy {
        val mainPath = File("")
            .absoluteFile
            .path
            .substringBeforeLast('/')

        filePath.removePrefix(mainPath)
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
        "$filePath:$line:$column"
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
    val locationWithText by lazy { "Location: $location \nDeclaration:\n$text" }

    fun resideInFilePath(text: String) = PackageHelper.resideInPackage(text, filePath, '/')

    fun resideInProjectFilePath(text: String) = PackageHelper.resideInPackage(text, projectFilePath, '/')

    fun getDeclarationText(includeBody: Boolean = true): String {
        val declaration = "[${this::class.simpleName}] $location"

        return if (includeBody) {
            "$declaration\n$text"
        } else {
            declaration
        }
    }

    fun print() {
        print(getDeclarationText(false))
    }

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    override fun toString() = getDeclarationText(true)
}
