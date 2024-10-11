package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import com.lemonappdev.konsist.core.declaration.KoTypeParameterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance

internal interface KoTypeParameterProviderCore :
    KoTypeParameterProvider,
    KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val typeParameters: List<KoTypeParameterDeclaration>
        get() {
            val typeReferences = ktTypeParameterListOwner
                .typeParameters
                .flatMap {
                    it
                        .children
                        .filterIsInstance<KtTypeReference>()
                }

            return ktTypeParameterListOwner
                .typeParameters
                .map { typeParameter ->
                    val typeConstraints = ktTypeParameterListOwner
                        .typeConstraintList
                        ?.children
                        ?.groupBy {
                            it
                                .children
                                .firstIsInstance<KtNameReferenceExpression>()
                                .text
                        }
                        ?.filter { it.key == typeParameter.text }
                        ?.flatMap { it
                            .value
                            .map { value ->
                                value
                                    .children
                                    .firstIsInstance<KtTypeReference>()
                            }
                        }
                        ?: emptyList()

                    KoTypeParameterDeclarationCore.getInstance(typeParameter, typeReferences + typeConstraints, this.castToKoBaseDeclaration()) }
        }
}
