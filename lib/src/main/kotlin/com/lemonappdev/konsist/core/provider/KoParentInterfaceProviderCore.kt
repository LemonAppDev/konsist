package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry

internal interface KoParentInterfaceProviderCore :
    KoParentProvider,
    KoParentInterfaceProvider {
    val ktClass: KtClass
    override val parentInterfaces: List<KoParentDeclaration>
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
            return all.map { KoParentDeclarationImpl.getInstance(it, this) }
        }

    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }
}
