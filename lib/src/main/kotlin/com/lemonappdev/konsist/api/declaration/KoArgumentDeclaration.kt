package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoEnumNameProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
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
    KoContainingFileProvider,
//    KoFullyQualifiedNameProvider,
    KoLocationProvider,
    KoNameProvider,
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
