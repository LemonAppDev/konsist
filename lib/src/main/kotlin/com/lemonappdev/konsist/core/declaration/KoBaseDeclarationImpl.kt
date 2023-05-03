package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import org.jetbrains.kotlin.psi.KtElement

/**
 * Base declaration
 */
internal open class KoBaseDeclarationImpl(private val ktElement: KtElement) : KoPsiDeclarationImpl(ktElement), KoBaseDeclaration {
    /**
     * KoFile containing the declaration
     */
    override val containingFile by lazy { KoFileDeclarationImpl.getInstance(ktElement.containingKtFile) }
}
