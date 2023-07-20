package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import org.jetbrains.kotlin.psi.KtImportDirective

internal interface KoAliasProviderCore :
    KoAliasProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    val ktImportDirective: KtImportDirective
    override val alias: String?
        get() = ktImportDirective.alias?.name
}
