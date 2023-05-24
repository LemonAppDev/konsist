package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtElement

internal open class KoBaseDeclarationImpl(private val ktElement: KtElement) : KoPsiDeclarationImpl(ktElement), KoBaseDeclaration, KoParent {
    /**
     * KoFile containing the declaration
     */
    override val containingFile by lazy { KoFileDeclarationImpl(ktElement.containingKtFile) }
}
