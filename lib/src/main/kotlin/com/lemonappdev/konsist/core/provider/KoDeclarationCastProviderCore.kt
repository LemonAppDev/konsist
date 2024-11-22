package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.util.TypeUtil
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoDeclarationCastProviderCore :
    KoDeclarationCastProvider,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore {
    val koDeclarationCastProviderDeclaration: KoSourceDeclaration?
        get() = null

    override val isKotlinBasicType: Boolean
        get() = TypeUtil.isKotlinBasicType(name)

    override val isKotlinCollectionType: Boolean
        get() = TypeUtil.isKotlinCollectionTypes(name)

    override val isClass: Boolean
        get() = koDeclarationCastProviderDeclaration is KoClassDeclaration

    override val isObject: Boolean
        get() = koDeclarationCastProviderDeclaration is KoObjectDeclaration

    override val isInterface: Boolean
        get() = koDeclarationCastProviderDeclaration is KoInterfaceDeclaration

    override val isClassOrObject: Boolean
        get() = isClass || isObject

    override val isClassOrInterface: Boolean
        get() = isClass || isInterface

    override val isInterfaceOrObject: Boolean
        get() = isInterface || isObject

    override val isClassOrInterfaceOrObject: Boolean
        get() = isClass || isInterface || isObject

    override val isTypeAlias: Boolean
        get() = koDeclarationCastProviderDeclaration is KoTypeAliasDeclaration

    override val isImportAlias: Boolean
        get() = koDeclarationCastProviderDeclaration is KoImportAliasDeclaration

    override val isKotlinType: Boolean
        get() =
            koDeclarationCastProviderDeclaration is KoKotlinTypeDeclaration ||
                    koDeclarationCastProviderDeclaration?.name?.let { TypeUtil.isKotlinType(it) } == true

    override val isTypeParameter: Boolean
        get() = koDeclarationCastProviderDeclaration is KoTypeParameterDeclaration

    override val isExternal: Boolean
        get() = koDeclarationCastProviderDeclaration is KoExternalDeclaration

    override val isFunction: Boolean
        get() = koDeclarationCastProviderDeclaration is KoFunctionDeclaration

    override val isProperty: Boolean
        get() = koDeclarationCastProviderDeclaration is KoPropertyDeclaration

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("isExternal"))
    override val isExternalType: Boolean
        get() = isExternal

    override fun asClassDeclaration(): KoClassDeclaration? = koDeclarationCastProviderDeclaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? =
        koDeclarationCastProviderDeclaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? =
        koDeclarationCastProviderDeclaration as? KoInterfaceDeclaration

    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration? =
        asClassDeclaration() ?: asObjectDeclaration()

    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration? =
        asClassDeclaration() ?: asInterfaceDeclaration()

    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration? =
        asInterfaceDeclaration() ?: asObjectDeclaration()

    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration? =
        asClassDeclaration() ?: asInterfaceDeclaration() ?: asObjectDeclaration()

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? =
        koDeclarationCastProviderDeclaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? =
        koDeclarationCastProviderDeclaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? =
        koDeclarationCastProviderDeclaration as? KoKotlinTypeDeclaration

    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration? =
        if ((koDeclarationCastProviderDeclaration as? KoDeclarationCastProvider)?.isKotlinBasicType == true) asKotlinTypeDeclaration() else null

    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration? =
        if ((koDeclarationCastProviderDeclaration as? KoDeclarationCastProvider)?.isKotlinCollectionType == true) asKotlinTypeDeclaration() else null

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? =
        koDeclarationCastProviderDeclaration as? KoTypeParameterDeclaration

    override fun asExternalDeclaration(): KoExternalDeclaration? =
        koDeclarationCastProviderDeclaration as? KoExternalDeclaration

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("asExternalDeclaration"))
    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = asExternalDeclaration()

    override fun asFunctionDeclaration(): KoFunctionDeclaration? = koDeclarationCastProviderDeclaration as? KoFunctionDeclaration

    override fun asPropertyDeclaration(): KoPropertyDeclaration? = koDeclarationCastProviderDeclaration as? KoPropertyDeclaration

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isClass
            else -> asClassDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asClassDeclaration()?.fullyQualifiedName

    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isObject
            else -> asObjectDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asObjectDeclaration()?.fullyQualifiedName

    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isInterface
            else -> asInterfaceDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asInterfaceDeclaration()?.fullyQualifiedName

    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean =
        hasClassDeclaration(predicate) || hasObjectDeclaration(predicate)

    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean = hasClassDeclarationOf(kClass) || hasObjectDeclarationOf(kClass)

    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean =
        hasClassDeclaration(predicate) || hasInterfaceDeclaration(predicate)

    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        hasClassDeclarationOf(kClass) || hasInterfaceDeclarationOf(kClass)

    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        hasInterfaceDeclaration(predicate) || hasObjectDeclaration(predicate)

    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        hasInterfaceDeclarationOf(kClass) || hasObjectDeclarationOf(kClass)

    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        hasClassDeclaration(predicate) || hasInterfaceDeclaration(predicate) || hasObjectDeclaration(predicate)

    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        hasClassDeclarationOf(kClass) || hasInterfaceDeclarationOf(kClass) || hasObjectDeclarationOf(kClass)

    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isTypeAlias
            else -> asTypeAliasDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isImportAlias
            else -> asImportAliasDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isKotlinType
            else -> asKotlinTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asKotlinTypeDeclaration()?.fullyQualifiedName

    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isKotlinBasicType
            else -> asKotlinBasicTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asKotlinBasicTypeDeclaration()?.fullyQualifiedName

    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isKotlinCollectionType
            else -> asKotlinCollectionTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asKotlinCollectionTypeDeclaration()?.fullyQualifiedName

    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isTypeParameter
            else -> asTypeParameterDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isExternal
            else -> asExternalDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asExternalDeclaration()?.fullyQualifiedName

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("hasExternalDeclaration"))
    override fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean = hasExternalDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("hasExternalDeclarationOf"))
    override fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean = hasExternalDeclarationOf(kClass)

    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isFunction
            else -> asFunctionDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asFunctionDeclaration()?.fullyQualifiedName

    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> isProperty
            else -> asPropertyDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean = kClass.qualifiedName == asPropertyDeclaration()?.fullyQualifiedName
}
