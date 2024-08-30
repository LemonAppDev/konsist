package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoWildcardProvider
import org.jetbrains.kotlin.psi.KtImportDirective

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsWildcardProviderCore"))
internal interface KoWildcardProviderCore :
    KoWildcardProvider,
    KoBaseProviderCore {
    val ktImportDirective: KtImportDirective
    override val isWildcard: Boolean
        get() = ktImportDirective.text.endsWith('*')
}
