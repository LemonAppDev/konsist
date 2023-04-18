@file:Suppress("detekt.TooManyFunctions")

package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass
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

fun Sequence<KoClass>.withParentNamed(name: String) = filter { it.hasParent(name) }

fun Sequence<KoClass>.withoutParentNamed(name: String) = filterNot { it.hasParent(name) }

inline fun <reified T> Sequence<KoClass>.withParentTyped() = filter { koClass ->
    koClass
        .parents
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentTyped() = this - withParentTyped<T>().toSet()

fun Sequence<KoClass>.withParents(vararg name: String) = filter { koClass -> name.all { koClass.hasParent(it) } }

fun Sequence<KoClass>.withoutParents(vararg name: String) = filter { koClass -> name.none { koClass.hasParent(it) } }

fun Sequence<KoClass>.withSomeParents(vararg name: String) = filter { koClass -> name.any { koClass.hasParent(it) } }

fun Sequence<KoClass>.withParents(vararg name: KClass<*>) = filter { koClass ->
    name.all { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParents(vararg name: KClass<*>) = filter { koClass ->
    name.none { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParents(vararg name: KClass<*>) = filter { koClass ->
    name.any { kClass ->
        koClass
            .parents
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentInterface() = filter { it.hasParentInterface() }

fun Sequence<KoClass>.withoutParentInterface() = filterNot { it.hasParentInterface() }

fun Sequence<KoClass>.withParentInterfaceNamed(name: String) = filter { it.hasParentInterface(name) }

fun Sequence<KoClass>.withoutParentInterfaceNamed(name: String) = filterNot { it.hasParentInterface(name) }

inline fun <reified T> Sequence<KoClass>.withParentInterfaceTyped() = filter { koClass ->
    koClass
        .parentInterfaces
        .any { it.name == T::class.simpleName }
}

inline fun <reified T> Sequence<KoClass>.withoutParentInterfaceTyped() = this - withParentInterfaceTyped<T>().toSet()

fun Sequence<KoClass>.withParentInterfaces(vararg name: String) =
    filter { koClass -> name.all { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withoutParentInterfaces(vararg name: String) =
    filter { koClass -> name.none { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withSomeParentInterfaces(vararg name: String) =
    filter { koClass -> name.any { koClass.hasParentInterface(it) } }

fun Sequence<KoClass>.withParentInterfaces(vararg name: KClass<*>) = filter { koClass ->
    name.all { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withoutParentInterfaces(vararg name: KClass<*>) = filter { koClass ->
    name.none { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withSomeParentInterfaces(vararg name: KClass<*>) = filter { koClass ->
    name.any { kClass ->
        koClass
            .parentInterfaces
            .any { parent -> parent.name == kClass.simpleName }
    }
}

fun Sequence<KoClass>.withParentClass() = filter { it.hasParentClass() }

fun Sequence<KoClass>.withoutParentClass() = filterNot { it.hasParentClass() }

fun Sequence<KoClass>.withParentClassNamed(name: String) = filter { it.hasParentClass(name) }

fun Sequence<KoClass>.withoutParentClassNamed(name: String) = filterNot { it.hasParentClass(name) }

inline fun <reified T> Sequence<KoClass>.withParentClassTyped() = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

inline fun <reified T> Sequence<KoClass>.withoutParentClassTyped() = this - withParentClassTyped<T>().toSet()
