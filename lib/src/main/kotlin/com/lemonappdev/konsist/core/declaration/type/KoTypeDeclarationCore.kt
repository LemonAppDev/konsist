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
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoIsFunctionTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericProviderCore
import com.lemonappdev.konsist.core.provider.KoIsMutableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallExpression
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
    KoIsNullableProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoSourceTypeProviderCore,
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

    override val psiElement: PsiElement
        get() = ktElement

    override val ktElement: KtElement
        get() =
            ktTypeReference ?: ktNameReferenceExpression ?: ktTypeProjection
                ?: error("KtTypeReference, KtNameReferenceExpression and KtTypeProjection are null")

    override val ktUserType: KtUserType?
        get() = extractEffectiveType<KtUserType>(ktTypeReference)

    override val ktFunctionType: KtFunctionType?
        get() = extractEffectiveType<KtFunctionType>(ktTypeReference)

    private inline fun <reified T : KtElement> extractEffectiveType(ktTypeReference: KtTypeReference?): T? {
        val children = ktTypeReference?.children

        val effectiveChildren =
            when (val nullableType = children?.filterIsInstance<KtNullableType>()?.firstOrNull()) {
                null -> children
                else -> nullableType.children
            }

        // The last item is chosen because when a type is preceded by an annotation or modifier,
        // the type being searched for is the last item in the list.
        return effectiveChildren
            ?.filterIsInstance<T>()
            ?.lastOrNull()
    }

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktTypeReference?.annotationEntries

    // Constructor call the type is inferred from, e.g. `Foo<Bar>()` in `val foo = Foo<Bar>()`
    private val ktCallExpression: KtCallExpression?
        get() = ktNameReferenceExpression?.parent as? KtCallExpression

    override val ktTypeProjections: List<KtTypeProjection>
        get() = ktCallExpression?.typeArguments ?: super<KoTypeArgumentProviderCore>.ktTypeProjections

    override val text: String
        get() = if (ktCallExpression != null) name else super<KoTextProviderCore>.text

    @Volatile
    private var cachedKoDeclarationCastProviderDeclaration: Any? = KoPropertyCache.NOT_COMPUTED

    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration?
        get() =
            KoPropertyCache.getOrCompute(cachedKoDeclarationCastProviderDeclaration, {
                cachedKoDeclarationCastProviderDeclaration = it
            }) { sourceDeclaration }

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() =
            KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) {
                val typeReference =
                    ktTypeReference
                        ?.children
                        // The last item is chosen because when a type is preceded by an annotation or modifier,
                        // the type being searched for is the last item in the list.
                        ?.lastOrNull()
                        ?: ktNameReferenceExpression
                        ?: ktTypeProjection

                val callExpression = ktCallExpression

                when {
                    callExpression != null -> {
                        TypeUtil.getTypeName(typeReference) + callExpression.typeArgumentList?.text.orEmpty()
                    }

                    typeReference is KtNullableType -> {
                        typeReference.children.firstOrNull()?.text ?: ""
                    }

                    else -> {
                        typeReference?.text ?: ""
                    }
                }
            }

    @Volatile
    private var cachedIsMutableType: Any? = KoPropertyCache.NOT_COMPUTED

    override val isMutableType: Boolean
        get() =
            KoPropertyCache.getOrCompute(cachedIsMutableType, { cachedIsMutableType = it }) {
                super<KoIsMutableTypeProviderCore>.isMutableType
            }

    @Volatile
    private var cachedHasMutableTypeArgument: Any? = KoPropertyCache.NOT_COMPUTED

    override val hasMutableTypeArgument: Boolean
        get() =
            KoPropertyCache.getOrCompute(cachedHasMutableTypeArgument, { cachedHasMutableTypeArgument = it }) {
                super<KoIsMutableTypeProviderCore>.hasMutableTypeArgument
            }

    @Volatile
    private var cachedIsImmutableType: Any? = KoPropertyCache.NOT_COMPUTED

    override val isImmutableType: Boolean
        get() =
            KoPropertyCache.getOrCompute(cachedIsImmutableType, { cachedIsImmutableType = it }) {
                super<KoIsMutableTypeProviderCore>.isImmutableType
            }

    @Volatile
    private var cachedHasImmutableTypeArgument: Any? = KoPropertyCache.NOT_COMPUTED

    override val hasImmutableTypeArgument: Boolean
        get() =
            KoPropertyCache.getOrCompute(cachedHasImmutableTypeArgument, { cachedHasImmutableTypeArgument = it }) {
                super<KoIsMutableTypeProviderCore>.hasImmutableTypeArgument
            }

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() = KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) { containingFile.packagee }

    @Volatile
    private var cachedSourceType: Any? = KoPropertyCache.NOT_COMPUTED

    @RemoveInVersion("0.19.0")
    override val sourceType: String
        get() = KoPropertyCache.getOrCompute(cachedSourceType, { cachedSourceType = it }) { super<KoSourceTypeProviderCore>.sourceType }

    @Volatile
    private var cachedBareSourceType: Any? = KoPropertyCache.NOT_COMPUTED

    @RemoveInVersion("0.19.0")
    override val bareSourceType: String
        get() =
            KoPropertyCache.getOrCompute(
                cachedBareSourceType,
                { cachedBareSourceType = it },
            ) { super<KoSourceTypeProviderCore>.bareSourceType }

    override val isClass: Boolean
        get() = super<KoDeclarationCastProviderCore>.isClass

    override val isObject: Boolean
        get() = super<KoDeclarationCastProviderCore>.isObject

    override val isInterface: Boolean
        get() = super<KoDeclarationCastProviderCore>.isInterface

    override val isClassOrObject: Boolean
        get() = super<KoDeclarationCastProviderCore>.isClassOrObject

    override val isClassOrInterface: Boolean
        get() = super<KoDeclarationCastProviderCore>.isClassOrInterface

    override val isInterfaceOrObject: Boolean
        get() = super<KoDeclarationCastProviderCore>.isInterfaceOrObject

    override val isClassOrInterfaceOrObject: Boolean
        get() = super<KoDeclarationCastProviderCore>.isClassOrInterfaceOrObject

    override val isTypeAlias: Boolean
        get() = super<KoDeclarationCastProviderCore>.isTypeAlias

    override val isImportAlias: Boolean
        get() = super<KoDeclarationCastProviderCore>.isImportAlias

    override val isKotlinType: Boolean
        get() = super<KoDeclarationCastProviderCore>.isKotlinType

    override val isKotlinBasicType: Boolean
        get() = super<KoDeclarationCastProviderCore>.isKotlinBasicType

    override val isKotlinCollectionType: Boolean
        get() = super<KoDeclarationCastProviderCore>.isKotlinCollectionType

    override val isTypeParameter: Boolean
        get() = super<KoDeclarationCastProviderCore>.isTypeParameter

    override val isExternal: Boolean
        get() = super<KoDeclarationCastProviderCore>.isExternal

    override val isFunction: Boolean
        get() = super<KoDeclarationCastProviderCore>.isFunction

    override val isProperty: Boolean
        get() = super<KoDeclarationCastProviderCore>.isProperty

    override fun asClassDeclaration(): KoClassDeclaration? = super<KoDeclarationCastProviderCore>.asClassDeclaration()

    override fun asObjectDeclaration(): KoObjectDeclaration? = super<KoDeclarationCastProviderCore>.asObjectDeclaration()

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? = super<KoDeclarationCastProviderCore>.asInterfaceDeclaration()

    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrObjectDeclaration()

    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrInterfaceDeclaration()

    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asInterfaceOrObjectDeclaration()

    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration? =
        super<KoDeclarationCastProviderCore>.asClassOrInterfaceOrObjectDeclaration()

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? = super<KoDeclarationCastProviderCore>.asTypeAliasDeclaration()

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? = super<KoDeclarationCastProviderCore>.asImportAliasDeclaration()

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? = super<KoDeclarationCastProviderCore>.asKotlinTypeDeclaration()

    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration? =
        super<KoDeclarationCastProviderCore>.asKotlinBasicTypeDeclaration()

    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration? =
        super<KoDeclarationCastProviderCore>.asKotlinCollectionTypeDeclaration()

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? =
        super<KoDeclarationCastProviderCore>.asTypeParameterDeclaration()

    override fun asExternalDeclaration(): KoExternalDeclaration? = super<KoDeclarationCastProviderCore>.asExternalDeclaration()

    override fun asFunctionDeclaration(): KoFunctionDeclaration? = super<KoDeclarationCastProviderCore>.asFunctionDeclaration()

    override fun asPropertyDeclaration(): KoPropertyDeclaration? = super<KoDeclarationCastProviderCore>.asPropertyDeclaration()

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassDeclaration(predicate)

    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasClassDeclarationOf(kClass)

    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasObjectDeclaration(predicate)

    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean = super<KoDeclarationCastProviderCore>.hasObjectDeclarationOf(kClass)

    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceDeclaration(predicate)

    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceDeclarationOf(kClass)

    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrObjectDeclaration(predicate)

    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrObjectDeclarationOf(kClass)

    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceDeclaration(predicate)

    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceDeclarationOf(kClass)

    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceOrObjectDeclaration(predicate)

    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasInterfaceOrObjectDeclarationOf(kClass)

    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceOrObjectDeclaration(predicate)

    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasClassOrInterfaceOrObjectDeclarationOf(kClass)

    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasTypeAliasDeclaration(predicate)

    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasImportAliasDeclaration(predicate)

    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinTypeDeclaration(predicate)

    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinTypeDeclarationOf(kClass)

    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinBasicTypeDeclaration(predicate)

    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinBasicTypeDeclarationOf(kClass)

    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinCollectionTypeDeclaration(predicate)

    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasKotlinCollectionTypeDeclarationOf(kClass)

    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasTypeParameterDeclaration(predicate)

    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasExternalDeclaration(predicate)

    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasExternalDeclarationOf(kClass)

    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasFunctionDeclaration(predicate)

    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasFunctionDeclarationOf(kClass)

    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean =
        super<KoDeclarationCastProviderCore>.hasPropertyDeclaration(predicate)

    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean =
        super<KoDeclarationCastProviderCore>.hasPropertyDeclarationOf(kClass)

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
