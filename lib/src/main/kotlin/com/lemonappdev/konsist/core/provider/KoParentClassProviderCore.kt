package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.declaration.KoParentClassDeclarationCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass

internal interface KoParentClassProviderCore :
    KoContainingDeclarationProviderCore,
    KoParentClassProvider,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val parentClass: KoParentClassDeclaration?
        get() {
            val kotlinClass = ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeCallEntry>()
                ?.firstOrNull()

            val javaClass = ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeEntry>()
                ?.firstOrNull { it is JavaClass }

            return if (kotlinClass != null) {
                KoParentClassDeclarationCore.getInstance(kotlinClass, this)
            } else if (javaClass != null) {
                KoParentClassDeclarationCore.getInstance(javaClass, this)
            } else {
                null
            }
        }

    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }
}
