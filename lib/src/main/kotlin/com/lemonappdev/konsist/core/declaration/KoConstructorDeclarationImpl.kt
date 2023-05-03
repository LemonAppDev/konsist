package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

internal open class KoConstructorDeclarationImpl(
    ktConstructor: KtConstructor<*>,
) : KoParametrizedDeclarationImpl(ktConstructor), KoConstructorDeclaration
