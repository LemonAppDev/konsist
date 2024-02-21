package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

interface KoFunctionTypeDeclaration : KoTypeDeclaration {
    val parameterTypes: List<KoParameterDeclaration>

    val returnType: KoTypeDeclaration
}
