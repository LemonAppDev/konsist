package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParent
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentImpl
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentProviderCore :
    KoParentProvider,
    KoParentDeclarationProviderCore,
    KoParentClassProviderCore,
    KoParentInterfaceProviderCore,
    KoBaseProviderCore {
    override val parents: List<KoParent>
        get() = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentImpl.getInstance(it, this) } ?: emptyList()

    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }
}
