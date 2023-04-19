package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

open class KoBaseDeclaration(private val ktElement: KtElement) {
    val filePath by lazy {
        ktElement
            .containingKtFile
            .virtualFilePath
            .replace("//", "/")
    }

    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }

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

    val text by lazy {
        ktElement
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    val textWithLocation by lazy { "Location: $location \nDeclaration:\n$text" }

    override fun toString() = textWithLocation
}
