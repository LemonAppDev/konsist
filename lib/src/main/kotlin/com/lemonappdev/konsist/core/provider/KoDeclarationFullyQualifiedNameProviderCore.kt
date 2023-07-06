package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoDeclarationFullyQualifiedNameProviderCore : KoDeclarationFullyQualifiedNameProvider {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val fullyQualifiedName: String
        get() = if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
}
