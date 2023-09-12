package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val parents: List<KoParentDeclaration>
        get() = ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationCore.getInstance(it, this) }
            ?: emptyList()

    override val numParents: Int
        get() = parents.size

    override fun countParents(predicate: (KoParentDeclaration) -> Boolean): Int =
        parents.count { predicate(it) }

    @Deprecated("Will be removed in v1.0.0.", ReplaceWith("hasParentsWithAllNames(*names)"))
    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> parents.isNotEmpty()
        else -> names.all {
            parents.any { parent -> it == parent.name }
        }
    }

    override fun hasParents(): Boolean = parents.isNotEmpty()

    override fun hasParentWithName(vararg names: String): Boolean = names.any {
        parents.any { parent -> it == parent.name }
    }

    override fun hasParentsWithAllNames(vararg names: String): Boolean = names.all {
        parents.any { parent -> it == parent.name }
    }

    override fun hasParent(predicate: (KoParentDeclaration) -> Boolean): Boolean = parents.any(predicate)

    override fun hasAllParents(predicate: (KoParentDeclaration) -> Boolean): Boolean = parents.all(predicate)
}
