@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoClassDeclarationImpl>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutEnumModifier() = filterNot { it.hasEnumModifier() }

fun Sequence<KoClassDeclarationImpl>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutSealedModifier() = filterNot { it.hasSealedModifier() }

fun Sequence<KoClassDeclarationImpl>.withInnerModifier() = filter { it.hasInnerModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutInnerModifier() = filterNot { it.hasInnerModifier() }

fun Sequence<KoClassDeclarationImpl>.withValueModifier() = filter { it.hasValueModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutValueModifier() = filterNot { it.hasValueModifier() }

fun Sequence<KoClassDeclarationImpl>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutAnnotationModifier() = filterNot { it.hasAnnotationModifier() }

fun Sequence<KoClassDeclarationImpl>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutDataModifier() = filterNot { it.hasDataModifier() }

fun Sequence<KoClassDeclarationImpl>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoClassDeclarationImpl>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoClassDeclarationImpl>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoClassDeclarationImpl>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoClassDeclarationImpl>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoClassDeclarationImpl>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoClassDeclarationImpl>.withPrimaryConstructor() = filter { it.hasPrimaryConstructor() }

fun Sequence<KoClassDeclarationImpl>.withoutPrimaryConstructor() = filterNot { it.hasPrimaryConstructor() }

fun Sequence<KoClassDeclarationImpl>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClassDeclarationImpl>.withoutSecondaryConstructors() = filterNot { it.hasSecondaryConstructors() }

fun Sequence<KoClassDeclarationImpl>.withParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

fun Sequence<KoClassDeclarationImpl>.withSomeParents(vararg names: String) = filter {
    names.any { name -> it.hasParents(name) }
}

fun Sequence<KoClassDeclarationImpl>.withoutParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withParentOf() = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withoutParentOf() = this - withParentOf<T>().toSet()

fun Sequence<KoClassDeclarationImpl>.withParentsOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withSomeParentsOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withoutParentsOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

fun Sequence<KoClassDeclarationImpl>.withSomeParentInterfaces(vararg names: String) =
    filter { names.any { name -> it.hasParentInterfaces(name) } }

fun Sequence<KoClassDeclarationImpl>.withoutParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withParentInterfaceOf() = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withoutParentInterfaceOf() = this - withParentInterfaceOf<T>().toSet()

fun Sequence<KoClassDeclarationImpl>.withParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withSomeParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withoutParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClassDeclarationImpl>.withParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

fun Sequence<KoClassDeclarationImpl>.withoutParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withParentClassOf() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClassDeclarationImpl>.withoutParentClassOf() = this - withParentClassOf<T>().toSet()

fun Sequence<KoClassDeclarationImpl>.withParentClassOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

fun Sequence<KoClassDeclarationImpl>.withoutParentClassOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
