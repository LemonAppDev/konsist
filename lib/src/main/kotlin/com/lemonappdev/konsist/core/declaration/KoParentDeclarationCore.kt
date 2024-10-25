package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
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
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil.filterIsInstance
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression
import org.jetbrains.kotlin.psi.KtDeclarationModifierList
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

internal class KoParentDeclarationCore(
    private val ktSuperTypeListEntry: KtSuperTypeListEntry,
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
    KoDeclarationCastProviderCore,
    KoAnnotationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore {
    override val psiElement: PsiElement by lazy { ktSuperTypeListEntry }

    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

    override val ktTypeReference: KtTypeReference? by lazy { null }

    override val ktNameReferenceExpression: KtNameReferenceExpression? by lazy { null }

    override val ktTypeProjection: KtTypeProjection? by lazy { null }

    private val children = ktSuperTypeListEntry.children

    private val targetChildren =
        children
            .filterIsInstance<KtConstructorCalleeExpression>()
            .firstOrNull()
            ?.children
            ?: children

    override val ktUserType: KtUserType? by lazy {
        targetChildren
            .firstOrNull { it is KtTypeReference }
            ?.children
            ?.filterIsInstance<KtUserType>()
            ?.firstOrNull()
    }

    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy {
        targetChildren
            .firstOrNull { it is KtTypeReference }
            ?.children
            ?.firstOrNull { it is KtDeclarationModifierList }
            ?.children
            ?.filterIsInstance<KtAnnotationEntry>()
    }

    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration by lazy { sourceDeclaration }

    override val sourceDeclaration: KoSourceDeclaration by lazy {
        val name =
            ktSuperTypeListEntry
                .text
                .substringBefore(" ")
                .substringBefore("(")
                .substringBefore("<")

        val innerName = if (name.contains(".")) name.substringBeforeLast(".") else name
        val outerName = if (name.contains(".")) name.substringAfterLast(".") else name

        val import =
            containingFile
                .imports
                .firstOrNull { import ->
                    if (import.alias != null) {
                        import.alias?.name == innerName
                    } else {
                        import.name.substringAfterLast(".") == innerName
                    }
                }

        val fqn =
            import
                ?.name
                ?: (containingFile.packagee?.name + "." + name)

        val isAlias = import?.alias != null

        getClass(outerName, fqn, isAlias, containingFile)
            ?: getInterface(outerName, fqn, isAlias, containingFile)
            ?: KoExternalDeclarationCore.getInstance(outerName, ktSuperTypeListEntry)
    }

    override val name: String by lazy {
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
