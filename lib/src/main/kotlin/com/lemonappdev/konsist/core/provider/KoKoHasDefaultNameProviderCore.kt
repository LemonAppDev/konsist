package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasDefaultNameProvider
import com.lemonappdev.konsist.api.provider.KoIsExtensionProvider
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.util.CompanionUtil.COMPANION_NAME
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoKoHasDefaultNameProviderCore :
    KoBaseProviderCore,
    KoHasDefaultNameProvider,
    KoNameProviderCore {
    override val hasDefaultName: Boolean get() = name == COMPANION_NAME
}
