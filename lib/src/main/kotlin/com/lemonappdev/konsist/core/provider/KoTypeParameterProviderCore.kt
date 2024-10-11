package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import com.lemonappdev.konsist.core.declaration.KoTypeParameterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtTypeParameter
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoTypeParameterProviderCore : KoTypeParameterProvider, KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val typeParameters: List<KoTypeParameterDeclaration>
        get() = ktTypeParameterListOwner
            .typeParameters
            .map { KoTypeParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
}
