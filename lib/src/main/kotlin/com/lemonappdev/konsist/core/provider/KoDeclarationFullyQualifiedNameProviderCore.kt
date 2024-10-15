package com.lemonappdev.konsist.core.provider

import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoDeclarationFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProviderCore,
    KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val fullyQualifiedName: String?
        get() = ktTypeParameterListOwner.fqName?.toString()
}
