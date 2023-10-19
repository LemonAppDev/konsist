package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.list.constructors
import com.lemonappdev.konsist.api.ext.list.indexOfFirstInstance
import com.lemonappdev.konsist.api.ext.list.indexOfLastInstance
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withModifier
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withValueModifier
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifier
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import java.util.*
import javax.inject.Inject

class GeneralSnippets {
    fun `files in 'ext' package must have name ending with 'Ext'`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("..ext..")
            .assertTrue { it.hasNameEndingWith("Ext") }
    }

    fun `all data class properties are defined in constructor`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withModifier(KoModifier.DATA)
            .properties()
            .assertTrue {
                it.isConstructorDefined
            }
    }

    fun `every class has test`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .assertTrue { it.hasTestClass() }
    }

    fun `every class - except data and value class - has test`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withoutModifier(KoModifier.DATA, KoModifier.VALUE)
            .assertTrue { it.hasTestClass() }
    }

    fun `properties are declared before functions`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assertTrue {
                val lastKoPropertyDeclarationIndex = it
                    .declarations(includeNested = false, includeLocal = false)
                    .indexOfLastInstance<KoPropertyDeclaration>()

                val firstKoFunctionDeclarationIndex = it
                    .declarations(includeNested = false, includeLocal = false)
                    .indexOfFirstInstance<KoFunctionDeclaration>()

                if (lastKoPropertyDeclarationIndex != -1 && firstKoFunctionDeclarationIndex != -1) {
                    lastKoPropertyDeclarationIndex < firstKoFunctionDeclarationIndex
                } else {
                    true
                }
            }
    }

    fun `every constructor parameter has name derived from parameter type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .parameters
            .assertTrue {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }

    fun `every class constructor has alphabetically ordered parameters`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .assertTrue {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = names.sorted()
                names == sortedNames
            }
    }

    fun `companion object is last declaration in the class`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assertTrue {
                val companionObject = it.objects(includeNested = false).lastOrNull { obj ->
                    obj.hasModifier(KoModifier.COMPANION)
                }

                if (companionObject != null) {
                    it.declarations(includeNested = false, includeLocal = false).last() == companionObject
                } else {
                    true
                }
            }
    }

    fun `every value class has parameter named 'value'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withValueModifier()
            .primaryConstructors
            .assertTrue { it.hasParameterWithName("value") }
    }

    fun `no empty files allowed`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.text.isEmpty() }
    }

    fun `no field should have 'm' prefix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .properties()
            .assertFalse {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    fun `no class should use field injection`() {
        Konsist
            .scopeFromProject()
            .classes()
            .properties()
            .assertFalse { it.hasAnnotationOf<Inject>() }
    }

    fun `no class should use Java util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "java.util.logging.." } }
    }

    fun `package name must match file path`() {
        Konsist
            .scopeFromProject()
            .packages
            .assertTrue { it.hasMatchingPath }
    }

    fun `no wildcard imports allowed`() {
        Konsist
            .scopeFromProject()
            .imports
            .assertFalse { it.isWildcard }
    }

    fun `forbid the usage of 'forbiddenString' in file`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.text.contains("forbiddenString") }
    }
}
