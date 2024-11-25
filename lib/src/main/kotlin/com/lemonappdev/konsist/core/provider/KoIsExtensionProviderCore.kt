package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsExtensionProvider
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoIsExtensionProviderCore :
    KoBaseProviderCore,
    KoIsExtensionProvider,
    KoSourceDeclarationCore {
    override val isExtension: Boolean get() = psiElement?.isExtensionDeclaration() == true
}
