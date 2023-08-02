package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoParentInterfaceDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry

internal interface KoParentInterfaceProviderCore :
    KoContainingDeclarationProviderCore,
    KoParentInterfaceProvider,
    KoBaseProviderCore {
    val ktClass: KtClass
    override val parentInterfaces: List<KoParentInterfaceDeclaration>
        get() {
            val interfaces = ktClass
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeEntry>() ?: emptyList()

            val delegations = ktClass
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtDelegatedSuperTypeEntry>() ?: emptyList()

            val all = interfaces + delegations
            return all.map { KoParentInterfaceDeclarationImpl.getInstance(it, this) }
        }

    override val numParentInterfaces: Int
        get() = parentInterfaces.size

    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }
}
