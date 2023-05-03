package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

open class KoConstructorDeclarationImpl(
    ktConstructor: KtConstructor<*>,
) : KoParametrizedDeclarationImpl(ktConstructor)
