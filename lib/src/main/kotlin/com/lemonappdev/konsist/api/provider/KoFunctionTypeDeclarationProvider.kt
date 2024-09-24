package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration

interface KoFunctionTypeDeclarationProvider : KoBaseProvider {
    /**
     * Represents the parameters of the function type.
     */
    val parameterTypes: List<KoParameterDeclaration>

    /**
     * Represents the return type of the function type.
     */
    val returnType: KoTypeDeclaration
}
