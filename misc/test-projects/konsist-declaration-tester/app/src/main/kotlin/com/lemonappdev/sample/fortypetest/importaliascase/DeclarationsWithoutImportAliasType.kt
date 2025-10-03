package com.lemonappdev.sample.fortypetest.importaliascase

import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.ClassType as ClassTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.InterfaceType as InterfaceTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.ObjectType as ObjectTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.TypeAliasType as TypeAliasTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType

class ClassContainingParameterWithClassTypeWithoutImportAlias(val parameter: ClassType) {
    lateinit var property: ClassTypeImportAlias
}

fun functionContainingParameterWithClassTypeWithoutImportAlias(parameter: ClassType) {}

class ClassContainingParameterWithInterfaceTypeWithoutImportAlias(val parameter: InterfaceType) {
    lateinit var property: InterfaceTypeImportAlias
}

fun functionContainingParameterWithInterfaceTypeWithoutImportAlias(parameter: InterfaceType) {}

class ClassContainingParameterWithObjectTypeWithoutImportAlias(val parameter: ObjectType) {
    lateinit var property: ObjectTypeImportAlias
}

fun functionContainingParameterWithObjectTypeWithoutImportAlias(parameter: ObjectType) {}

class ClassContainingParameterWithTypeAliasTypeWithoutImportAlias(val parameter: TypeAliasType) {
    lateinit var property: TypeAliasTypeImportAlias
}

fun functionContainingParameterWithTypeAliasTypeWithoutImportAlias(parameter: TypeAliasType) {}
