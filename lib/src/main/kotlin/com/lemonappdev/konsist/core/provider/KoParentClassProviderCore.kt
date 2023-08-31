package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.declaration.KoParentClassDeclarationCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry

@Deprecated("Will be removed in v1.0.0")
internal interface KoParentClassProviderCore :
    KoContainingDeclarationProviderCore,
    KoParentClassProvider,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("parents"))
    override val parentClass: KoParentClassDeclaration?
        get() = ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeCallEntry>()
            ?.firstOrNull()
            ?.let { KoParentClassDeclarationCore.getInstance(it, this) }

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }
}
