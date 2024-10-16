package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoIsValProvider
import com.lemonappdev.konsist.api.provider.KoIsVarProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider
import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVarModifierProvider

/**
 * Represents a Kotlin property declaration.
 */
interface KoVariableDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingDeclarationProvider,
    KoContainingFileProvider,
    KoDelegateProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModuleProvider,
    KoNameProvider,
    KoPackageProvider,
    KoPathProvider,
    KoNullableTypeProvider,
    KoResideInPackageProvider,
    KoSourceSetProvider,
    KoTextProvider,
    KoValModifierProvider,
    KoValueProvider,
    KoVarModifierProvider,
    KoTacitTypeProvider,
    KoIsValProvider,
    KoIsVarProvider
