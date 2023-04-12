package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

open class KoBaseDeclaration(private val ktElement: KtElement) {
    val filePath by lazy {
        ktElement
            .containingKtFile
            .virtualFilePath
            .replace("//", "/")
    }

    val textWithLocation by lazy { ktElement.getTextWithLocation() }

    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }

    val location by lazy {
        val lineAndColumn = textWithLocation
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
        textWithLocation
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    override fun toString() = textWithLocation
}
