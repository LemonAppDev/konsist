package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin secondary constructor declaration.
 */
interface KoSecondaryConstructorDeclaration :
    KoBaseProvider,
    KoAnnotationProvider,
    KoConstructorProvider,
    KoContainingFileProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoPackageProvider,
    KoParametersProvider,
    KoParentProvider,
    KoPathProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider {
    /**
     * String representing the secondary constructor.
     *
     * @return a string representing the secondary constructor.
     */
    override fun toString(): String
}
