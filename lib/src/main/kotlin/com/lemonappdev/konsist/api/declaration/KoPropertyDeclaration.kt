package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider

/**
 * Represents a Kotlin property declaration.
 */
interface KoPropertyDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoDelegateProvider,
    KoExplicitTypeProvider,
    KoExtensionProvider,
    KoFullyQualifiedNameProvider,
    KoImplementationProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoParentProvider,
    KoPathProvider,
    KoReceiverTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoValModifierProvider {
    /**
     * String representing the property.
     *
     * @return a string representing the property.
     */
    override fun toString(): String
}
