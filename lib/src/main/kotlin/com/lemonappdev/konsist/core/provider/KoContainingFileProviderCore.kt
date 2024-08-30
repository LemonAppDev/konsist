package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.psi.KtElement

internal interface KoContainingFileProviderCore :
    KoContainingFileProvider,
    KoBaseProviderCore {
    val ktElement: KtElement

    /**
     * KoFile containing the declaration
     */
    override val containingFile: KoFileDeclaration
        get() = KoFileDeclarationCore(ktElement.containingKtFile)
}
