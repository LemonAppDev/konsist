package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.core.declaration.KoImportAliasDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtImportDirective

internal interface KoAliasProviderCore :
    KoAliasProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore
