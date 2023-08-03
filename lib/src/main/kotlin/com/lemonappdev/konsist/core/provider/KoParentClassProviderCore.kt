package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.declaration.KoParentClassDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry

internal interface KoParentClassProviderCore :
    KoContainingDeclarationProviderCore,
    KoParentClassProvider,
    KoBaseProviderCore {
    val ktClass: KtClass

    override val parentClass: KoParentClassDeclaration?
        get() = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeCallEntry>()
            ?.firstOrNull()
            ?.let { KoParentClassDeclarationCore.getInstance(it, this) }

    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }
}
