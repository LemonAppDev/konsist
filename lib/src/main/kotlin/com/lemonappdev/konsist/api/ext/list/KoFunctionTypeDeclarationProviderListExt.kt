package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import kotlin.reflect.KClass

val <T : KoFunctionTypeDeclarationProvider> List<T>.returnTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.returnType }

val <T : KoFunctionTypeDeclarationProvider> List<T>.parameterTypes: List<KoParameterDeclaration>
    get() = flatMap { it.parameterTypes }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.returnType) }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.returnType) }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withReturnTypeOf(listOf(kClass, *kClasses))

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutReturnTypeOf(listOf(kClass, *kClasses))

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasParameterType(predicate)
    }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasParameterType(predicate) }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllParameterTypes(predicate)
    }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllParameterTypes(predicate) }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.parameterTypes) }

fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.parameterTypes) }
