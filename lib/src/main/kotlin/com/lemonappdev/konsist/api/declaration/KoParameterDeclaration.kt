package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.api.provider.modifier.KoCrossInlineModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoNoInlineModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVarArgModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVarModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParameterDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoDefaultValueProvider,
    KoFullyQualifiedNameProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoParentProvider,
    KoPathProvider,
    KoRepresentsTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTypeProvider,
    KoVisibilityModifierProvider,
    KoVarModifierProvider,
    KoValModifierProvider,
    KoVarArgModifierProvider,
    KoNoInlineModifierProvider,
    KoCrossInlineModifierProvider {
    /**
     * String representing the parameter.
     *
     * @return a string representing the parameter.
     */
    override fun toString(): String
}
