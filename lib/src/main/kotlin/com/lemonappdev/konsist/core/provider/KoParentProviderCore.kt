package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentProviderCore :
    KoParentProvider,
    KoParentDeclarationProviderCore,
    KoParentClassProviderCore,
    KoParentInterfaceProviderCore {
    override val parents: List<KoParentDeclaration>
        get() = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationImpl.getInstance(it, this) } ?: emptyList()

    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }
}
