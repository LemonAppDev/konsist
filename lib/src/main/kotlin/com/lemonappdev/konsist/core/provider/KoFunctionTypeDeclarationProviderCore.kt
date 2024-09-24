package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtParameterList

internal interface KoFunctionTypeDeclarationProviderCore :
    KoFunctionTypeDeclarationProvider,
    KoBaseProviderCore {
    val ktFunctionType: KtFunctionType

    override val parameterTypes: List<KoParameterDeclaration>
        get() =
        ktFunctionType
            .children
            .filterIsInstance<KtParameterList>()
            .flatMap { it.children.toList() }
            .filterIsInstance<KtParameter>()
            .map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val returnType: KoTypeDeclaration
        get() {
        val typeReference = ktFunctionType.returnTypeReference

        return typeReference?.let { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
            ?: throw KoInternalException("Lambda function has no specified type")
    }
}
