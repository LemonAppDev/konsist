package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

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
 * Sequence containing all classes with class or interface parent.
 *
 * @return A sequence containing classes with class or interface parent.
 */
fun Sequence<KoClassDeclaration>.withParents(): Sequence<KoClassDeclaration> = filter { it.hasParentDeclarations() }

/**
 * Sequence containing all classes with all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A sequence containing classes with all specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withAllParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParentDeclarations(name, *names)
}

/**
 * Sequence containing all classes with some parents.
 *
 * @param name The name of the parent to include.
 * @param names The names of the parents to include.
 * @return A sequence containing classes with at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withSomeParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    it.hasParentDeclarations(name) || names.any { name -> it.hasParentDeclarations(name) }
}

/**
 * Sequence containing all classes with no parent - class does not extend any class and does not implement any interface.
 *
 * @return A sequence containing classes with no parent - class does not extend any class and does not implement any interface.
 */
fun Sequence<KoClassDeclaration>.withoutParents(): Sequence<KoClassDeclaration> = filterNot { it.hasParentDeclarations() }

/**
 * Sequence containing all classes without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A sequence containing classes without all specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withoutAllParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    !it.hasParentDeclarations(name, *names)
}

/**
 * Sequence containing all classes without some parents represented by name.
 *
 * @param name The name of the parent to exclude.
 * @param names The names of the parents to exclude.
 * @return A sequence containing classes without at least one of the specified parent(s).
 */
fun Sequence<KoClassDeclaration>.withoutSomeParents(name: String, vararg names: String): Sequence<KoClassDeclaration> = filter {
    !it.hasParentDeclarations(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentDeclarations(name) }
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
        .parentDeclarations
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
    it.parentDeclarations.any { parent -> parent.name == name.simpleName } &&
        names.all { kClass ->
            it
                .parentDeclarations
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
    it.parentDeclarations.any { parent -> parent.name == name.simpleName } ||
        names.any { kClass ->
            it
                .parentDeclarations
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
    it.parentDeclarations.none { parent -> parent.name == name.simpleName } &&
        names.none { kClass ->
            it
                .parentDeclarations
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
    it.parentDeclarations.none { parent -> parent.name == name.simpleName } &&
        if (names.isNotEmpty()) {
            names.any { kClass ->
                it
                    .parentDeclarations
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

/**
 * Sequence containing all classes that have init block(s).
 *
 * @return A sequence containing classes with the init block(s).
 */
fun Sequence<KoClassDeclaration>.withInitBlocks(): Sequence<KoClassDeclaration> = filter { it.hasInitBlocks() }

/**
 * Sequence containing all classes that don't have init block(s).
 *
 * @return A sequence containing classes without the init block(s).
 */
fun Sequence<KoClassDeclaration>.withoutInitBlocks(): Sequence<KoClassDeclaration> = filterNot { it.hasInitBlocks() }
