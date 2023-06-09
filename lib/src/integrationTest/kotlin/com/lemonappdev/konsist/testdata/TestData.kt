package com.lemonappdev.konsist.testdata

class SampleClass

class SampleClass1

class SampleClass2

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
