package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import org.jetbrains.kotlin.psi.KtElement

internal open class KoBaseDeclarationImpl(private val ktElement: KtElement) : KoPsiDeclarationImpl(ktElement), KoBaseDeclaration {
    /**
     * KoFile containing the declaration
     */
    override val containingFile: KoFileDeclaration by lazy { KoFileDeclarationImpl.getInstance(ktElement.containingKtFile, this) }
}
