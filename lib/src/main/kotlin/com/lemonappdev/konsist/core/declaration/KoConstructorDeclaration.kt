package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

open class KoConstructorDeclaration(
    ktConstructor: KtConstructor<*>,
) : KoParametrizedDeclaration(ktConstructor)
