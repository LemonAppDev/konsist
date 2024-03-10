package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoSourceDeclarationProviderCore :
    KoSourceDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoTypeProviderCore {
    val ktTypeReference: KtTypeReference
    override val sourceDeclaration: KoBaseTypeDeclaration
        get() =
            TypeUtil.getBasicType(
                listOf(ktTypeReference),
                ktTypeReference.isExtensionDeclaration(),
                castToKoBaseDeclaration(),
                containingFile,
            ) ?: throw KoInternalException("Source declaration cannot be a null")

    override val sourceClass: KoClassDeclaration?
        get() = sourceDeclaration as? KoClassDeclaration

    override val sourceObject: KoObjectDeclaration?
        get() = sourceDeclaration as? KoObjectDeclaration

    override val sourceInterface: KoInterfaceDeclaration?
        get() = sourceDeclaration as? KoInterfaceDeclaration

    override val sourceTypeAlias: KoTypeAliasDeclaration?
        get() = sourceDeclaration as? KoTypeAliasDeclaration

    override val sourceImportAlias: KoImportAliasDeclaration?
        get() = sourceDeclaration as? KoImportAliasDeclaration

    override val sourceKotlinType: KoKotlinTypeDeclaration?
        get() = sourceDeclaration as? KoKotlinTypeDeclaration

    override val sourceFunctionType: KoFunctionTypeDeclaration?
        get() = sourceDeclaration as? KoFunctionTypeDeclaration

    override val sourceExternalType: KoExternalDeclaration?
        get() = sourceDeclaration as? KoExternalDeclaration

    override val isClass: Boolean
        get() = sourceDeclaration is KoClassDeclaration

    override val isObject: Boolean
        get() = sourceDeclaration is KoObjectDeclaration

    override val isInterface: Boolean
        get() = sourceDeclaration is KoInterfaceDeclaration

    override val isTypeAlias: Boolean
        get() = sourceDeclaration is KoTypeAliasDeclaration

    override val isImportAlias: Boolean
        get() = sourceDeclaration is KoImportAliasDeclaration

    override val isKotlinType: Boolean
        get() = sourceDeclaration is KoKotlinTypeDeclaration

    override val isFunctionType: Boolean
        get() = sourceDeclaration is KoFunctionTypeDeclaration

    override val isExternalType: Boolean
        get() = sourceDeclaration is KoExternalDeclaration

    override fun hasSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean = predicate(sourceDeclaration)

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean =
        hasSourceClassOf(kClass) || hasSourceObjectOf(kClass) || hasSourceInterfaceOf(kClass) ||
            hasSourceKotlinTypeOf(
                kClass,
            ) || hasSourceExternalTypeOf(kClass)

    override fun hasSourceClass(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceClass != null
            else -> sourceClass?.let { predicate(it) } ?: false
        }

    override fun hasSourceClassOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == sourceClass?.fullyQualifiedName

    override fun hasSourceObject(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceObject != null
            else -> sourceObject?.let { predicate(it) } ?: false
        }

    override fun hasSourceObjectOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == sourceObject?.fullyQualifiedName

    override fun hasSourceInterface(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceInterface != null
            else -> sourceInterface?.let { predicate(it) } ?: false
        }

    override fun hasSourceInterfaceOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == sourceInterface?.fullyQualifiedName

    override fun hasSourceTypeAlias(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceTypeAlias != null
            else -> sourceTypeAlias?.let { predicate(it) } ?: false
        }

    override fun hasSourceImportAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceImportAlias != null
            else -> sourceImportAlias?.let { predicate(it) } ?: false
        }

    override fun hasSourceKotlinType(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceKotlinType != null
            else -> sourceKotlinType?.let { predicate(it) } ?: false
        }

    override fun hasSourceKotlinTypeOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == sourceKotlinType?.fullyQualifiedName

    override fun hasSourceFunctionType(predicate: ((KoFunctionTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceFunctionType != null
            else -> sourceFunctionType?.let { predicate(it) } ?: false
        }

    override fun hasSourceExternalType(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> sourceExternalType != null
            else -> sourceExternalType?.let { predicate(it) } ?: false
        }

    override fun hasSourceExternalTypeOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == sourceExternalType?.fullyQualifiedName
}
