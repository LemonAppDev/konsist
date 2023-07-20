package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParent
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.declaration.KoParentImpl
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry

internal interface KoParentClassProviderCore :
    KoParentDeclarationProviderCore,
    KoParentClassProvider,
    KoBaseProviderCore{
    val ktClass: KtClass

    override val parentClass: KoParent?
        get() {
            val parentClass = ktClass
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeCallEntry>()
                ?.first()

            return parentClass?.let { KoParentImpl.getInstance(it, this) }
        }

    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }
}
