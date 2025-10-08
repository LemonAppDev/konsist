package com.lemonappdev.sample.fortypetest.importaliascase

import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.ClassType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType as ClassTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.InterfaceType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType as InterfaceTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.ObjectType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType as ObjectTypeImportAlias
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.other.TypeAliasType
import com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType as TypeAliasTypeImportAlias

class ClassContainingParameterWithClassTypeWithImportAlias(val parameter: ClassTypeImportAlias) {
    lateinit var property: ClassType
}

fun functionContainingParameterWithClassTypeWithImportAlias(parameter: ClassTypeImportAlias) {}

class ClassContainingParameterWithInterfaceTypeWithImportAlias(val parameter: InterfaceTypeImportAlias) {
    lateinit var property: InterfaceType
}

fun functionContainingParameterWithInterfaceTypeWithImportAlias(parameter: InterfaceTypeImportAlias) {}

class ClassContainingParameterWithObjectTypeWithImportAlias(val parameter: ObjectTypeImportAlias) {
    lateinit var property: ObjectType
}

fun functionContainingParameterWithObjectTypeWithImportAlias(parameter: ObjectTypeImportAlias) {}

class ClassContainingParameterWithTypeAliasTypeWithImportAlias(val parameter: TypeAliasTypeImportAlias) {
    lateinit var property: TypeAliasType
}

fun functionContainingParameterWithTypeAliasTypeWithImportAlias(parameter: TypeAliasTypeImportAlias) {}
