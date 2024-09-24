package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

interface KoFunctionTypeDeclarationProvider : KoBaseProvider {
    /**
     * Represents the parameters of the function type.
     */
    val parameterTypes: List<KoParameterDeclaration>

    val numParameterTypes: Int

    /**
     * Represents the return type of the function type.
     */
    val returnType: KoTypeDeclaration

    fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int

    fun hasParameterTypeWithName(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasParameterTypeWithName(names: Collection<String>): Boolean

    fun hasParameterTypesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasParameterTypesWithAllNames(names: Collection<String>): Boolean

    fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean

    fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean

    fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    fun hasReturnTypeOf(kClass: KClass<*>): Boolean
}
