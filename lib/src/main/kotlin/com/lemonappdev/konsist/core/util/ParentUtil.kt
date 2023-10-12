package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationCore
import org.jetbrains.kotlin.psi.KtTypeReference
import kotlin.reflect.KClass

object ParentUtil {
    internal fun <T: KoParentDeclaration> checkIfParentOf(kClass: KClass<*>, list: List<T>): Boolean=
        list.any { parent ->
            parent.name == kClass.simpleName || parent.fullyQualifiedName == kClass.qualifiedName
        }
}
