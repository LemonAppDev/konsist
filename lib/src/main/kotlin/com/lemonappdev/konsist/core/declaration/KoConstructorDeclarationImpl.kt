package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtConstructor

internal open class KoConstructorDeclarationImpl(
    ktConstructor: KtConstructor<*>,
    parent: KoParent,
) : KoParametrizedDeclarationImpl(ktConstructor, parent), KoConstructorDeclaration
