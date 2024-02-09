package com.lemonappdev.konsist.api.declaration

interface KoFunctionTypeDeclaration : KoTypeDeclaration {
    val parameterTypes: List<KoTypeDeclaration>

    val returnType: KoTypeDeclaration
}
