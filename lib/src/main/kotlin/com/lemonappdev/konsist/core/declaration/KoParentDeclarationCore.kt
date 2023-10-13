package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore

internal interface KoParentDeclarationCore :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore
