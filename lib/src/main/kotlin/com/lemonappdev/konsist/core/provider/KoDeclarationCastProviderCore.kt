package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.util.TypeUtil
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoDeclarationCastProviderCore :
    KoDeclarationCastProvider,
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

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("isExternal"))
    override val isExternalType: Boolean
        get() = isExternal

    override fun asClassDeclaration(): KoClassDeclaration? = koDeclarationCastProviderDeclaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? = koDeclarationCastProviderDeclaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = koDeclarationCastProviderDeclaration as? KoInterfaceDeclaration

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = koDeclarationCastProviderDeclaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = koDeclarationCastProviderDeclaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = koDeclarationCastProviderDeclaration as? KoKotlinTypeDeclaration

    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration? =
        if (koDeclarationCastProviderDeclaration?.isKotlinBasicType == true) asKotlinTypeDeclaration() else null

    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration? =
        if (koDeclarationCastProviderDeclaration?.isKotlinCollectionType == true) asKotlinTypeDeclaration() else null

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? =
        koDeclarationCastProviderDeclaration as? KoTypeParameterDeclaration

    override fun asExternalDeclaration(): KoExternalDeclaration? = koDeclarationCastProviderDeclaration as? KoExternalDeclaration

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("asExternalDeclaration"))
    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = asExternalDeclaration()

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
}
