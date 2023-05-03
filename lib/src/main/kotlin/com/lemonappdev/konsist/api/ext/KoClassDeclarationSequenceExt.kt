@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

fun Sequence<KoClassDeclaration>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClassDeclaration>.withoutEnumModifier() = filterNot { it.hasEnumModifier() }

fun Sequence<KoClassDeclaration>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClassDeclaration>.withoutSealedModifier() = filterNot { it.hasSealedModifier() }

fun Sequence<KoClassDeclaration>.withInnerModifier() = filter { it.hasInnerModifier() }

fun Sequence<KoClassDeclaration>.withoutInnerModifier() = filterNot { it.hasInnerModifier() }

fun Sequence<KoClassDeclaration>.withValueModifier() = filter { it.hasValueModifier() }

fun Sequence<KoClassDeclaration>.withoutValueModifier() = filterNot { it.hasValueModifier() }

fun Sequence<KoClassDeclaration>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

fun Sequence<KoClassDeclaration>.withoutAnnotationModifier() = filterNot { it.hasAnnotationModifier() }

fun Sequence<KoClassDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoClassDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }

fun Sequence<KoClassDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoClassDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoClassDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoClassDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoClassDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoClassDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoClassDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoClassDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoClassDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoClassDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoClassDeclaration>.withPrimaryConstructor() = filter { it.hasPrimaryConstructor() }

fun Sequence<KoClassDeclaration>.withoutPrimaryConstructor() = filterNot { it.hasPrimaryConstructor() }

fun Sequence<KoClassDeclaration>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClassDeclaration>.withoutSecondaryConstructors() = filterNot { it.hasSecondaryConstructors() }

fun Sequence<KoClassDeclaration>.withParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

fun Sequence<KoClassDeclaration>.withSomeParents(vararg names: String) = filter {
    names.any { name -> it.hasParents(name) }
}

fun Sequence<KoClassDeclaration>.withoutParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

inline fun <reified T> Sequence<KoClassDeclaration>.withParentOf() = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentOf() = this - withParentOf<T>().toSet()

fun Sequence<KoClassDeclaration>.withParentsOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withSomeParentsOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withoutParentsOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

fun Sequence<KoClassDeclaration>.withSomeParentInterfaces(vararg names: String) =
    filter { names.any { name -> it.hasParentInterfaces(name) } }

fun Sequence<KoClassDeclaration>.withoutParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

inline fun <reified T> Sequence<KoClassDeclaration>.withParentInterfaceOf() = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentInterfaceOf() = this - withParentInterfaceOf<T>().toSet()

fun Sequence<KoClassDeclaration>.withParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withSomeParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withoutParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclaration>.withParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

fun Sequence<KoClassDeclaration>.withoutParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

inline fun <reified T> Sequence<KoClassDeclaration>.withParentClassOf() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentClassOf() = this - withParentClassOf<T>().toSet()

fun Sequence<KoClassDeclaration>.withParentClassOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

fun Sequence<KoClassDeclaration>.withoutParentClassOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
