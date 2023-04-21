package com.lemonappdev.konsist.testdata

class SampleClass

class SampleClass1

class SampleClass2

class SampleType

interface SampleInterface

interface SampleInterface1

interface SampleInterface2

object SampleObject

interface SampleTopLevelInterface {
    companion object SampleCompanionObject
}

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

annotation class SampleAnnotation1

annotation class SampleAnnotation2
