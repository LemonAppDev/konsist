package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import org.jetbrains.kotlin.psi.KtConstructor

internal open class KoConstructorDeclarationImpl(
    ktConstructor: KtConstructor<*>, parent: KoBaseDeclarationImpl?,
) : KoParametrizedDeclarationImpl(ktConstructor, parent), KoConstructorDeclaration
