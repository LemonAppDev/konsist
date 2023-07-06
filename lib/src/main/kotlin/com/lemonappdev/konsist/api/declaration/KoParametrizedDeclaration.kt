package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParametrizedDeclaration :
    KoBaseDeclaration,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoParentProvider {
    /**
     * Parameters of the declaration.
     */
    val parameters: List<KoParameterDeclaration>

    /**
     * Whatever declaration has a parameter with given name.
     *
     * @param name the name of the parameter to check.
     * @return `true` if the declaration has a parameter with the specified name, `false` otherwise.
     */
    fun hasParameterNamed(name: String): Boolean
}
