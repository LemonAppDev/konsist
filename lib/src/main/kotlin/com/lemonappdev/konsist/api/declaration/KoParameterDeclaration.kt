package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParameterDeclaration :
    KoBaseDeclaration,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoDefaultValueProvider,
    KoTopLevelProvider,
    KoParentProvider {
    /**
     * Type of the parameter.
     */
    val type: KoTypeDeclaration

    /**
     * Whether the parameter type represents the specified type.
     *
     * @param name the type to compare. It can be either a simple name or a fully qualified name.
     * @return `true` if this type represents the specified type, `false` otherwise.
     */
    fun representsType(name: String): Boolean
}
