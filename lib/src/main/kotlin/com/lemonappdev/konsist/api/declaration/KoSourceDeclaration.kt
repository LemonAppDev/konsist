package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

interface KoSourceDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoTypeDeclarationProvider,
    KoTypeProvider
