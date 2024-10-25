package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.model.DataCore
import com.lemonappdev.konsist.core.util.KClassUtil.checkIfKClassOf
import kotlin.reflect.KClass

internal interface KoChildProviderCore :
    KoChildProvider,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    override fun children(indirectChildren: Boolean): List<KoChildDeclaration> {
        val items: List<KoChildDeclaration> = DataCore.classes + DataCore.interfaces + DataCore.objects

        return items.filter {
            (it as? KoParentProvider)?.hasParent(indirectChildren) { parent -> parent.sourceDeclaration == (this as? KoSourceDeclaration) }
                ?: false
        }
    }

    override fun numChildren(indirectChildren: Boolean): Int = children(indirectChildren).size

    override fun countChildren(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Int = children(indirectChildren).count { predicate(it) }

    override fun hasChildren(indirectChildren: Boolean): Boolean = children(indirectChildren).isNotEmpty()

    override fun hasChildWithName(
        name: String,
        vararg names: String,
        indirectChildren: Boolean,
    ): Boolean = hasChildWithName(listOf(name, *names), indirectChildren)

    override fun hasChildWithName(
        names: Collection<String>,
        indirectChildren: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasChildren(indirectChildren)
            else ->
                names.any {
                    children(indirectChildren).any { child -> it == child.name }
                }
        }

    override fun hasChildrenWithAllNames(
        name: String,
        vararg names: String,
        indirectChildren: Boolean,
    ): Boolean = hasChildrenWithAllNames(listOf(name, *names), indirectChildren)

    override fun hasChildrenWithAllNames(
        names: Collection<String>,
        indirectChildren: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasChildren(indirectChildren)
            else ->
                names.all {
                    children(indirectChildren).any { child -> it == child.name }
                }
        }

    override fun hasChild(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean = children(indirectChildren).any(predicate)

    override fun hasAllChildren(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean = children(indirectChildren).all(predicate)

    override fun hasChildOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean,
    ): Boolean = hasChildOf(listOf(name, *names))

    override fun hasChildOf(
        names: Collection<KClass<*>>,
        indirectChildren: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasChildren(indirectChildren)
            else -> names.any { checkIfKClassOf(it, children(indirectChildren)) }
        }

    override fun hasAllChildrenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean,
    ): Boolean = hasAllChildrenOf(listOf(name, *names))

    override fun hasAllChildrenOf(
        names: Collection<KClass<*>>,
        indirectChildren: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasChildren(indirectChildren)
            else -> names.all { checkIfKClassOf(it, children(indirectChildren)) }
        }
}
