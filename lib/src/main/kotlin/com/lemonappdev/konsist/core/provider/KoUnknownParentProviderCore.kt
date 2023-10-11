package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoUnknownParentProvider

internal interface KoUnknownParentProviderCore :
    KoUnknownParentProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override val unknownParents: List<KoParentDeclaration>
        get() = parents
            .filterNot { it is KoClassDeclaration || it is KoInterfaceDeclaration }

//    override val numUnknownParents: Int
//        get() = unknownParents.size
//
//    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
//    override fun hasUnknownParents(vararg names: String): Boolean = when {
//        names.isEmpty() -> unknownParents.isNotEmpty()
//        else -> names.all {
//            unknownParents.any { koParent -> it == koParent.name }
//        }
//    }
//
//    override fun countUnknownParents(predicate: (KoParentDeclaration) -> Boolean): Int =
//        unknownParents.count { predicate(it) }
//
//    override fun hasUnknownParents(): Boolean = unknownParents.isNotEmpty()
//
//    override fun hasUnknownParentWithName(name: String, vararg names: String): Boolean {
//        val givenNames = names.toList() + name
//
//        return givenNames.any {
//            unknownParents.any { parentInterface -> it == parentInterface.name }
//        }
//    }
//
//    override fun hasUnknownParentsWithAllNames(name: String, vararg names: String): Boolean {
//        val givenNames = names.toList() + name
//
//        return givenNames.all {
//            unknownParents.any { parentInterface -> it == parentInterface.name }
//        }
//    }
//
//    override fun hasUnknownParent(predicate: (KoParentDeclaration) -> Boolean): Boolean = unknownParents.any(predicate)
//
//    override fun hasAllUnknownParents(predicate: (KoParentDeclaration) -> Boolean): Boolean = unknownParents.all(predicate)
//
//    override fun hasUnknownParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
//        checkIfUnknownParentOf(name) || names.any { checkIfUnknownParentOf(it) }
//
//    override fun hasAllUnknownParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
//        checkIfUnknownParentOf(name) && names.all { checkIfUnknownParentOf(it) }
//
//    private fun checkIfUnknownParentOf(kClass: KClass<*>): Boolean = unknownParents.any { parent ->
//        parent.name == kClass.simpleName || parent.fullyQualifiedName == kClass.qualifiedName
//    }
}
