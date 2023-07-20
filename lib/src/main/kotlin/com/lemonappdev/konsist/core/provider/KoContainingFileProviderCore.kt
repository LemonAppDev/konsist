package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.core.container.KoFileImpl
import org.jetbrains.kotlin.psi.KtElement

internal interface KoContainingFileProviderCore : KoContainingFileProvider, KoBaseProviderCore {
    val ktElement: KtElement

    /**
     * KoFile containing the declaration
     */
    override val containingFile: KoFile
        get() = KoFileImpl(ktElement.containingKtFile)
}
