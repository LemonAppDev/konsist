package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

open class KoBaseDeclaration(private val ktElement: KtElement) {
    val filePath by lazy { ktElement.containingKtFile.virtualFilePath }

    val textWithLocation by lazy { ktElement.getTextWithLocation() }

    val containingFile by lazy { KoFile(ktElement.containingKtFile) }
}
