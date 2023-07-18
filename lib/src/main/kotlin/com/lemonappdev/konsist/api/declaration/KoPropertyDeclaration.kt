package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.KoVarAndValProvider

/**
 * Represents a Kotlin property declaration.
 */
interface KoPropertyDeclaration :
    KoBaseDeclaration,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoParentProvider,
    KoVarAndValProvider,
    KoExtensionProvider,
    KoReceiverTypeProvider,
    KoDelegateProvider {

    /**
     * Property explicit type.
     */
    val explicitType: KoTypeDeclaration?

    /**
     * Whatever property has an explicit type.
     *
     * @param type the type to check for (optional).
     * @return `true` if the property has the specified type (or any type if [type] is `null`), `false` otherwise.
     */
    fun hasExplicitType(type: String? = null): Boolean
}
