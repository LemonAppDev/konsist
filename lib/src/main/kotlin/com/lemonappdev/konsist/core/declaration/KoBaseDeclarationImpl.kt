package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

/**
 * Base declaration
 */
open class KoBaseDeclarationImpl(private val ktElement: KtElement) : KoPsiDeclarationImpl(ktElement) {
    /**
     * KoFile containing the declaration
     */
    val containingFile by lazy { KoFileDeclarationImpl.getInstance(ktElement.containingKtFile) }
}
