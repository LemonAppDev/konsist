package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsWildcardProvider
import org.jetbrains.kotlin.psi.KtImportDirective

internal interface KoIsWildcardProviderCore :
    KoIsWildcardProvider,
    KoBaseProviderCore {
    val ktImportDirective: KtImportDirective
    override val isWildcard: Boolean
        get() = ktImportDirective.text.endsWith('*')
}
