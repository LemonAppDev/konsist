package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsNullableProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoSourceTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import kotlin.reflect.KClass

/**
 * Represents a Kotlin type declaration.
 */
@Suppress("detekt.TooManyFunctions")
interface KoTypeDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoPathProvider,
    KoLocationProvider,
    KoNullableProvider,
    KoIsNullableProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoGenericTypeProvider,
    KoIsGenericTypeProvider,
    KoIsGenericProvider,
    KoIsFunctionTypeProvider,
    KoSourceAndAliasTypeProvider,
    KoSourceTypeProvider,
    KoPackageProvider,
    KoResideInPackageProvider,
    KoAnnotationProvider,
    KoSourceDeclarationProvider,
    KoIsMutableTypeProvider,
    KoTypeArgumentProvider,
    KoFunctionTypeDeclarationProvider,
    @RemoveInVersion("0.19.0")
    KoDeclarationCastProvider {
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClass"))
    override val isClass: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isObject"))
    override val isObject: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isInterface"))
    override val isInterface: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrObject"))
    override val isClassOrObject: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrInterface"))
    override val isClassOrInterface: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isInterfaceOrObject"))
    override val isInterfaceOrObject: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrInterfaceOrObject"))
    override val isClassOrInterfaceOrObject: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isTypeAlias"))
    override val isTypeAlias: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isImportAlias"))
    override val isImportAlias: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinType"))
    override val isKotlinType: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinBasicType"))
    override val isKotlinBasicType: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinCollectionType"))
    override val isKotlinCollectionType: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isTypeParameter"))
    override val isTypeParameter: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isExternal"))
    override val isExternal: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isFunction"))
    override val isFunction: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isProperty"))
    override val isProperty: Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassDeclaration()"))
    override fun asClassDeclaration(): KoClassDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asObjectDeclaration()"))
    override fun asObjectDeclaration(): KoObjectDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asInterfaceDeclaration()"))
    override fun asInterfaceDeclaration(): KoInterfaceDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassOrObjectDeclaration()"))
    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassOrInterfaceDeclaration()"))
    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asInterfaceOrObjectDeclaration()"))
    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration?

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.asClassOrInterfaceOrObjectDeclaration()"),
    )
    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asTypeAliasDeclaration()"))
    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asImportAliasDeclaration()"))
    override fun asImportAliasDeclaration(): KoImportAliasDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asKotlinTypeDeclaration()"))
    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asKotlinBasicTypeDeclaration()"))
    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration?

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.asKotlinCollectionTypeDeclaration()"),
    )
    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asTypeParameterDeclaration()"))
    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asExternalDeclaration()"))
    override fun asExternalDeclaration(): KoExternalDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asFunctionDeclaration()"))
    override fun asFunctionDeclaration(): KoFunctionDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asPropertyDeclaration()"))
    override fun asPropertyDeclaration(): KoPropertyDeclaration?

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassDeclaration()"))
    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassDeclarationOf()"))
    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasObjectDeclaration()"))
    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasObjectDeclarationOf()"))
    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasInterfaceDeclaration()"))
    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasInterfaceDeclarationOf()"))
    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrObjectDeclaration()"))
    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrObjectDeclarationOf()"))
    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrInterfaceDeclaration()"))
    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceDeclarationOf()"),
    )
    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasInterfaceOrObjectDeclaration()"),
    )
    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasInterfaceOrObjectDeclarationOf()"),
    )
    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceOrObjectDeclaration()"),
    )
    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceOrObjectDeclarationOf()"),
    )
    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasTypeAliasDeclaration()"))
    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasImportAliasDeclaration()"))
    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinTypeDeclaration()"))
    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinTypeDeclarationOf()"))
    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinBasicTypeDeclaration()"))
    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinBasicTypeDeclarationOf()"),
    )
    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinCollectionTypeDeclaration()"),
    )
    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinCollectionTypeDeclarationOf()"),
    )
    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasTypeParameterDeclaration()"))
    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasExternalDeclaration()"))
    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasExternalDeclarationOf()"))
    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasFunctionDeclaration()"))
    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasFunctionDeclarationOf()"))
    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasPropertyDeclaration()"))
    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasPropertyDeclarationOf()"))
    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean
}
