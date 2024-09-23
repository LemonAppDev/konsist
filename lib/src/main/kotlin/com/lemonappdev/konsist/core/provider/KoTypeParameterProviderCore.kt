package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoBodyProvider
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtTypeParameter
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoTypeParameterProviderCore : KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    val typeParameters: List<KtTypeParameter>
        get() = ktTypeParameterListOwner.typeParameters
}
