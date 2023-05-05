package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withEnumModifier(): Sequence<KoClassDeclaration> = filter { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that don't have 'enum' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutEnumModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withSealedModifier(): Sequence<KoClassDeclaration> = filter { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that don't have 'sealed' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutSealedModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withInnerModifier(): Sequence<KoClassDeclaration> = filter { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that don't have 'inner' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutInnerModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withValueModifier(): Sequence<KoClassDeclaration> = filter { it.hasValueModifier() }

/**
 * Sequence containing all declarations that don't have 'value' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutValueModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all declarations that have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withAnnotationModifier(): Sequence<KoClassDeclaration> = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that don't have 'annotation' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAnnotationModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withDataModifier(): Sequence<KoClassDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all declarations that don't have 'data' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutDataModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all declarations that have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withActualModifier(): Sequence<KoClassDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing all declarations that don't have 'actual' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutActualModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all declarations that have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withExpectModifier(): Sequence<KoClassDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that don't have 'expect' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutExpectModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withAbstractModifier(): Sequence<KoClassDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that don't have 'abstract' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAbstractModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withOpenModifier(): Sequence<KoClassDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that don't have 'open' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutOpenModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that have 'final' modifier.
 */
fun Sequence<KoClassDeclaration>.withFinalModifier(): Sequence<KoClassDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing all declarations that don't have 'final' modifier.
 */
fun Sequence<KoClassDeclaration>.withoutFinalModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing all declarations that have primary constructor.
 */
fun Sequence<KoClassDeclaration>.withPrimaryConstructor(): Sequence<KoClassDeclaration> = filter { it.hasPrimaryConstructor() }

/**
 * Sequence containing all declarations that don't have primary constructor.
 */
fun Sequence<KoClassDeclaration>.withoutPrimaryConstructor(): Sequence<KoClassDeclaration> = filterNot { it.hasPrimaryConstructor() }

/**
 * Sequence containing all declarations that have secondary constructors.
 */
fun Sequence<KoClassDeclaration>.withSecondaryConstructors(): Sequence<KoClassDeclaration> = filter { it.hasSecondaryConstructors() }

/**
 * Sequence containing all declarations that don't have secondary constructors.
 */
fun Sequence<KoClassDeclaration>.withoutSecondaryConstructors(): Sequence<KoClassDeclaration> = filterNot { it.hasSecondaryConstructors() }

/**
 * Sequence containing all declarations that have parents.
 */
fun Sequence<KoClassDeclaration>.withParents(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

/**
 * Sequence containing all declarations that have some parents.
 */
fun Sequence<KoClassDeclaration>.withSomeParents(vararg names: String): Sequence<KoClassDeclaration> = filter {
    names.any { name -> it.hasParents(name) }
}

/**
 * Sequence containing all declarations that don't have parents.
 */
fun Sequence<KoClassDeclaration>.withoutParents(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

/**
 * Sequence containing all declarations that have parent of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all declarations that don't have some parent of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentOf(): Sequence<KoClassDeclaration> = this - withParentOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent of type.
 */
fun Sequence<KoClassDeclaration>.withParentsOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.all { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have some parent of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentsOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.any { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that don't have parent of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentsOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.none { kClass ->
        it
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have parent of type.
 */
fun Sequence<KoClassDeclaration>.withParentInterfaces(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all declarations that have some parent of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfaces(vararg names: String): Sequence<KoClassDeclaration> =
    filter { names.any { name -> it.hasParentInterfaces(name) } }

/**
 * Sequence containing all declarations that don't have parent of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfaces(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all declarations that have parent interface of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentInterfaceOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all declarations that don't have some parent interface of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentInterfaceOf(): Sequence<KoClassDeclaration> =
    this - withParentInterfaceOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withParentInterfacesOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.all { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have some parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfacesOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.any { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that don't have parent interface of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfacesOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.none { kClass ->
        it
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

/**
 * Sequence containing all declarations that have parent class.
 */
fun Sequence<KoClassDeclaration>.withParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have some parent class.
 */
fun Sequence<KoClassDeclaration>.withoutParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have parent class of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentClassOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

/**
 * Sequence containing all declarations that don't have some parent class of type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentClassOf(): Sequence<KoClassDeclaration> =
    this - withParentClassOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent class of type.
 */
fun Sequence<KoClassDeclaration>.withParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all declarations that have some parent class of type.
 */
fun Sequence<KoClassDeclaration>.withoutParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
