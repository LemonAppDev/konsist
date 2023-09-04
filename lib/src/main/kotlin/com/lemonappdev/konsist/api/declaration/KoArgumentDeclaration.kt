package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoArgumentNameProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin argument declaration.
 */
interface KoArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoArgumentNameProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoPackageProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider {
    /**
     * String representing the argument.
     *
     * @return a string representing the argument.
     */
    override fun toString(): String
}
