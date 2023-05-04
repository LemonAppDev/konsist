package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing all elements that have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withEnumModifier() = filter { it.hasEnumModifier() }

/**
 * Sequence containing all elements that do not have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutEnumModifier() = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all elements that have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withSealedModifier() = filter { it.hasSealedModifier() }

/**
 * Sequence containing all elements that do not have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutSealedModifier() = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all elements that have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withInnerModifier() = filter { it.hasInnerModifier() }

/**
 * Sequence containing all elements that do not have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutInnerModifier() = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all elements that have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withValueModifier() = filter { it.hasValueModifier() }

/**
 * Sequence containing all elements that do not have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutValueModifier() = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all elements that have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all elements that do not have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAnnotationModifier() = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all elements that have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

/**
 * Sequence containing all elements that do not have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all elements that have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

/**
 * Sequence containing all elements that do not have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all elements that have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

/**
 * Sequence containing all elements that do not have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all elements that have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all elements that do not have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all elements that have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

/**
 * Sequence containing all elements that do not have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all elements that have 'final' modifier.
 */
fun Sequence<KoClassDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

/**
 * Sequence containing all elements that do not have 'final' modifier.
 */
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
