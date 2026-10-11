package com.lemonappdev.konsist.testdata

import com.unknown.UnknownClass
import com.unknown.UnknownInterface
import com.unknown.UnknownType

/*
 * This file is in the resources, so it is not compiled. Types imported from `com.unknown` are not declared anywhere,
 * so they are neither on the classpath nor in the project files.
 */
class FixtureClassWithParentClassNotOnClasspath :
    UnknownClass(),
    UnknownInterface

class FixtureClassWithDelegatedParentInterfaceNotOnClasspath(
    val fixtureProperty: UnknownInterface,
) : UnknownInterface by fixtureProperty

class FixtureClassWithoutPrimaryConstructorWithParentClassNotOnClasspath : UnknownClass {
    constructor() : super()
}

interface FixtureInterfaceWithParentInterfaceNotOnClasspath : UnknownInterface

object FixtureObjectWithParentInterfaceNotOnClasspath : UnknownInterface

class FixtureClassWithTypeNotOnClasspath(
    val fixtureProperty: UnknownType,
)
