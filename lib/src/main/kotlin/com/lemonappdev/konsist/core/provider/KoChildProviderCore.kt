package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.model.DataCore
import kotlin.reflect.KClass

internal interface KoChildProviderCore :
    KoChildProvider,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    override fun children(indirectChildren: Boolean): List<KoChildDeclaration> {
        val items: List<KoChildDeclaration> = DataCore.classes + DataCore.interfaces + DataCore.objects

        return items.filter { (it as KoParentProvider).parents.contains(this as KoParentDeclaration) }
    }

    private fun getIndirectChildren(children: List<KoChildDeclaration>): List<KoChildDeclaration> {
        val indirectChildren = mutableListOf<KoChildDeclaration>()

        children
            .forEach {
                val nextChildren = if (it as? KoChildProvider != null) {
                    it.children(indirectChildren = true)
                } else {
                    emptyList()
                }

                if (nextChildren.isNotEmpty()) {
                    indirectChildren += nextChildren + getIndirectChildren(nextChildren)
                }
            }

        return indirectChildren
    }

    private fun getChildClass(name: String, fqn: String?): KoClassDeclaration? = DataCore
        .classes
        .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
        ?: containingFile
            .classes()
            .firstOrNull { decl -> decl.name == name }

    private fun getChildInterface(name: String, fqn: String?): KoInterfaceDeclaration? = DataCore
        .interfaces
        .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
        ?: containingFile
            .interfaces()
            .firstOrNull { decl -> decl.name == name }

    override fun numChildren(indirectChildren: Boolean): Int = children(indirectChildren).size

    override fun countChildren(indirectChildren: Boolean, predicate: (KoChildDeclaration) -> Boolean): Int =
        children(indirectChildren).count { predicate(it) }

    override fun hasChildren(indirectChildren: Boolean): Boolean = children(indirectChildren).isNotEmpty()

    override fun hasChildWithName(name: String, vararg names: String, indirectChildren: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            children(indirectChildren).any { child -> it == child.name }
        }
    }

    override fun hasChildrenWithAllNames(name: String, vararg names: String, indirectChildren: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            children(indirectChildren).any { child -> it == child.name }
        }
    }

    override fun hasChild(indirectChildren: Boolean, predicate: (KoChildDeclaration) -> Boolean): Boolean =
        children(indirectChildren).any(predicate)

    override fun hasAllChildren(indirectChildren: Boolean, predicate: (KoChildDeclaration) -> Boolean): Boolean =
        children(indirectChildren).all(predicate)

    override fun hasChildOf(name: KClass<*>, vararg names: KClass<*>, indirectChildren: Boolean): Boolean = true
//        checkIfChildOf(name, children(indirectChildren)) || names.any { checkIfChildOf(it, children(indirectChildren)) }

    override fun hasAllChildrenOf(name: KClass<*>, vararg names: KClass<*>, indirectChildren: Boolean): Boolean = true
//        checkIfChildOf(name, children(indirectChildren)) && names.all { checkIfChildOf(it, children(indirectChildren)) }
}
