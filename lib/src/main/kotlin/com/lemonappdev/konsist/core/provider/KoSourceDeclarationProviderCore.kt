package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.core.declaration.private.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.private.KoGenericTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoSourceDeclarationProviderCore :
    KoSourceDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore {
    val ktTypeReference: KtTypeReference?
        get() = null
    val ktNameReferenceExpression: KtNameReferenceExpression?
        get() = null
    val ktTypeProjection: KtTypeProjection?
        get() = null

    override val sourceDeclaration: KoSourceDeclaration?
        get() {
            val type =
                TypeUtil.getBasicType(
                    listOf(ktTypeReference, ktNameReferenceExpression, ktTypeProjection),
                    isExtensionDeclaration(),
                    getDeclarationWithFqn(containingDeclaration) ?: containingDeclaration,
                    containingFile,
                )

            return if (type is KoGenericTypeDeclarationCore) {
                val nestedKtNameReferenceExpression =
                    if (ktNameReferenceExpression != null) {
                        ktNameReferenceExpression
                    } else {
                        val typeElement = ktTypeReference?.typeElement

                        val notNullableTypeElement =
                            if (typeElement is KtNullableType) {
                                typeElement
                                    .children
                                    .firstOrNull()
                            } else {
                                typeElement
                            }

                        notNullableTypeElement
                            ?.children
                            ?.filterIsInstance<KtNameReferenceExpression>()
                            ?.firstOrNull()
                    }

                val nestedType =
                    nestedKtNameReferenceExpression
                        ?.isExtensionDeclaration()
                        ?.let {
                            TypeUtil.getBasicType(
                                listOf(nestedKtNameReferenceExpression),
                                it,
                                this.castToKoBaseDeclaration(),
                                containingFile,
                            )
                        }

                when (nestedType) {
                    is KoGenericTypeDeclarationCore -> nestedType.sourceDeclaration
                    is KoFunctionTypeDeclarationCore -> null
                    else -> nestedType
                }
            } else if (type is KoFunctionTypeDeclarationCore) {
                null
            } else {
                type
            } ?: this as? KoSourceDeclaration
        }

    private fun isExtensionDeclaration(): Boolean =
        ktTypeReference?.isExtensionDeclaration() == true ||
            ktNameReferenceExpression?.isExtensionDeclaration() == true ||
            ktTypeProjection?.isExtensionDeclaration() == true

    private fun getDeclarationWithFqn(declaration: KoBaseDeclaration): KoBaseDeclaration? =
        when {
            declaration is KoFullyQualifiedNameProvider && declaration.fullyQualifiedName != null -> {
                declaration
            }

            declaration is KoContainingDeclarationProvider && declaration !is KoFileDeclaration -> {
                getDeclarationWithFqn(declaration.containingDeclaration)
            }

            else -> {
                null
            }
        }

    override fun hasSourceDeclaration(predicate: (KoSourceDeclaration) -> Boolean): Boolean =
        sourceDeclaration?.let { predicate(it) } == true

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean =
        sourceDeclaration?.hasClassDeclarationOf(kClass) == true ||
            sourceDeclaration?.hasObjectDeclarationOf(kClass) == true ||
            sourceDeclaration?.hasInterfaceDeclarationOf(kClass) == true ||
            sourceDeclaration?.hasKotlinTypeDeclarationOf(kClass) == true ||
            sourceDeclaration?.hasExternalTypeDeclarationOf(kClass) == true
}
