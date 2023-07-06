package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement

internal open class KoBaseDeclarationImpl(private val ktElement: KtElement) :
    KoBaseDeclaration,
    KoPathProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement
        get() = ktElement

    /**
     * KoFile containing the declaration
     */
    override val containingFile: KoFile by lazy { KoFileImpl(ktElement.containingKtFile) }
}
