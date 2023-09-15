package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider

/**
 * Represents a Kotlin argument declaration.
 */
interface KoArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoValueProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPackageProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoResideInPackageProvider,
    KoTextProvider {
    /**
     * String representing the argument.
     *
     * @return a string representing the argument.
     */
    override fun toString(): String
}
