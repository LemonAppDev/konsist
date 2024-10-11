package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration

interface KoTypeParameterProvider : KoBaseProvider {
    val typeParameters: List<KoTypeParameterDeclaration>
}
