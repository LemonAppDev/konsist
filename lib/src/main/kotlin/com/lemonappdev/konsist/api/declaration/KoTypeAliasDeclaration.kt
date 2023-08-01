package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * Represents a Kotlin type alias declaration.
 */
interface KoTypeAliasDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoFullyQualifiedNameProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoParentProvider,
    KoPathProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoTypeProvider {
    /**
     * String representing the type alias.
     *
     * @return a string representing the type alias.
     */
    override fun toString(): String
}
