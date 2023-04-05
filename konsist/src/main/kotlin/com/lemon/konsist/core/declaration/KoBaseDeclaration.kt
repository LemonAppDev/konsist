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

    override fun toString() = textWithLocation

    val location by lazy {
        val lineAndColumn =
            textWithLocation
                .substringAfter("at")
                .substringBefore("in")
                .toMutableList()

        lineAndColumn.removeIf { !it.isDigit() }

        val line = lineAndColumn[0]
        val column = lineAndColumn[1]
        "$filePath:$line:$column"
    }

    val text by lazy {
        textWithLocation
            .substringBefore(" {\n}")
            .substringAfter("'")
    }
}
