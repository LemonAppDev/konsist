package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withEnumModifier() = filter { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that don't have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutEnumModifier() = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withSealedModifier() = filter { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that don't have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutSealedModifier() = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withInnerModifier() = filter { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that don't have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutInnerModifier() = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withValueModifier() = filter { it.hasValueModifier() }

/**
 * Sequence containing all declarations that don't have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutValueModifier() = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all declarations that have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that don't have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAnnotationModifier() = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

/**
 * Sequence containing all declarations that don't have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all declarations that have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

/**
 * Sequence containing all declarations that don't have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all declarations that have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that don't have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that don't have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that don't have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that have 'final' modifier.
 */
fun Sequence<KoClassDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

/**
 * Sequence containing all declarations that don't have 'final' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing all declarations that have primary constructor.
 */
fun Sequence<KoClassDeclaration>.withPrimaryConstructor() = filter { it.hasPrimaryConstructor() }

/**
 * Sequence containing all declarations that don't have primary constructor.
 */
fun Sequence<KoClassDeclaration>.withoutPrimaryConstructor() = filterNot { it.hasPrimaryConstructor() }

/**
 * Sequence containing all declarations that have secondary constructors.
 */
fun Sequence<KoClassDeclaration>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

/**
 * Sequence containing all declarations that don't have secondary constructors.
 */
fun Sequence<KoClassDeclaration>.withoutSecondaryConstructors() = filterNot { it.hasSecondaryConstructors() }

/**
 * Sequence containing all declarations that have parents.
 */
fun Sequence<KoClassDeclaration>.withParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

/**
 * Sequence containing all declarations that have some parents.
 */
fun Sequence<KoClassDeclaration>.withSomeParents(vararg names: String) = filter {
    names.any { name -> it.hasParents(name) }
}

/**
 * Sequence containing all declarations that don't have parents.
 */
fun Sequence<KoClassDeclaration>.withoutParents(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

/**
 * Sequence containing all declarations that have parent of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentOf() = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all declarations that don't have some parent of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentOf() = this - withParentOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent of type.
 */
fun Sequence<KoClassDeclaration>.withParentsOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have some parent of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentsOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that don't have parent of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentsOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have parent of type.
 */
fun Sequence<KoClassDeclaration>.withParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all declarations that have some parent of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfaces(vararg names: String) =
    filter { names.any { name -> it.hasParentInterfaces(name) } }

/**
 * Sequence containing all declarations that don't have parent of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfaces(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all declarations that have parent interface of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentInterfaceOf() = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all declarations that don't have some parent interface of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentInterfaceOf() = this - withParentInterfaceOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.all { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have some parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.any { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that don't have parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfacesOf(vararg names: KClass<*>) = filter {
    names.none { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have parent class.
 */
fun Sequence<KoClassDeclaration>.withParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have some parent class.
 */
fun Sequence<KoClassDeclaration>.withoutParentClass(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have parent class of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentClassOf() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

/**
 * Sequence containing all declarations that don't have some parent class of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentClassOf() = this - withParentClassOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent class of type.
 */
fun Sequence<KoClassDeclaration>.withParentClassOf(vararg names: KClass<*>) = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all declarations that have some parent class of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentClassOf(vararg names: KClass<*>) = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
