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
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoTypeDeclarationProviderCore :
    KoTypeDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore {
    val koTypeDeclarationProviderDeclaration: KoSourceDeclaration?
        get() = null

    override fun asClassDeclaration(): KoClassDeclaration? = koTypeDeclarationProviderDeclaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? = koTypeDeclarationProviderDeclaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = koTypeDeclarationProviderDeclaration as? KoInterfaceDeclaration

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = koTypeDeclarationProviderDeclaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = koTypeDeclarationProviderDeclaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = koTypeDeclarationProviderDeclaration as? KoKotlinTypeDeclaration

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? =
        koTypeDeclarationProviderDeclaration as? KoTypeParameterDeclaration

    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = koTypeDeclarationProviderDeclaration as? KoExternalDeclaration

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
