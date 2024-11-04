package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.core.provider.KoChildProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeParameterProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoSealedModifierProviderCore
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoClassAndInterfaceDeclarationCore :
    KoClassAndInterfaceDeclaration,
    KoClassAndInterfaceAndObjectDeclarationCore,
    KoActualModifierProviderCore,
    KoChildProviderCore,
    KoExpectModifierProviderCore,
    KoSealedModifierProviderCore,
    KoTypeParameterProviderCore,
    KoIsGenericProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
}
