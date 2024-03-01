package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

interface KoFunctionTypeDeclaration :
    KoBaseTypeDeclaration,
    KoContainingFileProvider, // todo: add
    KoContainingDeclarationProvider, // todo: add
    KoLocationProvider, // todo: add
    KoPathProvider, // todo: add
    KoModuleProvider, // todo: add
    KoSourceSetProvider { // todo: add
    val parameterTypes: List<KoParameterDeclaration>

    val returnType: KoTypeDeclaration
}
