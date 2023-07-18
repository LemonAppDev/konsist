package com.lemonappdev.konsist.testdata

class SampleClass

class SampleClass1

class SampleClass2

class SampleCollection1<out E> : Collection<E> {
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

object SampleObject

annotation class NonExistingAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.TYPEALIAS,
)
annotation class SampleAnnotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
)
annotation class SampleAnnotation1

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPEALIAS,
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

@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS)
annotation class SampleAnnotationWithAngleBrackets<T, U>
