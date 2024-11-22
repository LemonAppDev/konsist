package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsFunctionTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsMutableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoStarProjectionProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal class KoTypeDeclarationCore private constructor(
    override val ktTypeReference: KtTypeReference?,
    override val ktNameReferenceExpression: KtNameReferenceExpression?,
    override val ktTypeProjection: KtTypeProjection?,
    override val containingDeclaration: KoBaseDeclaration,
) : KoTypeDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoPathProviderCore,
    KoLocationProviderCore,
    KoNullableProviderCore,
    KoIsNullableProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoStarProjectionProviderCore,
    KoSourceAndAliasTypeProviderCore,
    KoSourceTypeProviderCore,
    KoGenericTypeProviderCore,
    KoIsGenericTypeProviderCore,
    KoIsGenericProviderCore,
    KoIsFunctionTypeProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore,
    KoAnnotationProviderCore,
    @RemoveInVersion("0.19.0")
    KoDeclarationCastProviderCore,
    KoSourceDeclarationProviderCore,
    KoIsMutableTypeProviderCore,
    KoTypeArgumentProviderCore,
    KoFunctionTypeDeclarationProviderCore {
    // Ensure that at least one of the parameters is not null
    init {
        require(ktTypeReference != null || ktNameReferenceExpression != null || ktTypeProjection != null) {
            "Either KtTypeReference, KtNameReferenceExpression or KtTypeProjection must be provided"
        }
    }

    override val psiElement: PsiElement by lazy {
        ktTypeReference ?: ktNameReferenceExpression ?: ktTypeProjection
            ?: error("KtTypeReference, KtNameReferenceExpression and KtTypeProjection are null")
    }

    override val ktElement: KtElement by lazy {
        ktTypeReference ?: ktNameReferenceExpression ?: ktTypeProjection
            ?: error("KtTypeReference, KtNameReferenceExpression and KtTypeProjection are null")
    }

    override val ktUserType: KtUserType? by lazy {
        ktTypeReference
            ?.children
            // The last item is chosen because when a type is preceded by an annotation or modifier,
            // the type being searched for is the last item in the list.
            ?.filterIsInstance<KtUserType>()
            ?.lastOrNull()
    }

    override val ktFunctionType: KtFunctionType? by lazy {
        ktTypeReference
            ?.children
            // The last item is chosen because when a type is preceded by an annotation or modifier,
            // the type being searched for is the last item in the list.
            ?.filterIsInstance<KtFunctionType>()
            ?.lastOrNull()
    }

    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy { ktTypeReference?.annotationEntries }

    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration? by lazy { sourceDeclaration }

    override val name: String by lazy {
        val typeReference =
            ktTypeReference
                ?.children
                // The last item is chosen because when a type is preceded by an annotation or modifier,
                // the type being searched for is the last item in the list.
                ?.lastOrNull()
                ?: ktNameReferenceExpression
                ?: ktTypeProjection

        if (typeReference is KtNullableType) {
            typeReference.children.firstOrNull()?.text ?: ""
        } else {
            typeReference?.text ?: ""
        }
    }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    @RemoveInVersion("0.18.0")
    override val isGenericType: Boolean by lazy { super<KoIsGenericTypeProviderCore>.isGenericType }

    @RemoveInVersion("0.18.0")
    override val isNullable: Boolean by lazy { super<KoIsNullableProviderCore>.isNullable }

    @RemoveInVersion("0.19.0")
    override val sourceType: String by lazy { super<KoSourceTypeProviderCore>.sourceType }

    @RemoveInVersion("0.19.0")
    override val bareSourceType: String by lazy { super<KoSourceTypeProviderCore>.bareSourceType }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClass"))
    override val isClass: Boolean by lazy { super<KoDeclarationCastProviderCore>.isClass }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isObject"))
    override val isObject: Boolean by lazy { super<KoDeclarationCastProviderCore>.isObject }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isInterface"))
    override val isInterface: Boolean by lazy { super<KoDeclarationCastProviderCore>.isInterface }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrObject"))
    override val isClassOrObject: Boolean by lazy { super<KoDeclarationCastProviderCore>.isClassOrObject }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrInterface"))
    override val isClassOrInterface: Boolean by lazy { super<KoDeclarationCastProviderCore>.isClassOrInterface }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isInterfaceOrObject"))
    override val isInterfaceOrObject: Boolean by lazy { super<KoDeclarationCastProviderCore>.isInterfaceOrObject }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isClassOrInterfaceOrObject"))
    override val isClassOrInterfaceOrObject: Boolean by lazy { super<KoDeclarationCastProviderCore>.isClassOrInterfaceOrObject }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isTypeAlias"))
    override val isTypeAlias: Boolean by lazy { super<KoDeclarationCastProviderCore>.isTypeAlias }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isImportAlias"))
    override val isImportAlias: Boolean by lazy { super<KoDeclarationCastProviderCore>.isImportAlias }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinType"))
    override val isKotlinType: Boolean by lazy { super<KoDeclarationCastProviderCore>.isKotlinType }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinBasicType"))
    override val isKotlinBasicType: Boolean by lazy { super<KoDeclarationCastProviderCore>.isKotlinBasicType }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isKotlinCollectionType"))
    override val isKotlinCollectionType: Boolean by lazy { super<KoDeclarationCastProviderCore>.isKotlinCollectionType }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isTypeParameter"))
    override val isTypeParameter: Boolean by lazy { super<KoDeclarationCastProviderCore>.isTypeParameter }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isExternal"))
    override val isExternal: Boolean by lazy { super<KoDeclarationCastProviderCore>.isExternal }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isFunction"))
    override val isFunction: Boolean by lazy { super<KoDeclarationCastProviderCore>.isFunction }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.isProperty"))
    override val isProperty: Boolean by lazy { super<KoDeclarationCastProviderCore>.isProperty }

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassDeclaration()"))
    override fun asClassDeclaration(): KoClassDeclaration? = super<KoDeclarationCastProviderCore>.asClassDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asObjectDeclaration()"))
    override fun asObjectDeclaration(): KoObjectDeclaration? = super<KoDeclarationCastProviderCore>.asObjectDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asInterfaceDeclaration()"))
    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = super<KoDeclarationCastProviderCore>.asInterfaceDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassOrObjectDeclaration()"))
    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrObjectDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asClassOrInterfaceDeclaration()"))
    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrInterfaceDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asInterfaceOrObjectDeclaration()"))
    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asInterfaceOrObjectDeclaration()

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.asClassOrInterfaceOrObjectDeclaration()"),
    )
    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrInterfaceOrObjectDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asTypeAliasDeclaration()"))
    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = super<KoDeclarationCastProviderCore>.asTypeAliasDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asImportAliasDeclaration()"))
    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = super<KoDeclarationCastProviderCore>.asImportAliasDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asKotlinTypeDeclaration()"))
    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = super<KoDeclarationCastProviderCore>.asKotlinTypeDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asKotlinBasicTypeDeclaration()"))
    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration? =
        super<KoDeclarationCastProviderCore>.asKotlinBasicTypeDeclaration()

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.asKotlinCollectionTypeDeclaration()"),
    )
    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration? =
        super<KoDeclarationCastProviderCore>.asKotlinCollectionTypeDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asTypeParameterDeclaration()"))
    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? =
        super<KoDeclarationCastProviderCore>.asTypeParameterDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asExternalDeclaration()"))
    override fun asExternalDeclaration(): KoExternalDeclaration? = super<KoDeclarationCastProviderCore>.asExternalDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asFunctionDeclaration()"))
    override fun asFunctionDeclaration(): KoFunctionDeclaration? = super<KoDeclarationCastProviderCore>.asFunctionDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.asPropertyDeclaration()"))
    override fun asPropertyDeclaration(): KoPropertyDeclaration? = super<KoDeclarationCastProviderCore>.asPropertyDeclaration()

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassDeclaration()"))
    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassDeclarationOf()"))
    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasClassDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasObjectDeclaration()"))
    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasObjectDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasObjectDeclarationOf()"))
    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasObjectDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasInterfaceDeclaration()"))
    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasInterfaceDeclarationOf()"))
    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrObjectDeclaration()"))
    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrObjectDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrObjectDeclarationOf()"))
    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrObjectDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasClassOrInterfaceDeclaration()"))
    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceDeclaration(predicate)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceDeclarationOf()"),
    )
    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceDeclarationOf(kClass)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasInterfaceOrObjectDeclaration()"),
    )
    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceOrObjectDeclaration(predicate)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasInterfaceOrObjectDeclarationOf()"),
    )
    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceOrObjectDeclarationOf(kClass)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceOrObjectDeclaration()"),
    )
    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceOrObjectDeclaration(predicate)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasClassOrInterfaceOrObjectDeclarationOf()"),
    )
    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceOrObjectDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasTypeAliasDeclaration()"))
    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasTypeAliasDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasImportAliasDeclaration()"))
    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasImportAliasDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinTypeDeclaration()"))
    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinTypeDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinTypeDeclarationOf()"))
    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinTypeDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasKotlinBasicTypeDeclaration()"))
    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinBasicTypeDeclaration(predicate)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinBasicTypeDeclarationOf()"),
    )
    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinBasicTypeDeclarationOf(kClass)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinCollectionTypeDeclaration()"),
    )
    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinCollectionTypeDeclaration(predicate)

    @Deprecated(
        "Will be removed in version 0.19.0",
        ReplaceWith("sourceDeclaration?.hasKotlinCollectionTypeDeclarationOf()"),
    )
    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinCollectionTypeDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasTypeParameterDeclaration()"))
    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasTypeParameterDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasExternalDeclaration()"))
    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasExternalDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasExternalDeclarationOf()"))
    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasExternalDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasFunctionDeclaration()"))
    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasFunctionDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasFunctionDeclarationOf()"))
    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasFunctionDeclarationOf(kClass)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasPropertyDeclaration()"))
    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasPropertyDeclaration(predicate)

    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("sourceDeclaration?.hasPropertyDeclarationOf()"))
    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasPropertyDeclarationOf(kClass)

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        // Factory method for KtTypeReference
        internal fun getInstance(
            ktTypeReference: KtTypeReference,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, containingDeclaration) {
                KoTypeDeclarationCore(ktTypeReference, null, null, containingDeclaration)
            }

        // Factory method for KtNameReferenceExpression
        internal fun getInstance(
            ktNameReferenceExpression: KtNameReferenceExpression,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktNameReferenceExpression, containingDeclaration) {
                KoTypeDeclarationCore(null, ktNameReferenceExpression, null, containingDeclaration)
            }

        // Factory method for KtTypeProjection
        internal fun getInstance(
            ktTypeProjection: KtTypeProjection,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeProjection, containingDeclaration) {
                KoTypeDeclarationCore(null, null, ktTypeProjection, containingDeclaration)
            }
    }
}
