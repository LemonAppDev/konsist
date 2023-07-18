package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing all classes with `enum` modifier.
 *
 * @return A sequence containing classes with the `enum` modifier.
 */
fun Sequence<KoClassDeclaration>.withEnumModifier(): Sequence<KoClassDeclaration> = filter { it.hasEnumModifier() }

/**
 * Sequence containing all classes without `enum` modifier.
 *
 * @return A sequence containing classes without the `enum` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutEnumModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all classes with `sealed` modifier.
 *
 * @return A sequence containing classes with the `sealed` modifier.
 */
fun Sequence<KoClassDeclaration>.withSealedModifier(): Sequence<KoClassDeclaration> = filter { it.hasSealedModifier() }

/**
 * Sequence containing all classes without `sealed` modifier.
 *
 * @return A sequence containing classes without the `sealed` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutSealedModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all classes with `inner` modifier.
 *
 * @return A sequence containing classes with the `inner` modifier.
 */
fun Sequence<KoClassDeclaration>.withInnerModifier(): Sequence<KoClassDeclaration> = filter { it.hasInnerModifier() }

/**
 * Sequence containing all classes without `inner` modifier.
 *
 * @return A sequence containing classes without the `inner` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutInnerModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all classes with `value` modifier.
 *
 * @return A sequence containing classes with the `value` modifier.
 */
fun Sequence<KoClassDeclaration>.withValueModifier(): Sequence<KoClassDeclaration> = filter { it.hasValueModifier() }

/**
 * Sequence containing all classes without `value` modifier.
 *
 * @return A sequence containing classes without the `value` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutValueModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all classes with `annotation` modifier.
 *
 * @return A sequence containing classes with the `annotation` modifier.
 */
fun Sequence<KoClassDeclaration>.withAnnotationModifier(): Sequence<KoClassDeclaration> = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all classes without `annotation` modifier.
 *
 * @return A sequence containing classes without the `annotation` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAnnotationModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all classes with `data` modifier.
 *
 * @return A sequence containing classes with the `data` modifier.
 */
fun Sequence<KoClassDeclaration>.withDataModifier(): Sequence<KoClassDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all classes without `data` modifier.
 *
 * @return A sequence containing classes without the `data` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutDataModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all classes with `actual` modifier.
 *
 * @return A sequence containing classes with the `actual` modifier.
 */
fun Sequence<KoClassDeclaration>.withActualModifier(): Sequence<KoClassDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing all classes without `actual` modifier.
 *
 * @return A sequence containing classes without the `actual` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutActualModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all classes with `expect` modifier.
 *
 * @return A sequence containing classes with the `expect` modifier.
 */
fun Sequence<KoClassDeclaration>.withExpectModifier(): Sequence<KoClassDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing all classes without `expect` modifier.
 *
 * @return A sequence containing classes without the `expect` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutExpectModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all classes with `abstract` modifier.
 *
 * @return A sequence containing classes with the `abstract` modifier.
 */
fun Sequence<KoClassDeclaration>.withAbstractModifier(): Sequence<KoClassDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all classes without `abstract` modifier.
 *
 * @return A sequence containing classes without the `abstract` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutAbstractModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all classes with `open` modifier.
 *
 * @return A sequence containing classes with the `open` modifier.
 */
fun Sequence<KoClassDeclaration>.withOpenModifier(): Sequence<KoClassDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing all classes without `open` modifier.
 *
 * @return A sequence containing classes without the `open` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutOpenModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all classes with `final` modifier.
 *
 * @return A sequence containing classes with the `final` modifier.
 */
fun Sequence<KoClassDeclaration>.withFinalModifier(): Sequence<KoClassDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing all classes without `final` modifier.
 *
 * @return A sequence containing classes without the `final` modifier.
 */
fun Sequence<KoClassDeclaration>.withoutFinalModifier(): Sequence<KoClassDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing all classes with primary constructor.
 *
 * @return A sequence containing classes with primary constructor.
 */
fun Sequence<KoClassDeclaration>.withPrimaryConstructor(): Sequence<KoClassDeclaration> = filter { it.hasPrimaryConstructor() }

/**
 * Sequence containing all classes without primary constructor.
 *
 * @return A sequence containing classes without primary constructor.
 */
fun Sequence<KoClassDeclaration>.withoutPrimaryConstructor(): Sequence<KoClassDeclaration> = filterNot { it.hasPrimaryConstructor() }

/**
 * Sequence containing all classes with secondary constructors.
 *
 * @return A sequence containing classes with secondary constructor(s).
 */
fun Sequence<KoClassDeclaration>.withSecondaryConstructors(): Sequence<KoClassDeclaration> = filter { it.hasSecondaryConstructors() }

/**
 * Sequence containing all classes without secondary constructors.
 *
 * @return A sequence containing classes without secondary constructor(s).
 */
fun Sequence<KoClassDeclaration>.withoutSecondaryConstructors(): Sequence<KoClassDeclaration> = filterNot { it.hasSecondaryConstructors() }

/**
 * Sequence containing all classes with class or interface parent.
 *
 * @return A sequence containing classes with class or interface parent.
 */
fun Sequence<KoClassDeclaration>.withParents(): Sequence<KoClassDeclaration> = filter { it.hasParents() }

/**
 * Sequence containing all classes with all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A sequence containing classes with all specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withAllParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParents(name, *names)
}

/**
 * Sequence containing all classes with some parents.
 *
 * @param name The name of the parent to include.
 * @param names The names of the parents to include.
 * @return A sequence containing classes with at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParents(name) || names.any { name -> it.hasParents(name) }
}

/**
 * Sequence containing all classes with no parent - class does not extend any class and does not implement any interface.
 *
 * @return A sequence containing classes with no parent - class does not extend any class and does not implement any interface.
 */
fun Sequence<KoClassDeclaration>.withoutParents(): Sequence<KoClassDeclaration> = filterNot { it.hasParents() }

/**
 * Sequence containing all classes without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A sequence containing classes without all specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withoutAllParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    !it.hasParents(name, *names)
}

/**
 * Sequence containing all classes without some parents represented by name.
 *
 * @param name The name of the parent to exclude.
 * @param names The names of the parents to exclude.
 * @return A sequence containing classes without at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withoutSomeParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    !it.hasParents(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParents(name) }
    } else {
        true
    }
}

/**
 * Sequence containing all classes with named parents.
 *
 * @return A sequence containing classes with the parent of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parents
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all classes without named parents.
 *
 * @return A sequence containing classes without parent of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentOf(): Sequence<KoClassDeclaration> = this - withParentOf<T>().toSet()

/**
 * Sequence containing all classes with named parents.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin classes representing the parents to include.
 * @return A sequence containing classes with the parents of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withAllParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.any { parent -> parent.name == name.simpleName } &&
        names.all { kClass ->
            it
                .parents
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes with some named parents.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin classes representing the parents to include.
 * @return A sequence containing classes with at least one of the specified parent(s).
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
 * Sequence containing all classes without named parents.
 *
 * @param name The Kotlin class representing the parent to exclude.
 * @param names The Kotlin classes representing the parents to exclude.
 * @return A sequence containing classes without parents of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutAllParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.none { parent -> parent.name == name.simpleName } &&
        names.none { kClass ->
            it
                .parents
                .any { parent -> parent.name == kClass.simpleName }
        }
}

/**
 * Sequence containing all classes without some named parents.
 *
 * @param name The Kotlin class representing the parent to exclude.
 * @param names The Kotlin classes representing the parents to exclude.
 * @return A sequence containing classes without at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withoutSomeParentsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    it.parents.none { parent -> parent.name == name.simpleName } &&
        if (names.isNotEmpty()) {
            names.any { kClass ->
                it
                    .parents
                    .none { parent -> parent.name == kClass.simpleName }
            }
        } else {
            true
        }
}

/**
 * Sequence containing all classes with any named parent interface.
 *
 * @return A sequence containing classes with any parent interface.
 */
fun Sequence<KoClassDeclaration>.withParentInterfaces(): Sequence<KoClassDeclaration> = filter { it.hasParentInterfaces() }

/**
 * Sequence containing all classes with all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @return A sequence containing classes with all the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withAllParentInterfaces(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParentInterfaces(name, *names)
}

/**
 * Sequence containing all classes with some named parent interface.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A sequence containing classes with at least one of the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParentInterfaces(name: String, vararg names: String): Sequence<KoClassDeclaration> =
    filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * Sequence containing all classes with no named parent interface.
 *
 * @return A sequence containing classes with no parent interface.
 */
fun Sequence<KoClassDeclaration>.withoutParentInterfaces(): Sequence<KoClassDeclaration> = filterNot { it.hasParentInterfaces() }

/**
 * Sequence containing all classes without all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A sequence containing classes without all specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withoutAllParentInterfaces(name: String, vararg names: String): Sequence<KoClassDeclaration> = filterNot {
    it.hasParentInterfaces(name, *names)
}

/**
 * Sequence containing all classes without some named parent interface.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of the parent interfaces to exclude.
 * @return A sequence containing classes without at least one of the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withoutSomeParentInterfaces(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    !it.hasParentInterfaces(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentInterfaces(name) }
    } else {
        true
    }
}

/**
 * Sequence containing all classes with named parent interface.
 *
 * @return A sequence containing classes with the parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentInterfaceOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all classes without some named parent interface.
 *
 * @return A sequence containing classes without parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentInterfaceOf(): Sequence<KoClassDeclaration> =
    this - withParentInterfaceOf<T>().toSet()

/**
 * Sequence containing all classes with all specified parent interfaces of type.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin classes representing the parent interfaces to include.
 * @return A sequence containing classes with the parent interfaces of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } &&
            names.all { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all classes with some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin classes representing the parent interfaces to include.
 * @return A sequence containing classes with at least one of the specified parent interface(s).
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
 * Sequence containing all classes without named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin classes representing the parent interfaces to exclude.
 * @return A sequence containing classes without parent interfaces of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            names.none { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all classes without some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin classes representing the parent interfaces to exclude.
 * @return A sequence containing classes without at least one of the specified parent interface(s).
 */
fun Sequence<KoClassDeclaration>.withoutSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<KoClassDeclaration> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            if (names.isNotEmpty()) {
                names.any { kClass ->
                    it
                        .parentInterfaces
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }
    }

/**
 * Sequence containing all classes with parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A sequence containing classes with the specified parent class (or any parent class if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all classes with some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A sequence containing classes without specified parent class (or none parent class if [names] is empty).
 */
fun Sequence<KoClassDeclaration>.withoutParentClass(vararg names: String): Sequence<KoClassDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all classes with named parent class.
 *
 * @return A sequence containing classes with the parent class of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withParentClassOf(): Sequence<KoClassDeclaration> = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

/**
 * Sequence containing all classes without some named parent class.
 *
 * @return A sequence containing classes without parent class of the specified type.
 */
inline fun <reified T> Sequence<KoClassDeclaration>.withoutParentClassOf(): Sequence<KoClassDeclaration> =
    this - withParentClassOf<T>().toSet()

/**
 * Sequence containing all classes with named parent class.
 *
 * @param names The Kotlin classes representing the parent classes to include.
 * @return A sequence containing classes with the parent class of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all classes with some named parent class.
 *
 * @param names The classes representing the parent classes to exclude.
 * @return A sequence containing classes without parent class of the specified type(s).
 */
fun Sequence<KoClassDeclaration>.withoutParentClassOf(vararg names: KClass<*>): Sequence<KoClassDeclaration> = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all classes that have init block(s).
 *
 * @return A sequence containing classes with the init block(s).
 */
fun Sequence<KoClassDeclaration>.withInitBlocks(): Sequence<KoClassDeclaration> = filter { it.hasInitBlocks() }

/**
 * Sequence containing all classes without init block(s).
 *
 * @return A sequence containing classes without the init block(s).
 */
fun Sequence<KoClassDeclaration>.withoutInitBlocks(): Sequence<KoClassDeclaration> = filterNot { it.hasInitBlocks() }
