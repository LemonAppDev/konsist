package com.lemonappdev.sample

class ClassType {
    class FirstClass(val sampleProperty1: NestedClassType)

    class NestedClassType

    object NestedObjectType

    interface NestedInterfaceType
}

class ObjectType {
    class SecondClass(val sampleProperty1: NestedObjectType)

    class NestedClassType

    object NestedObjectType

    interface NestedInterfaceType
}

class InterfaceType {
    class ThirdClass(val sampleProperty1: NestedInterfaceType)

    class NestedClassType

    object NestedObjectType

    interface NestedInterfaceType
}
