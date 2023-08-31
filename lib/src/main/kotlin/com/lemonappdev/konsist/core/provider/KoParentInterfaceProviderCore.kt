package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoParentInterfaceDeclarationCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry

@Deprecated("Will be removed in v1.0.0")
internal interface KoParentInterfaceProviderCore :
    KoContainingDeclarationProviderCore,
    KoParentInterfaceProvider,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject
    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("parents"))
    override val parentInterfaces: List<KoParentInterfaceDeclaration>
        get() {
            val interfaces = ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeEntry>() ?: emptyList()

            val delegations = ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtDelegatedSuperTypeEntry>() ?: emptyList()

            val all = interfaces + delegations
            return all.map { KoParentInterfaceDeclarationCore.getInstance(it, this) }
        }

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("numParents"))
    override val numParentInterfaces: Int
        get() = parentInterfaces.size

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }
}
