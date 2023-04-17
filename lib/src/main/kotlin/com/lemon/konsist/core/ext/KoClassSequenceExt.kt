package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass

fun Sequence<KoClass>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClass>.withoutEnumModifier() = this - withEnumModifier().toSet()

fun Sequence<KoClass>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClass>.withoutSealedModifier() = this - withSealedModifier().toSet()

fun Sequence<KoClass>.withInnerModifier() = filter { it.hasInnerModifier() }

fun Sequence<KoClass>.withoutInnerModifier() = this - withInnerModifier().toSet()

fun Sequence<KoClass>.withValueModifier() = filter { it.hasValueModifier() }

fun Sequence<KoClass>.withoutValueModifier() = this - withValueModifier().toSet()

fun Sequence<KoClass>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

fun Sequence<KoClass>.withoutAnnotationModifier() = this - withAnnotationModifier().toSet()

fun Sequence<KoClass>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoClass>.withoutDataModifier() = this - withDataModifier().toSet()

fun Sequence<KoClass>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoClass>.withoutAbstractModifier() = this - withAbstractModifier().toSet()

fun Sequence<KoClass>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoClass>.withoutOpenModifier() = this - withOpenModifier().toSet()

fun Sequence<KoClass>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoClass>.withoutFinalModifier() = this - withFinalModifier().toSet()

fun Sequence<KoClass>.withExplicitPrimaryConstructor() = filter { it.hasExplicitPrimaryConstructor() }

fun Sequence<KoClass>.withoutExplicitPrimaryConstructor() = this - withExplicitPrimaryConstructor().toSet()

fun Sequence<KoClass>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withoutSecondaryConstructors() = this - withSecondaryConstructors().toSet()

fun Sequence<KoClass>.withAnyParent() = filter { it.hasParent() }

fun Sequence<KoClass>.withoutAnyParent() = this - withAnyParent().toSet()

fun Sequence<KoClass>.withParent(name: String) = filter { it.hasParent(name) }

fun Sequence<KoClass>.withoutParent(name: String) = this - withParent(name).toSet()

inline fun <reified T> Sequence<KoClass>.withParent() = filter { koClass ->
    koClass
        .parents
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParent() = this - withParent<T>().toSet()

fun Sequence<KoClass>.withAnyParentInterface() = filter { it.hasParentInterface() }

fun Sequence<KoClass>.withoutAnyParentInterface() = this - withAnyParentInterface().toSet()

fun Sequence<KoClass>.withParentInterface(name: String) = filter { it.hasParentInterface(name) }

fun Sequence<KoClass>.withoutParentInterface(name: String) = this - withParentInterface(name).toSet()

inline fun <reified T> Sequence<KoClass>.withParentInterface() = filter { koClass ->
    koClass
        .parentInterfaces
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentInterface() = this - withParentInterface<T>().toSet()

fun Sequence<KoClass>.withAnyParentClass() = filter { it.hasParentClass() }

fun Sequence<KoClass>.withoutAnyParentClass() = this - withAnyParentClass().toSet()

fun Sequence<KoClass>.withParentClass(name: String) = filter { it.hasParentClass(name) }

fun Sequence<KoClass>.withoutParentClass(name: String) = this - withParentClass(name).toSet()

inline fun <reified T> Sequence<KoClass>.withParentClass() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClass>.withoutParentClass() = this - withParentClass<T>().toSet()
