package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
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
    KoContainingDeclarationProviderCore,
    KoTypeProviderCore {
    val ktTypeReference: KtTypeReference?
    val ktNameReferenceExpression: KtNameReferenceExpression?
    val ktTypeProjection: KtTypeProjection?

    override val declaration: KoBaseTypeDeclaration
        get() =
            TypeUtil.getBasicType(
                listOf(ktTypeReference, ktNameReferenceExpression, ktTypeProjection),
                isExtensionDeclaration(),
                getDeclarationWithFqn(containingDeclaration) ?: containingDeclaration,
                containingFile,
            ) ?: throw KoInternalException("Source declaration cannot be a null")

    private fun isExtensionDeclaration(): Boolean =
        ktTypeReference?.isExtensionDeclaration() == true ||
            ktNameReferenceExpression?.isExtensionDeclaration() == true ||
            ktTypeProjection?.isExtensionDeclaration() == true

    private fun getDeclarationWithFqn(declaration: KoBaseDeclaration): KoBaseDeclaration? =
        when {
            declaration is KoFullyQualifiedNameProvider && declaration.fullyQualifiedName != null -> declaration
            declaration is KoContainingDeclarationProvider && declaration !is KoFileDeclaration-> getDeclarationWithFqn(declaration.containingDeclaration)
            else -> null
        }

    override fun asClassDeclaration(): KoClassDeclaration? = declaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? = declaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = declaration as? KoInterfaceDeclaration

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = declaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = declaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = declaration as? KoKotlinTypeDeclaration

    override fun asFunctionTypeDeclaration(): KoFunctionTypeDeclaration? = declaration as? KoFunctionTypeDeclaration

    override fun asGenericTypeDeclaration(): KoGenericTypeDeclaration? = declaration as? KoGenericTypeDeclaration

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? = declaration as? KoTypeParameterDeclaration

    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = declaration as? KoExternalDeclaration

    override fun asStarProjectionDeclaration(): KoStarProjectionDeclaration? = declaration as? KoStarProjectionDeclaration

    override val isClass: Boolean
        get() = declaration is KoClassDeclaration

    override val isObject: Boolean
        get() = declaration is KoObjectDeclaration

    override val isInterface: Boolean
        get() = declaration is KoInterfaceDeclaration

    override val isTypeAlias: Boolean
        get() = declaration is KoTypeAliasDeclaration

    override val isImportAlias: Boolean
        get() = declaration is KoImportAliasDeclaration

    override val isKotlinType: Boolean
        get() = declaration is KoKotlinTypeDeclaration

    override val isFunctionType: Boolean
        get() = declaration is KoFunctionTypeDeclaration

    override val isGenericType: Boolean
        get() = declaration is KoGenericTypeDeclaration

    override val isTypeParameter: Boolean
        get() = declaration is KoTypeParameterDeclaration

    override val isExternalType: Boolean
        get() = declaration is KoExternalDeclaration

    override val isStarProjection: Boolean
        get() = declaration is KoStarProjectionDeclaration

    override fun hasDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean = predicate(declaration)

    override fun hasDeclarationOf(kClass: KClass<*>): Boolean =
        hasClassDeclarationOf(kClass) ||
            hasObjectDeclarationOf(kClass) ||
            hasInterfaceDeclarationOf(kClass) ||
            hasKotlinTypeDeclarationOf(
                kClass,
            ) ||
            hasExternalTypeDeclarationOf(kClass)

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asClassDeclaration() != null
            else -> asClassDeclaration()?.let { predicate(it) } ?: false
        }

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
