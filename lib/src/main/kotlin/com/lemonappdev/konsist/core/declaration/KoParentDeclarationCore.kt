package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.ClasspathUtil
import com.lemonappdev.konsist.core.util.TypeKind
import com.lemonappdev.konsist.core.util.TypeKindUtil
import com.lemonappdev.konsist.core.util.TypeResolver
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression
import org.jetbrains.kotlin.psi.KtDeclarationModifierList
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

/*
 * Parent written in the code, or the parent declared outside the project (in a library or the Kotlin standard library)
 * read from the classpath when searching the indirect parents (e.g. `View` for `class MyView : LinearLayout(context)`).
 * The parent read from the classpath is not written in the code, so its [ktSuperTypeListEntry] is the parent through
 * which it was found (it is the source of the location, path and package).
 */
internal class KoParentDeclarationCore private constructor(
    override val ktSuperTypeListEntry: KtSuperTypeListEntry,
    override val containingDeclaration: KoBaseDeclaration,
    private val classpathSupertype: ClasspathUtil.Supertype?,
) : KoParentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore,
    KoTextProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoLocationProviderCore,
    KoAnnotationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoArgumentProviderCore,
    KoInterfaceDelegateProviderCore {
    override val psiElement: PsiElement
        get() = ktSuperTypeListEntry

    override val ktElement: KtElement
        get() = ktSuperTypeListEntry

    override val ktTypeReference: KtTypeReference?
        get() = null

    override val ktNameReferenceExpression: KtNameReferenceExpression?
        get() = null

    override val ktTypeProjection: KtTypeProjection?
        get() = null

    override val ktUserType: KtUserType?
        get() = if (classpathSupertype == null) ktSuperTypeListEntry.typeAsUserType else null

    override val packagee: KoPackageDeclaration?
        get() = super<KoPackageDeclarationProviderCore>.packagee

    @Volatile
    private var cachedKtAnnotationEntries: Any? = KoPropertyCache.NOT_COMPUTED

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() =
            KoPropertyCache.getOrCompute(cachedKtAnnotationEntries, { cachedKtAnnotationEntries = it }) {
                if (classpathSupertype != null) return@getOrCompute null

                val children = ktSuperTypeListEntry.children

                val targetChildren =
                    children
                        .filterIsInstance<KtConstructorCalleeExpression>()
                        .firstOrNull()
                        ?.children
                        ?: children

                targetChildren
                    .firstOrNull { it is KtTypeReference }
                    ?.children
                    ?.firstOrNull { it is KtDeclarationModifierList }
                    ?.children
                    ?.filterIsInstance<KtAnnotationEntry>()
            }

    @Volatile
    private var cachedSourceDeclaration: Any? = KoPropertyCache.NOT_COMPUTED

    override val sourceDeclaration: KoDeclarationCastProvider?
        get() =
            KoPropertyCache.getOrCompute(cachedSourceDeclaration, { cachedSourceDeclaration = it }) {
                if (classpathSupertype != null) return@getOrCompute getClasspathSourceDeclaration(classpathSupertype)

                val name =
                    TypeUtil.getTypeName(ktUserType)
                        ?: ktSuperTypeListEntry
                            .text
                            .substringBefore(" ")
                            .substringBefore("(")
                            .substringBefore("<")

                // Nested types of the class are not visible in its parent list
                val fullyQualifiedName =
                    TypeResolver.resolveFullyQualifiedName(
                        name,
                        containingFile,
                        (containingDeclaration as? KoContainingDeclarationProvider)?.containingDeclaration,
                    )

                (
                    containingFile.imports.firstOrNull { it.alias?.name == name }?.alias
                        ?: fullyQualifiedName?.let { TypeResolver.getTypeDeclaration(it, containingFile) }
                        ?: if (fullyQualifiedName != null && TypeUtil.isKotlinTypeFullyQualifiedName(fullyQualifiedName)) {
                            // Pass only the type, without constructor arguments (`Foo(bar)`) or delegation (`Foo by bar`)
                            ktUserType?.let { KoKotlinTypeDeclarationCore.getInstance(it, containingDeclaration) }
                        } else {
                            null
                        }
                        ?: KoExternalDeclarationCore.getInstance(name, fullyQualifiedName ?: name, ktSuperTypeListEntry)
                )
                    as? KoDeclarationCastProvider
            }

    @Volatile
    private var cachedKind: Any? = KoPropertyCache.NOT_COMPUTED

    internal val kind: TypeKind
        get() =
            KoPropertyCache.getOrCompute(cachedKind, { cachedKind = it }) {
                TypeKindUtil.getParentKind(sourceDeclaration, ktSuperTypeListEntry)
            }

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() =
            KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) {
                if (classpathSupertype != null) return@getOrCompute classpathSupertype.name

                val children = ktElement.children

                children
                    .filterIsInstance<KtTypeReference>()
                    .firstOrNull()
                    ?.text
                    ?: children
                        .flatMap { it.children.toList() }
                        .filterIsInstance<KtTypeReference>()
                        .firstOrNull()
                        ?.text
                    ?: text
            }

    @Volatile
    private var cachedArguments: Any? = KoPropertyCache.NOT_COMPUTED

    override val arguments: List<KoArgumentDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedArguments, { cachedArguments = it }) {
                if (classpathSupertype != null) return@getOrCompute emptyList()

                ktSuperTypeListEntry
                    .children
                    .filterIsInstance<KtValueArgumentList>()
                    .firstOrNull()
                    ?.children
                    ?.filterIsInstance<KtValueArgument>()
                    ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
                    .orEmpty()
            }

    override val text: String
        get() = classpathSupertype?.name ?: ktSuperTypeListEntry.text

    override val delegateName: String?
        get() = if (classpathSupertype == null) super.delegateName else null

    internal val isClasspathParent: Boolean
        get() = classpathSupertype != null

    @Volatile
    private var cachedClasspathParents: Any? = KoPropertyCache.NOT_COMPUTED

    /*
     * Direct and indirect parents of the parent declared outside the project, read from the classpath. Empty when the parent
     * is declared in the project (its parents are written in the code) or it is not on the classpath.
     */
    internal val classpathParents: List<KoParentDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedClasspathParents, { cachedClasspathParents = it }) {
                val fullyQualifiedName =
                    when (val declaration = sourceDeclaration) {
                        is KoExternalDeclaration -> declaration.fullyQualifiedName
                        is KoKotlinTypeDeclaration -> declaration.fullyQualifiedName
                        else -> null
                    }

                if (classpathSupertype != null || fullyQualifiedName == null) {
                    emptyList()
                } else {
                    ClasspathUtil
                        .getSupertypes(fullyQualifiedName)
                        .map { KoParentDeclarationCore(ktSuperTypeListEntry, containingDeclaration, it) }
                }
            }

    private fun getClasspathSourceDeclaration(supertype: ClasspathUtil.Supertype): KoDeclarationCastProvider? =
        if (TypeUtil.isKotlinTypeFullyQualifiedName(supertype.fullyQualifiedName)) {
            KoKotlinTypeDeclarationCore.getInstance(supertype.fullyQualifiedName, ktSuperTypeListEntry)
        } else {
            KoExternalDeclarationCore.getInstance(supertype.name, supertype.fullyQualifiedName, ktSuperTypeListEntry)
        } as? KoDeclarationCastProvider

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, containingDeclaration) {
                KoParentDeclarationCore(ktSuperTypeListEntry, containingDeclaration, null)
            }
    }
}
