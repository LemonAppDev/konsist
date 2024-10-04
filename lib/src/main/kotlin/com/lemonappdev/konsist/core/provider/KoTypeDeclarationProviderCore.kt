package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
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
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal interface KoTypeDeclarationProviderCore :
    KoTypeDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoSourceDeclarationProviderCore {
    @Deprecated("Will be removed in version 0.18.0", replaceWith = ReplaceWith("sourceDeclaration"))
    override val declaration: KoBaseTypeDeclaration
        get() = sourceDeclaration

    override fun asClassDeclaration(): KoClassDeclaration? = sourceDeclaration as? KoClassDeclaration

    override fun asObjectDeclaration(): KoObjectDeclaration? = sourceDeclaration as? KoObjectDeclaration

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = sourceDeclaration as? KoInterfaceDeclaration

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = sourceDeclaration as? KoTypeAliasDeclaration

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = sourceDeclaration as? KoImportAliasDeclaration

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = sourceDeclaration as? KoKotlinTypeDeclaration

    override fun asFunctionTypeDeclaration(): KoFunctionTypeDeclaration? = sourceDeclaration as? KoFunctionTypeDeclaration

    override fun asGenericTypeDeclaration(): KoGenericTypeDeclaration? = sourceDeclaration as? KoGenericTypeDeclaration

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? = sourceDeclaration as? KoTypeParameterDeclaration

    override fun asExternalTypeDeclaration(): KoExternalDeclaration? = sourceDeclaration as? KoExternalDeclaration

    override fun asStarProjectionDeclaration(): KoStarProjectionDeclaration? = sourceDeclaration as? KoStarProjectionDeclaration

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

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean =
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

    override fun hasStarProjectionDeclaration(predicate: ((KoStarProjectionDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asStarProjectionDeclaration() != null
            else -> asStarProjectionDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> asExternalTypeDeclaration() != null
            else -> asExternalTypeDeclaration()?.let { predicate(it) } ?: false
        }

    override fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean =
        kClass.qualifiedName == asExternalTypeDeclaration()?.fullyQualifiedName
}
