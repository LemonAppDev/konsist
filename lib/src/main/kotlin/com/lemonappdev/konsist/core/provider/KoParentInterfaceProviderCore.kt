package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParent
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoParentImpl
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry

internal interface KoParentInterfaceProviderCore :
    KoParentDeclarationProviderCore,
    KoParentInterfaceProvider,
    KoBaseProviderCore {
    val ktClass: KtClass
    override val parentInterfaces: List<KoParent>
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
            return all.map { KoParentImpl.getInstance(it, this) }
        }

    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }
}
