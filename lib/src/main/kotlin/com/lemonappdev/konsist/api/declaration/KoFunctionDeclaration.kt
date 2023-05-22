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
     * Whether this function is an operator modifier.
     *
     * @return `true` if the declaration has the 'operator' modifier, `false` otherwise.
     */
    fun hasOperatorModifier(): Boolean

    /**
     * Whether this function is an inline modifier.
     *
     * @return `true` if the declaration has the 'inline' modifier, `false` otherwise.
     */
    fun hasInlineModifier(): Boolean

    /**
     * Whether this function is a tailrec modifier.
     *
     * @return `true` if the declaration has the 'tailrec' modifier, `false` otherwise.
     */
    fun hasTailrecModifier(): Boolean

    /**
     * Whether this function is an infix modifier.
     *
     * @return `true` if the declaration has the 'infix' modifier, `false` otherwise.
     */
    fun hasInfixModifier(): Boolean

    /**
     * Whether this function is an external modifier.
     *
     * @return `true` if the declaration has the 'external' modifier, `false` otherwise.
     */
    fun hasExternalModifier(): Boolean

    /**
     * Whether this function is a suspend modifier.
     *
     * @return `true` if the declaration has the 'suspend' modifier, `false` otherwise.
     */
    fun hasSuspendModifier(): Boolean

    /**
     * Whether this function is a open modifier.
     *
     * @return `true` if the declaration has the 'open' modifier, `false` otherwise.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whether this function is an override modifier.
     *
     * @return `true` if the declaration has the 'override' modifier, `false` otherwise.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * Whether this function is a final modifier.
     *
     * @return `true` if the declaration has the 'final' modifier, `false` otherwise.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whether this function is an abstract modifier.
     *
     * @return `true` if the declaration has the 'abstract' modifier, `false` otherwise.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whether this function is an actual modifier.
     *
     * @return `true` if the declaration has the 'actual' modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whether this function is an expect modifier.
     *
     * @return `true` if the declaration has the 'expect' modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whether this function is an extension function.
     *
     * @return `true` if the declaration is an extension function, `false` otherwise.
     */
    fun isExtension(): Boolean

    /**
     * Whether this function has a return type.
     *
     * @return `true` if the declaration has return type, `false` otherwise.
     */
    fun hasReturnType(): Boolean
}
