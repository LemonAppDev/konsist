package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoEnumNameProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin enum const declaration.
 */
interface KoEnumConstDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoEnumNameProvider,
    KoFullyQualifiedNameProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPackageProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider {
    /**
     * String representing the class.
     *
     * @return a string representing the class.
     */
    override fun toString(): String
}
