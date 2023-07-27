package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin package declaration.
 */
interface KoPackageDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoContainingFileProvider,
    KoFullyQualifiedNameProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPackageMatchingFilePathProvider,
    KoPathProvider,
    KoTextProvider {
    /**
     * String representing the package.
     *
     * @return a string representing the package.
     */
    override fun toString(): String
}
