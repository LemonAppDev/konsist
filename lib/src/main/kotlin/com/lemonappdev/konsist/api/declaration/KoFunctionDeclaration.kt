package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration : KoParametrizedDeclaration, KoLocalClassProvider, KoLocalFunctionProvider, KoLocalPropertyProvider {
    /**
     * Return type of the function.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Receiver of the extension function.
     */
    val receiver: KoTypeDeclaration?

    /**
     * Whether this function has operator modifier.
     *
     * @return `true` if the function has the `operator` modifier, `false` otherwise.
     */
    fun hasOperatorModifier(): Boolean

    /**
     * Whether this function has inline modifier.
     *
     * @return `true` if the function has the `inline` modifier, `false` otherwise.
     */
    fun hasInlineModifier(): Boolean

    /**
     * Whether this function has tailrec modifier.
     *
     * @return `true` if the function has the `tailrec` modifier, `false` otherwise.
     */
    fun hasTailrecModifier(): Boolean

    /**
     * Whether this function has infix modifier.
     *
     * @return `true` if the function has the `infix` modifier, `false` otherwise.
     */
    fun hasInfixModifier(): Boolean

    /**
     * Whether this function has external modifier.
     *
     * @return `true` if the function has the `external` modifier, `false` otherwise.
     */
    fun hasExternalModifier(): Boolean

    /**
     * Whether this function has suspend modifier.
     *
     * @return `true` if the function has the `suspend` modifier, `false` otherwise.
     */
    fun hasSuspendModifier(): Boolean

    /**
     * Whether this function has open modifier.
     *
     * @return `true` if the function has the `open` modifier, `false` otherwise.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whether this function has override modifier.
     *
     * @return `true` if the function has the `override` modifier, `false` otherwise.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * Whether this function has final modifier.
     *
     * @return `true` if the function has the `final` modifier, `false` otherwise.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whether this function has abstract modifier.
     *
     * @return `true` if the function has the `abstract` modifier, `false` otherwise.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whether this function has actual modifier.
     *
     * @return `true` if the function has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whether this function has expect modifier.
     *
     * @return `true` if the function has the `expect` modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whether this function is an extension function.
     *
     * @return `true` if the function is an extension function, `false` otherwise.
     */
    fun isExtension(): Boolean

    /**
     * Whether this function has receiver.
     *
     * @param name the receiver to check.
     * @return `true` if the function has receiver with the specified name (or any receiver if [name] is null),
     * `false` otherwise.
     */
    fun hasReceiver(name: String? = null): Boolean

    /**
     * Whether this function has a return type.
     *
     * @return `true` if the function has the return type, `false` otherwise.
     */
    fun hasReturnType(): Boolean
}
