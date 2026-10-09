package com.lemonappdev.konsist.testdata

const val FIXTURE_PROPERTY: Int = 0

fun fixtureFunction() = {}

open class FixtureParentClass

open class FixtureParentClass1

open class FixtureParentClass2

open class FixtureParentClassWithDuplicatedName

open class FixtureParentClassWithNestedDeclarations {
    open class FixtureNestedClass
}

class FixtureClass

class FixtureClass1

class FixtureClass2

open class FixtureClassWithParameter(
    val param: String,
)

open class FixtureGenericClassWithParameter<T>(
    val param: String,
)

open class FixtureCollection1<out E> : Collection<E> {
    override val size: Int = 1

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<E> = this.iterator()

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = false

    override fun contains(element: @UnsafeVariance E): Boolean = false
}

class FixtureCollection2<out E, out V> : Collection<E> {
    override val size: Int = 1

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<E> = this.iterator()

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = false

    override fun contains(element: @UnsafeVariance E): Boolean = false
}

class FixtureType

class FixtureType1

class FixtureType2

interface FixtureInterface

interface FixtureInterface1

interface FixtureInterface2

interface FixtureParentInterface

interface FixtureParentInterface1

interface FixtureParentInterface2

interface FixtureParentInterfaceWithNestedDeclarations {
    interface FixtureNestedInterface

    open class FixtureNestedClass
}

interface FixtureGenericSuperInterface<T>

object FixtureObject

typealias FixtureTypeAlias = (FixtureClass) -> Unit

typealias FixtureBasicTypeAlias = FixtureClass

annotation class NonExistingAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.TYPE,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class FixtureAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.TYPE,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class FixtureAnnotation1

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.TYPE,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class FixtureAnnotation2

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS,
)
annotation class FixtureAnnotationWithParameter(
    val fixtureParameter: String,
)

annotation class FixtureAnnotationWithParameters(
    val fixtureParameter1: String,
    val fixtureParameter2: Boolean,
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS)
annotation class FixtureAnnotationWithAngleBrackets<T, U>
