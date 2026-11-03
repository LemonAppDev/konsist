package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
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
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsNullableProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoSourceTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
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
    KoIsNullableProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoIsGenericProvider,
    KoIsFunctionTypeProvider,
    KoSourceTypeProvider,
    KoPackageProvider,
    KoResideInPackageProvider,
    KoAnnotationProvider,
    KoSourceDeclarationProvider,
    KoIsMutableTypeProvider,
    KoTypeArgumentProvider,
    KoFunctionTypeDeclarationProvider,
    KoDeclarationCastProvider {
    /**
     * Package of the file where the type is referenced (the use site), not the package where the type is declared,
     * e.g. for `fun Foo.bar()` declared in `com.app`, `receiverType.packagee` is `com.app` even if `Foo` is declared
     * in `com.lib`. [residesInPackage] and [residesOutsidePackage] check this package too.
     *
     * To get the package where the type is declared use [sourceDeclaration], e.g.
     * `type.sourceDeclaration?.asClassDeclaration()?.packagee`.
     */
    override val packagee: KoPackageDeclaration?

    override val isClass: Boolean

    override val isObject: Boolean

    override val isInterface: Boolean

    override val isClassOrObject: Boolean

    override val isClassOrInterface: Boolean

    override val isInterfaceOrObject: Boolean

    override val isClassOrInterfaceOrObject: Boolean

    override val isTypeAlias: Boolean

    override val isImportAlias: Boolean

    override val isKotlinType: Boolean

    override val isKotlinBasicType: Boolean

    override val isKotlinCollectionType: Boolean

    override val isTypeParameter: Boolean

    override val isExternal: Boolean

    override val isFunction: Boolean

    override val isProperty: Boolean

    override fun asClassDeclaration(): KoClassDeclaration?

    override fun asObjectDeclaration(): KoObjectDeclaration?

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration?

    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration?

    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration?

    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration?

    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration?

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration?

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration?

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration?

    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration?

    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration?

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration?

    override fun asExternalDeclaration(): KoExternalDeclaration?

    override fun asFunctionDeclaration(): KoFunctionDeclaration?

    override fun asPropertyDeclaration(): KoPropertyDeclaration?

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean

    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean

    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean

    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean

    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean

    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean

    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean

    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean

    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean

    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean

    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean

    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean

    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean

    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean

    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean

    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean
}
