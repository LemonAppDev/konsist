package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration : KoParametrizedDeclaration, KoLocalClassProvider, KoLocalFunctionProvider, KoLocalPropertyProvider {
    /**
     * Whether this function is a return type.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Whether this function is a operator modifier.
     */
    fun hasOperatorModifier(): Boolean

    /**
     * Whether this function is a inline modifier.
     */
    fun hasInlineModifier(): Boolean

    /**
     * Whether this function is a tailrec modifier.
     */
    fun hasTailrecModifier(): Boolean

    /**
     * Whether this function is a infix modifier.
     */
    fun hasInfixModifier(): Boolean

    /**
     * Whether this function is a external modifier.
     */
    fun hasExternalModifier(): Boolean

    /**
     * Whether this function is a suspend modifier.
     */
    fun hasSuspendModifier(): Boolean

    /**
     * Whether this function is a open modifier.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whether this function is a override modifier.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * Whether this function is a final modifier.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whether this function is a abstract modifier.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whether this function is a actual modifier.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whether this function is a expect modifier.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whether this function is a extension function.
     */
    fun isExtension(): Boolean

    /**
     * Whether this function has a return type.
     */
    fun hasReturnType(): Boolean
}
