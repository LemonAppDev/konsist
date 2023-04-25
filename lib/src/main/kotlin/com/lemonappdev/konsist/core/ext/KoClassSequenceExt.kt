@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoClass
import kotlin.reflect.KClass

fun Sequence<KoClass>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClass>.withoutEnumModifier() = filterNot { it.hasEnumModifier() }

fun Sequence<KoClass>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClass>.withoutSealedModifier() = filterNot { it.hasSealedModifier() }

fun Sequence<KoClass>.withInnerModifier() = filter { it.hasInnerModifier() }

fun Sequence<KoClass>.withoutInnerModifier() = filterNot { it.hasInnerModifier() }

fun Sequence<KoClass>.withValueModifier() = filter { it.hasValueModifier() }

fun Sequence<KoClass>.withoutValueModifier() = filterNot { it.hasValueModifier() }

fun Sequence<KoClass>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

fun Sequence<KoClass>.withoutAnnotationModifier() = filterNot { it.hasAnnotationModifier() }

fun Sequence<KoClass>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoClass>.withoutDataModifier() = filterNot { it.hasDataModifier() }

fun Sequence<KoClass>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoClass>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoClass>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoClass>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoClass>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoClass>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoClass>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoClass>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoClass>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoClass>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoClass>.withPrimaryConstructor() = filter { it.hasPrimaryConstructor() }

fun Sequence<KoClass>.withoutPrimaryConstructor() = filterNot { it.hasPrimaryConstructor() }

fun Sequence<KoClass>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withoutSecondaryConstructors() = filterNot { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

fun Sequence<KoClass>.withSomeParents(vararg names: String) = filter {
    names.any { name -> it.hasParents(name) }
}

fun Sequence<KoClass>.withoutParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

inline fun <reified T> Sequence<KoClass>.withParentOf() = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentOf() = this - withParentOf<T>().toSet()

fun Sequence<KoClass>.withParentsOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParentsOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParentsOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

fun Sequence<KoClass>.withSomeParentInterfaces(vararg names: String) =
    filter { names.any { name -> it.hasParentInterfaces(name) } }

fun Sequence<KoClass>.withoutParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

inline fun <reified T> Sequence<KoClass>.withParentInterfaceOf() = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentInterfaceOf() = this - withParentInterfaceOf<T>().toSet()

fun Sequence<KoClass>.withParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

fun Sequence<KoClass>.withoutParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

inline fun <reified T> Sequence<KoClass>.withParentClassOf() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClass>.withoutParentClassOf() = this - withParentClassOf<T>().toSet()

fun Sequence<KoClass>.withParentClassOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

fun Sequence<KoClass>.withoutParentClassOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
