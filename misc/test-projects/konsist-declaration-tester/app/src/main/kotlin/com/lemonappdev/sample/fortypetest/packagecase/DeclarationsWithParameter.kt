package com.lemonappdev.sample.fortypetest.packagecase

class ClassWithClassTypeParameter(val parameter: ClassType = ClassType.FIRST)

fun functionWithClassTypeParameter(parameter: ClassType = ClassType.FIRST) {}

class ClassWithInterfaceTypeParameter(val parameter: InterfaceType)

fun functionWithInterfaceTypeParameter(parameter: InterfaceType) {}

class ClassWithObjectTypeParameter(val parameter: ObjectType)

fun functionWithObjectTypeParameter(parameter: ObjectType) {}

class ClassWithTypeAliasTypeParameter(val parameter: TypeAliasType)

fun functionWithTypeAliasTypeParameter(parameter: TypeAliasType) {}
