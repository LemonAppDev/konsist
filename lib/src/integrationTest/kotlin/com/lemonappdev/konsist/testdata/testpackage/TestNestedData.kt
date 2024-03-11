package com.lemonappdev.konsist.testdata.testpackage

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.LOCAL_VARIABLE,
)
annotation class Annotation

// Added to have a better file name (ktlint complains)
class Type
