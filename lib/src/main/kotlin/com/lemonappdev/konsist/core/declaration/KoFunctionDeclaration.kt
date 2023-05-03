package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider

interface KoFunctionDeclaration : KoParametrizedDeclaration, KoLocalClassProvider, KoLocalFunctionProvider, KoLocalPropertyProvider {
    val returnType: KoTypeDeclaration?

    fun hasOperatorModifier(): Boolean

    fun hasInlineModifier(): Boolean

    fun hasTailrecModifier(): Boolean

    fun hasInfixModifier(): Boolean

    fun hasExternalModifier(): Boolean

    fun hasSuspendModifier(): Boolean

    fun hasOpenModifier(): Boolean

    fun hasOverrideModifier(): Boolean

    fun hasFinalModifier(): Boolean

    fun hasAbstractModifier(): Boolean

    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean

    fun isExtension(): Boolean

    fun hasReturnType(): Boolean
}
