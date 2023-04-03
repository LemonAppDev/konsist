package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

open class KoConstructor(
    ktConstructor: KtConstructor<*>,
) : KoParametrizedDeclaration(ktConstructor)
