package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocalPropertyProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExternalModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInfixModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInlineModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOperatorModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOverrideModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSuspendModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoTailrecModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoExplicitReturnTypeProvider,
    KoExtensionProvider,
    KoFullyQualifiedNameProvider,
    KoImplementationProvider,
    KoKDocProvider,
    KoLocalClassProvider,
    KoLocalDeclarationProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoParametersProvider,
    KoParentProvider,
    KoPathProvider,
    KoReceiverTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoVisibilityModifierProvider,
    KoOperatorModifierProvider,
    KoInlineModifierProvider,
    KoTailrecModifierProvider,
    KoInfixModifierProvider,
    KoExternalModifierProvider,
    KoSuspendModifierProvider,
    KoOpenModifierProvider,
    KoOverrideModifierProvider,
    KoFinalModifierProvider,
    KoAbstractModifierProvider,
    KoActualModifierProvider,
    KoExpectModifierProvider {
    /**
     * String representing the function.
     *
     * @return a string representing the function.
     */
    override fun toString(): String
}
