package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing all classes that have `enum` modifier.
 *
 * @return A sequence containing classes with the `enum` modifier.
 */
fun Sequence<KoClassDeclaration>.withEnumModifier(): Sequence<KoClassDeclaration> = filter { it.hasEnumModifier() }

/**
 * Sequence containing all classes that don't have `enum` modifier.
 *
 * @return A sequence containing classes without the `enum` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutEnumModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all classes that have `sealed` modifier.
 *
 * @return A sequence containing classes with the `sealed` modifier.
 */
fun Sequence<KoClassDeclaration>.withSealedModifier(): Sequence<KoClassDeclaration> = filter { it.hasSealedModifier() }

/**
 * Sequence containing all classes that don't have `sealed` modifier.
 *
 * @return A sequence containing classes without the `sealed` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutSealedModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all classes that have `inner` modifier.
 *
 * @return A sequence containing classes with the `inner` modifier.
 */
fun Sequence<KoClassDeclaration>.withInnerModifier(): Sequence<KoClassDeclaration> = filter { it.hasInnerModifier() }

/**
 * Sequence containing all classes that don't have `inner` modifier.
 *
 * @return A sequence containing classes without the `inner` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutInnerModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all classes that have `value` modifier.
 *
 * @return A sequence containing classes with the `value` modifier.
 */
fun Sequence<KoClassDeclaration>.withValueModifier(): Sequence<KoClassDeclaration> = filter { it.hasValueModifier() }

/**
 * Sequence containing all classes that don't have `value` modifier.
 *
 * @return A sequence containing classes without the `value` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutValueModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all classes that have `annotation` modifier.
 *
 * @return A sequence containing classes with the `annotation` modifier.
 */
fun Sequence<KoClassDeclaration>.withAnnotationModifier(): Sequence<KoClassDeclaration> = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all classes that don't have `annotation` modifier.
 *
 * @return A sequence containing classes without the `annotation` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAnnotationModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all classes that have `data` modifier.
 *
 * @return A sequence containing classes with the `data` modifier.
 */
fun Sequence<KoClassDeclaration>.withDataModifier(): Sequence<KoClassDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all classes that don't have `data` modifier.
 *
 * @return A sequence containing classes without the `data` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutDataModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all classes that have `actual` modifier.
 *
 * @return A sequence containing classes with the `actual` modifier.
 */
fun Sequence<KoClassDeclaration>.withActualModifier(): Sequence<KoClassDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing all classes that don't have `actual` modifier.
 *
 * @return A sequence containing classes without the `actual` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutActualModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all classes that have `expect` modifier.
 *
 * @return A sequence containing classes with the `expect` modifier.
 */
fun Sequence<KoClassDeclaration>.withExpectModifier(): Sequence<KoClassDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing all classes that don't have `expect` modifier.
 *
 * @return A sequence containing classes without the `expect` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutExpectModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all classes that have `abstract` modifier.
 *
 * @return A sequence containing classes with the `abstract` modifier.
 */
fun Sequence<KoClassDeclaration>.withAbstractModifier(): Sequence<KoClassDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all classes that don't have `abstract` modifier.
 *
 * @return A sequence containing classes without the `abstract` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAbstractModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all classes that have `open` modifier.
 *
 * @return A sequence containing classes with the `open` modifier.
 */
fun Sequence<KoClassDeclaration>.withOpenModifier(): Sequence<KoClassDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing all classes that don't have `open` modifier.
 *
 * @return A sequence containing classes without the `open` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutOpenModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all classes that have `final` modifier.
 *
 * @return A sequence containing classes with the `final` modifier.
 */
fun Sequence<KoClassDeclaration>.withFinalModifier(): Sequence<KoClassDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing all classes that don't have `final` modifier.
 *
 * @return A sequence containing classes without the `final` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutFinalModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing all classes that have primary constructor.
 *
 * @return A sequence containing classes with primary constructor.
 */
fun Sequence<KoClassDeclaration>.withPrimaryConstructor(): Sequence<KoClassDeclaration> = filter { it.hasPrimaryConstructor() }

/**
 * Sequence containing all classes that don't have primary constructor.
 *
 * @return A sequence containing classes without primary constructor.
 */
fun Sequence<KoClassDeclaration>.withoutPrimaryConstructor(): Sequence<KoClassDeclaration> = filterNot { it.hasPrimaryConstructor() }

/**
 * Sequence containing all classes that have secondary constructors.
 *
 * @return A sequence containing classes with secondary constructor(s).
 */
fun Sequence<KoClassDeclaration>.withSecondaryConstructors(): Sequence<KoClassDeclaration> = filter { it.hasSecondaryConstructors() }

/**
 * Sequence containing all classes that don't have secondary constructors.
 *
 * @return A sequence containing classes without secondary constructor(s).
 */
fun Sequence<KoClassDeclaration>.withoutSecondaryConstructors(): Sequence<KoClassDeclaration> = filterNot { it.hasSecondaryConstructors() }

/**
 * Sequence containing all classes that have parents.
 *
 * @param names The name(s) of the parent(s) to include.
 * @return A sequence containing classes that have the specified parent(s) (or any parent if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withParents(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParents()
        else -> it.hasParents(*names)
    }
}

/**
 * Sequence containing all classes that have some parents.
 *
 * @param name The name of the parent to include.
 * @param names The names of the parents to include.
 * @return A sequence containing classes that have at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParents(name) || names.any { name -> it.hasParents(name) }
}

/**
 * Sequence containing all classes that don't have parents.
 *
 * @param names The name(s) of the parent(s) to exclude.
 * @return A sequence containing classes that don't have the specified parent(s) (or none parent if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withoutParents(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParents()
        else -> !it.hasParents(*names)
    }
}

/**
 * Sequence containing all classes that have parent of type.
 *
 * @return A sequence containing classes that have the parent of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all classes that don't have parent of type.
 *
 * @return A sequence containing classes that don't have the parent of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentOf(): Sequence<KoClassDeclaration> = this - withParentOf<T>().toSet()

/**
 * Sequence containing all classes that have parent of type.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin classes representing the parents to include.
 * @return A sequence containing classes that have the parents of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.any { parent -> parent.name == name.simpleName } &&
        names.all { kClass ->
            it
                .parents
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes that have some parent of type.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin classes representing the parents to include.
 * @return A sequence containing classes that have at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.any { parent -> parent.name == name.simpleName } ||
        names.any { kClass ->
            it
                .parents
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes that don't have parent of type.
 *
 * @param name The Kotlin class representing the parent to exclude.
 * @param names The Kotlin classes representing the parents to exclude.
 * @return A sequence containing classes that don't have the parents of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.none { parent -> parent.name == name.simpleName } &&
        names.none { kClass ->
            it
                .parents
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes that have parent of type.
 *
 * @param names The name(s) of the parent interface(s) to include.
 * @return A sequence containing classes that have the specified parent interface(s) (or any parent interface if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withParentInterfaces(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParentInterfaces()
        else -> it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all classes that have some parent interface of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A sequence containing classes that have at least one of the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfaces(name: String, vararg names: String): Sequence<KoClassDeclaration> =
    filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * Sequence containing all classes that don't have parent interface of type.
 *
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A sequence containing classes that don't have the specified parent interface(s) (or none parent interface if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfaces(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParentInterfaces()
        else -> !it.hasParentInterfaces(*names)
    }
}

/**
 * Sequence containing all classes that have parent interface of type.
 *
 * @return A sequence containing classes that have the parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentInterfaceOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all classes that don't have some parent interface of type.
 *
 * @return A sequence containing classes that don't have the parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentInterfaceOf(): Sequence<KoClassDeclaration> =
    this - withParentInterfaceOf<T>().toSet()

/**
 * Sequence containing all classes that have parent interface of type.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin classes representing the parent interfaces to include.
 * @return A sequence containing classes that have the parent interfaces of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parentInterfaces.any { parent -> parent.name == name.simpleName } &&
        names.all { kClass ->
            it
                .parentInterfaces
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes that have some parent interface of type.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin classes representing the parent interfaces to include.
 * @return A sequence containing classes that have at least one of the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } ||
            names.any { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all classes that don't have parent interface of type.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin classes representing the parent interfaces to exclude.
 * @return A sequence containing classes that don't have the parent interfaces of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            names.none { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all classes that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A sequence containing classes that have the specified parent class (or any parent class if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all classes that have some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A sequence containing classes that don't have the specified parent class (or none parent class if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withoutParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all classes that have parent class of type.
 *
 * @return A sequence containing classes that have the parent class of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentClassOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

/**
 * Sequence containing all classes that don't have some parent class of type.
 *
 * @return A sequence containing classes that don't have the parent class of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentClassOf(): Sequence<KoClassDeclaration> =
    this - withParentClassOf<T>().toSet()

/**
 * Sequence containing all classes that have parent class of type.
 *
 * @param names The Kotlin classes representing the parent classes to include.
 * @return A sequence containing classes that have the parent class of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all classes that have some parent class of type.
 *
 * @param names The classes representing the parent classes to exclude.
 * @return A sequence containing classes that don't have the parent class of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
