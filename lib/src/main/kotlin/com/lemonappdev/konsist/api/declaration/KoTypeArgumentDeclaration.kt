package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider {
    val name: String

    val typeArguments: List<KoTypeArgumentDeclaration>?

    val sourceDeclaration: KoTypeDeclaration
}
