package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoTypeDeclarationProviderCore :
    KoTypeDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore {
    /*
    Remove in version 0.18.0
    */
    val ktTypeReference: KtTypeReference?
        get() = null

    /*
    Remove in version 0.18.0
    */
    val ktNameReferenceExpression: KtNameReferenceExpression?
        get() = null

    /*
    Remove in version 0.18.0
    */
    val ktTypeProjection: KtTypeProjection?
        get() = null

    @Deprecated("Will be removed in version 0.18.0", replaceWith = ReplaceWith("sourceDeclaration"))
    override val declaration: KoBaseTypeDeclaration
        get() =
            TypeUtil.getBasicType(
                listOf(ktTypeReference, ktNameReferenceExpression, ktTypeProjection),
                isExtensionDeclaration(),
                getDeclarationWithFqn(containingDeclaration) ?: containingDeclaration,
                containingFile,
            ) as? KoBaseTypeDeclaration
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

    val koTypeDeclarationProviderDeclaration: KoSourceDeclaration

    override fun asClassDeclaration(): KoClassDeclaration? = koTypeDeclarationProviderDeclaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? = koTypeDeclarationProviderDeclaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = koTypeDeclarationProviderDeclaration as? KoInterfaceDeclaration

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = koTypeDeclarationProviderDeclaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = koTypeDeclarationProviderDeclaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = koTypeDeclarationProviderDeclaration as? KoKotlinTypeDeclaration

    override fun asFunctionTypeDeclaration(): KoFunctionTypeDeclaration? = koTypeDeclarationProviderDeclaration as? KoFunctionTypeDeclaration

    override fun asGenericTypeDeclaration(): KoGenericTypeDeclaration? = koTypeDeclarationProviderDeclaration as? KoGenericTypeDeclaration

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? = koTypeDeclarationProviderDeclaration as? KoTypeParameterDeclaration

    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = koTypeDeclarationProviderDeclaration as? KoExternalDeclaration

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asClassDeclaration() != null
            else -> asClassDeclaration()?.let { predicate(it) } ?: false
        }

    @Deprecated("Will be removed in version 0.18.0", replaceWith = ReplaceWith("hasSourceDeclaration"))
    override fun hasDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean = predicate(declaration)

    @Deprecated("Will be removed in version 0.18.0", replaceWith = ReplaceWith("hasSourceDeclarationOf"))
    override fun hasDeclarationOf(kClass: KClass<*>): Boolean =
        hasClassDeclarationOf(kClass) ||
            hasObjectDeclarationOf(kClass) ||
            hasInterfaceDeclarationOf(kClass) ||
            hasKotlinTypeDeclarationOf(
                kClass,
            ) ||
            hasExternalTypeDeclarationOf(kClass)

    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asClassDeclaration()?.fullyQualifiedName

    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asObjectDeclaration() != null
            else -> asObjectDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asObjectDeclaration()?.fullyQualifiedName

    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asInterfaceDeclaration() != null
            else -> asInterfaceDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asInterfaceDeclaration()?.fullyQualifiedName

    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asTypeAliasDeclaration() != null
            else -> asTypeAliasDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asImportAliasDeclaration() != null
            else -> asImportAliasDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asKotlinTypeDeclaration() != null
            else -> asKotlinTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asKotlinTypeDeclaration()?.fullyQualifiedName

    override fun hasFunctionTypeDeclaration(predicate: ((KoFunctionTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asFunctionTypeDeclaration() != null
            else -> asFunctionTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasGenericTypeDeclaration(predicate: ((KoGenericTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asGenericTypeDeclaration() != null
            else -> asGenericTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asTypeParameterDeclaration() != null
            else -> asTypeParameterDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asExternalTypeDeclaration() != null
            else -> asExternalTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asExternalTypeDeclaration()?.fullyQualifiedName
}
