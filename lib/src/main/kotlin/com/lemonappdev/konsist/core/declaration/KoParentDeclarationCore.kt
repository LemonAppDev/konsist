package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.model.getTypeAlias
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
import com.lemonappdev.konsist.core.util.TypeUtil.isKotlinType
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

internal class KoParentDeclarationCore(
    override val ktSuperTypeListEntry: KtSuperTypeListEntry,
    override val containingDeclaration: KoBaseDeclaration,
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
        get() = ktSuperTypeListEntry.typeAsUserType

    @Volatile
    private var cachedKtAnnotationEntries: Any? = KoPropertyCache.NOT_COMPUTED

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() =
            KoPropertyCache.getOrCompute(cachedKtAnnotationEntries, { cachedKtAnnotationEntries = it }) {
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
                val name =
                    ktSuperTypeListEntry
                        .text
                        .substringBefore(" ")
                        .substringBefore("(")
                        .substringBefore("<")

                val outerName = if (name.contains(".")) name.substringBeforeLast(".") else name
                val innerName = if (name.contains(".")) name.substringAfterLast(".") else name

                val import =
                    containingFile
                        .imports
                        .firstOrNull { import ->
                            if (import.alias != null) {
                                import.alias?.name == outerName
                            } else {
                                import.name.substringAfterLast(".") == outerName || import.name.endsWith(name)
                            }
                        }

                val fullyQualifiedName =
                    import?.name
                        ?: "${containingFile.packagee?.name?.let { "$it." } ?: ""}$name"

                val isAlias = import?.alias != null

                (
                    import?.alias
                        ?: getClass(innerName, fullyQualifiedName, isAlias, containingFile)
                        ?: getInterface(innerName, fullyQualifiedName, isAlias, containingFile)
                        ?: getTypeAlias(innerName, fullyQualifiedName, containingFile)
                        ?: if (isKotlinType(name)) {
                            // Pass only the type, without constructor arguments (`Foo(bar)`) or delegation (`Foo by bar`)
                            ktUserType?.let { KoKotlinTypeDeclarationCore.getInstance(it, containingDeclaration) }
                        } else {
                            null
                        }
                        ?: KoExternalDeclarationCore.getInstance(innerName, ktSuperTypeListEntry)
                )
                    as? KoDeclarationCastProvider
            }

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() =
            KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) {
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
                ktSuperTypeListEntry
                    .children
                    .filterIsInstance<KtValueArgumentList>()
                    .firstOrNull()
                    ?.children
                    ?.filterIsInstance<KtValueArgument>()
                    ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
                    .orEmpty()
            }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, containingDeclaration) {
                KoParentDeclarationCore(ktSuperTypeListEntry, containingDeclaration)
            }
    }
}
