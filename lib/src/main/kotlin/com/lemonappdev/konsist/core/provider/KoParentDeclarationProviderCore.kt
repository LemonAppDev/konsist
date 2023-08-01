package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentDeclarationProviderCore :
    KoParentDeclarationProvider,
    KoParentProviderCore,
    KoParentClassProviderCore,
    KoParentInterfaceProviderCore,
    KoBaseProviderCore {
    override val parentDeclarations: List<KoParentDeclaration>
        get() = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationImpl.getInstance(it, this) }
            ?: emptyList()

    override fun hasParentDeclarations(vararg names: String): Boolean = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }
}
