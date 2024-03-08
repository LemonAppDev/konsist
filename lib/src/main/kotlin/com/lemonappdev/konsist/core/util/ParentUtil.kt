package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import kotlin.reflect.KClass

object ParentUtil {
    internal fun <T : KoParentDeclaration> checkIfParentOf(
        kClass: KClass<*>,
        list: List<T>,
    ): Boolean = list.any { parent -> parent.fullyQualifiedName == kClass.qualifiedName }
}
