package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationImpl
import org.jetbrains.kotlin.kdoc.psi.api.KDoc

internal interface KoKDocProviderCore : KoKDocProvider, KoTextProviderCore, KoBaseProviderCore {
    override val kDoc: KoKDocDeclaration?
        get() {
            val kDocElement = psiElement
                .children
                .filterIsInstance<KDoc>()
                .firstOrNull()

            return kDocElement?.let { KoKDocDeclarationImpl(kDocElement) }
        }

    override fun hasKDoc(): Boolean = kDoc != null
}
