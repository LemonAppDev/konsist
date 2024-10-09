package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore

internal interface KoSourceDeclarationCore :
    KoSourceDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoTypeDeclarationProviderCore,
    KoTypeProviderCore
