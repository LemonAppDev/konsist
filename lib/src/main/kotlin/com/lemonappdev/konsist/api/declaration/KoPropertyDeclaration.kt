package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoConstructorDefinedProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoGetterProvider
import com.lemonappdev.konsist.api.provider.KoInitializerProvider
import com.lemonappdev.konsist.api.provider.KoIsConstructorDefinedProvider
import com.lemonappdev.konsist.api.provider.KoIsExtensionProvider
import com.lemonappdev.konsist.api.provider.KoIsInitializedProvider
import com.lemonappdev.konsist.api.provider.KoIsReadOnlyProvider
import com.lemonappdev.konsist.api.provider.KoIsTopLevelProvider
import com.lemonappdev.konsist.api.provider.KoIsValProvider
import com.lemonappdev.konsist.api.provider.KoIsVarProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoReadOnlyProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSetterProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoConstModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoLateinitModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOverrideModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVarModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * Represents a Kotlin property declaration.
 */
interface KoPropertyDeclaration :
    KoBaseDeclaration,
    KoSourceDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoConstructorDefinedProvider,
    KoIsConstructorDefinedProvider,
    KoContainingFileProvider,
    KoDelegateProvider,
    KoNullableTypeProvider,
    KoFullyQualifiedNameProvider,
    KoInitializerProvider,
    KoIsInitializedProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoReceiverTypeProvider,
    KoResideInPackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoIsTopLevelProvider,
    KoValueProvider,
    KoVisibilityModifierProvider,
    KoValModifierProvider,
    KoVarModifierProvider,
    KoLateinitModifierProvider,
    KoOverrideModifierProvider,
    KoAbstractModifierProvider,
    KoOpenModifierProvider,
    KoFinalModifierProvider,
    KoActualModifierProvider,
    KoExpectModifierProvider,
    KoConstModifierProvider,
    KoGetterProvider,
    KoSetterProvider,
    KoReadOnlyProvider,
    KoIsReadOnlyProvider,
    KoTacitTypeProvider,
    KoTypeParameterProvider,
    KoIsExtensionProvider,
    KoIsValProvider,
    KoIsVarProvider
