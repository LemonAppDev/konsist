package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoExplicitBackingFieldDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitBackingFieldProvider
import com.lemonappdev.konsist.core.declaration.KoExplicitBackingFieldDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoExplicitBackingFieldProviderCore :
    KoExplicitBackingFieldProvider,
    KoBaseProviderCore {
    val ktProperty: KtProperty?

    override val explicitBackingField: KoExplicitBackingFieldDeclaration?
        get() =
            ktProperty
                ?.fieldDeclaration
                ?.let { KoExplicitBackingFieldDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val hasExplicitBackingField: Boolean
        get() = ktProperty?.fieldDeclaration != null
}
