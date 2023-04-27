package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.parents
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
    open val text by lazy {
        ktElement
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    val locationWithText by lazy { "Location: $location \nDeclaration:\n$text" }

    val parentDeclaration by lazy {
        val parents = ktElement
            .parents
            .toList()

        val parent = when {
            parents.size > 1 -> parents[1]
            else -> null
        }

        val name = parent
            ?.namedUnwrappedElement
            ?.name

        name?.let {
            containingFile
                .declarations(includeLocal = true, includeNested = true)
                .firstOrNull { declaration -> declaration.name == it }
        }
    }

    fun hasParentDeclaration(name: String? = null) = when (name) {
        null -> parentDeclaration != null
        else -> parentDeclaration?.name == name
    }

    fun resideInFilePath(text: String) = PackageHelper.resideInPackage(text, filePath, '/')

    fun resideInProjectFilePath(text: String) = PackageHelper.resideInPackage(text, projectFilePath, '/')

    fun print() {
        print(toString())
    }

    /**
     * Text of the declaration containing ko declaration type, file path, line, column and the declaration text.
     */
    override fun toString() = "[${this::class.simpleName}] $location\n$text"
}
