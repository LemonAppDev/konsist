package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoExternalParentDeclarationCore
import com.lemonappdev.konsist.core.model.DataCore
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList
import kotlin.reflect.KClass

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val parents: List<KoParentDeclaration>
        get() = ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map {
                val name = it
                    .text
                    .substringBefore(" ")
                    .substringBefore("(")
                    .substringBefore("<")

                val fqn =
                    containingFile
                        .imports
                        .firstOrNull { import -> import.name.substringAfterLast(".") == name }
                        ?.name

                val isCallable = it
                    .children
                    .filterIsInstance<KtConstructorCalleeExpression>()
                    .isNotEmpty()

                val parent = if (isCallable) {
                    getParentClass(name, fqn, it)
                } else {
                    /*
                    We check this because parent may be a class without primary constructor, e.g.
                    class SampleClass : SampleSuperClass {
                         constructor(param: Int) : super(param)
                    }

                    open class SampleSuperClass(param: Int)
                     */
                    val hasSecondaryConstructor = ktClassOrObject
                        .children
                        .filterIsInstance<KtClassBody>()
                        .firstOrNull()
                        ?.children
                        ?.filterIsInstance<KtSecondaryConstructor>()
                        ?.isNotEmpty()
                        ?: false

                    if (hasSecondaryConstructor) {
                        getParentClass(name, fqn, it)
                    } else {
                        getParentInterface(name, fqn)
                    }
                }

                return@map parent ?: KoExternalParentDeclarationCore(name, it)
            }
            ?: emptyList()

    private fun getParentClass(
        name: String,
        fqn: String?,
        ktSuperTypeListEntry: KtSuperTypeListEntry
    ): KoClassDeclaration? {
        val numArguments = ktSuperTypeListEntry
            .children
            .filterIsInstance<KtValueArgumentList>()
            .firstOrNull()
            ?.children
            ?.filterIsInstance<KtValueArgument>()
            ?.firstOrNull()
            ?.children
            ?.size ?: 0

        return containingFile
            .classes()
            .firstOrNull { decl -> checkClassCompatibility(decl, name, fqn, numArguments) }
            ?: DataCore
                .classes
                .firstOrNull { parent -> checkClassCompatibility(parent, name, fqn, numArguments) }
    }

    private fun checkClassCompatibility(koClass: KoClassDeclaration, name: String, fqn: String?, numArguments: Int) =
        (koClass.name == name || (koClass.packagee?.fullyQualifiedName + "." + koClass.name) == fqn)
                && (koClass.constructors.any { it.numParameters == numArguments }
                || (numArguments == 0 && koClass.numConstructors == 0))

    private fun getParentInterface(name: String, fqn: String?): KoInterfaceDeclaration? = containingFile
        .interfaces()
        .firstOrNull { decl -> decl.name == name }
        ?: DataCore
            .interfaces
            .firstOrNull { parent ->
                (parent.packagee?.fullyQualifiedName + "." + parent.name) == fqn
            }

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

    override fun hasParentWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            parents.any { parent -> it == parent.name }
        }
    }

    override fun hasParentsWithAllNames(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parents.any { parent -> it == parent.name }
        }
    }

    override fun hasParent(predicate: (KoParentDeclaration) -> Boolean): Boolean = parents.any(predicate)

    override fun hasAllParents(predicate: (KoParentDeclaration) -> Boolean): Boolean = parents.all(predicate)

    override fun hasParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
        checkIfParentOf(name, parents) || names.any { checkIfParentOf(it, parents) }

    override fun hasAllParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
        checkIfParentOf(name, parents) && names.all { checkIfParentOf(it, parents) }
}
