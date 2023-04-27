package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

/**
 * Base declaration
 */
open class KoBaseDeclaration(private val ktElement: KtElement): KoPsiDeclaration(ktElement) {
    /**
     * KoFile containing the declaration
     */
    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }
}
