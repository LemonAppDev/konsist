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

fun Sequence<KoClass>.withExplicitPrimaryConstructor() = filter { it.hasExplicitPrimaryConstructor() }

fun Sequence<KoClass>.withoutExplicitPrimaryConstructor() = filterNot { it.hasExplicitPrimaryConstructor() }

fun Sequence<KoClass>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withoutSecondaryConstructors() = filterNot { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withParent() = filter { it.hasParent() }

fun Sequence<KoClass>.withoutParent() = filterNot { it.hasParent() }

inline fun <reified T> Sequence<KoClass>.withParentOf() = filter { koClass ->
    koClass
        .parents
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentOf() = this - withParentOf<T>().toSet()

fun Sequence<KoClass>.withParents(vararg names: String) = filter { koClass -> names.all { koClass.hasParent(it) } }

fun Sequence<KoClass>.withSomeParents(vararg names: String) = filter { koClass -> names.any { koClass.hasParent(it) } }

fun Sequence<KoClass>.withoutParents(vararg names: String) = filter { koClass -> names.none { koClass.hasParent(it) } }

fun Sequence<KoClass>.withParents(vararg names: KClass<*>) = filter { koClass ->
    names.all { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParents(vararg names: KClass<*>) = filter { koClass ->
    names.any { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParents(vararg names: KClass<*>) = filter { koClass ->
    names.none { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentInterface() = filter { it.hasParentInterface() }

fun Sequence<KoClass>.withoutParentInterface() = filterNot { it.hasParentInterface() }

inline fun <reified T> Sequence<KoClass>.withParentInterfaceOf() = filter { koClass ->
    koClass
        .parentInterfaces
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentInterfaceOf() = this - withParentInterfaceOf<T>().toSet()

fun Sequence<KoClass>.withParentInterfaces(vararg names: String) =
    filter { koClass -> names.all { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withSomeParentInterfaces(vararg names: String) =
    filter { koClass -> names.any { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withoutParentInterfaces(vararg names: String) =
    filter { koClass -> names.none { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withParentInterfaces(vararg names: KClass<*>) = filter { koClass ->
    names.all { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParentInterfaces(vararg names: KClass<*>) = filter { koClass ->
    names.any { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParentInterfaces(vararg names: KClass<*>) = filter { koClass ->
    names.none { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentClass(vararg names: String) = filter { koClass ->
    if (names.isEmpty()) {
        koClass.hasParentClass()
    } else {
        names.any {
            koClass.hasParentClass(it)
        }
    }
}

fun Sequence<KoClass>.withoutParentClass(vararg names: String) = filter { koClass ->
    if (names.isEmpty()) {
        !koClass.hasParentClass()
    } else {
        names.none {
            koClass.hasParentClass(it)
        }
    }
}

inline fun <reified T> Sequence<KoClass>.withParentClassOf() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClass>.withoutParentClassOf() = this - withParentClassOf<T>().toSet()
