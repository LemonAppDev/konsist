package com.lemonappdev.konsist.core.provider

import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoDeclarationFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProviderCore,
    KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val fullyQualifiedName: String?
        get() =
            if (ktTypeParameterListOwner.fqName != null) {
                ktTypeParameterListOwner.fqName.toString()
            } else {
                null
            }
}
