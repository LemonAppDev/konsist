package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

open class KoConstructor(
    ktConstructor: KtConstructor<*>,
) : KoParametrizedDeclaration(ktConstructor)
