package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration :
    KoBaseDeclaration,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoParentProvider,
    KoParametersProvider,
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider,
    KoExtensionProvider,
    KoExplicitReturnTypeProvider,
    KoReceiverTypeProvider {
    /**
     * Whether this function has implementation.
     *
     * @return `true` if the function has the implementation, `false` otherwise.
     */
    fun hasImplementation(): Boolean
    }
