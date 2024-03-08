package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
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
            (it as? KoParentProvider)?.hasParent(indirectChildren) { parent -> parent == (this as? KoParentDeclaration) }
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
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            children(indirectChildren).any { child -> it == child.name }
        }
    }

    override fun hasChildrenWithAllNames(
        name: String,
        vararg names: String,
        indirectChildren: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
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
    ): Boolean =
        checkIfKClassOf(name, children(indirectChildren)) ||
            names.any { checkIfKClassOf(it, children(indirectChildren)) }

    override fun hasAllChildrenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean,
    ): Boolean =
        checkIfKClassOf(name, children(indirectChildren)) &&
            names.all { checkIfKClassOf(it, children(indirectChildren)) }
}
