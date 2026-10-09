@file:Suppress("detekt.InvalidPackageDeclaration")
package com.lemonappdev.fixture.fortypetest.importaliascase.declarations.other

enum class ClassType {
    FIRST,
    SECOND;
}

interface InterfaceType

object ObjectType

typealias TypeAliasType = () -> Unit
