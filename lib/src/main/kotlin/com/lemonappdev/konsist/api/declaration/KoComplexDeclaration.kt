package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyCoreProvider

/**
 * Represents a complex declaration, such as a class, interface, object, etc.
 */
interface KoComplexDeclaration :
    KoDeclaration,
    KoClassCoreProvider,
    KoInterfaceCoreProvider,
    KoObjectCoreProvider,
    KoPropertyCoreProvider,
    KoFunctionCoreProvider {
    /**
     * Whether this type represents the specified type.
     *
     * @param name the name of type to compare.
     * @return `true` if this type represents the specified type, `false` otherwise.
     */
    fun representsType(name: String): Boolean
}
