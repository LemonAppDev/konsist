package com.lemonappdev.konsist.api.declaration

interface KoFunctionTypeDeclaration : KoTypeDeclaration {
    val parameterTypes: List<KoParameterDeclaration>

    val returnType: KoTypeDeclaration
}
