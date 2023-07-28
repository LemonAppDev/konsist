package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoWildcardProvider
import org.jetbrains.kotlin.psi.KtImportDirective

internal interface KoWildcardProviderCore : KoWildcardProvider, KoBaseProviderCore {
    val ktImportDirective: KtImportDirective
    override val isWildcard: Boolean
        get() = ktImportDirective.text.endsWith('*')
}
