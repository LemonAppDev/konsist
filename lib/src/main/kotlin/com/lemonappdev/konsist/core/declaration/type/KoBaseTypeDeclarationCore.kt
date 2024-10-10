package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore

internal interface KoBaseTypeDeclarationCore :
    KoBaseTypeDeclaration,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore
