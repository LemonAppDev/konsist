package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore

internal interface KoChildDeclarationCore :
    KoChildDeclaration,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore
