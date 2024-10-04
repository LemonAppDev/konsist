package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
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

    override val sourceDeclaration: KoBaseTypeDeclaration
        get() =
            TypeUtil.getBasicType(
                listOf(ktTypeReference, ktNameReferenceExpression, ktTypeProjection),
                isExtensionDeclaration(),
                getDeclarationWithFqn(containingDeclaration) ?: containingDeclaration,
                containingFile,
            )
                ?: if (this is KoBaseTypeDeclaration) {
                    this
                } else {
                    throw KoInternalException("Source declaration cannot be a null")
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

    override fun hasSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean =
        predicate(sourceDeclaration)

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean =
        sourceDeclaration.hasClassDeclarationOf(kClass) ||
                sourceDeclaration.hasObjectDeclarationOf(kClass) ||
                sourceDeclaration.hasInterfaceDeclarationOf(kClass) ||
                sourceDeclaration.hasKotlinTypeDeclarationOf(
                    kClass,
                ) ||
                sourceDeclaration.hasExternalTypeDeclarationOf(kClass)
}
