package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration :
    KoParametrizedDeclaration,
    KoBaseDeclaration,
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider,
    KoParentProvider,
    KoModifierProvider {

    /**
     * Return type of the function.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Receiver type of the function.
     */
    val receiverType: KoTypeDeclaration?

    /**
     * Whether this function is an extension function.
     *
     * @return `true` if the function is an extension function, `false` otherwise.
     */
    fun isExtension(): Boolean

    /**
     * Whether this function has receiver type.
     *
     * @param name the receiver type to check.
     * @return `true` if the function has receiver type with the specified name (or any receiver type if [name] is null),
     * `false` otherwise.
     */
    fun hasReceiverType(name: String? = null): Boolean

    /**
     * Whether this function has a return type.
     *
     * @return `true` if the function has the return type, `false` otherwise.
     */
    fun hasReturnType(): Boolean
}
