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
        get() =
            ktTypeParameterListOwner
                .typeParameters
                .map { typeParameter ->
                    val typeReferences =
                        ktTypeParameterListOwner
                            .typeParameters
                            .filter { it.name == typeParameter.name }
                            .flatMap {
                                it
                                    .children
                                    .filterIsInstance<KtTypeReference>()
                            }

                    val typeConstraints =
                        ktTypeParameterListOwner
                            .typeConstraintList
                            ?.children
                            ?.groupBy {
                                it
                                    .children
                                    .firstIsInstance<KtNameReferenceExpression>()
                                    .text
                            }?.filter { it.key == typeParameter.name }
                            ?.flatMap {
                                it
                                    .value
                                    .map { value ->
                                        value
                                            .children
                                            .firstIsInstance<KtTypeReference>()
                                    }
                            }
                            ?: emptyList()

                    KoTypeParameterDeclarationCore.getInstance(
                        typeParameter,
                        typeReferences + typeConstraints,
                        this.castToKoBaseDeclaration(),
                    )
                }

    override val numTypeParameters: Int
        get() = typeParameters.size

    override fun countTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Int = typeParameters.count { predicate(it) }

    override fun hasTypeParameters(): Boolean = typeParameters.isNotEmpty()

    override fun hasTypeParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeParameterWithName(listOf(name, *names))

    override fun hasTypeParameterWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasTypeParameters()
            else ->
                names.any {
                    typeParameters.any { parameter -> it == parameter.name }
                }
        }

    override fun hasTypeParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeParametersWithAllNames(listOf(name, *names))

    override fun hasTypeParametersWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasTypeParameters()
            else ->
                names.all {
                    typeParameters.any { parameter -> it == parameter.name }
                }
        }

    override fun hasTypeParameter(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean = typeParameters.any(predicate)

    override fun hasAllTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean = typeParameters.all(predicate)
}
