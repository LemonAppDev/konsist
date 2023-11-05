package com.lemonappdev.konsist.testdata

open class SampleParentClass

open class SampleParentClass1

open class SampleParentClass2

open class SampleParentClassWithDuplicatedName

class SampleClass

class SampleClass1

class SampleClass2

open class SampleClassWithParameter(val param: String)

open class SampleGenericClassWithParameter<T>(val param: String)

open class SampleCollection1<out E> : Collection<E> {
    override val size: Int = 1

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<E> {
        return this.iterator()
    }

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = false

    override fun contains(element: @UnsafeVariance E): Boolean = false
}

class SampleCollection2<out E, out V> : Collection<E> {
    override val size: Int = 1

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<E> {
        return this.iterator()
    }

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = false

    override fun contains(element: @UnsafeVariance E): Boolean = false
}

class SampleType

class SampleType1

class SampleType2

interface SampleInterface

interface SampleInterface1

interface SampleInterface2

interface SampleParentInterface

interface SampleParentInterface1

interface SampleParentInterface2

interface SampleGenericSuperInterface<T>

object SampleObject

annotation class NonExistingAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
)
annotation class SampleAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
)
annotation class SampleAnnotation1

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
)
annotation class SampleAnnotation2

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS,
)
annotation class SampleAnnotationWithParameter(val sampleParameter: String)

annotation class SampleAnnotationWithParameters(val sampleParameter1: String, val sampleParameter2: Boolean)

@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS)
annotation class SampleAnnotationWithAngleBrackets<T, U>
