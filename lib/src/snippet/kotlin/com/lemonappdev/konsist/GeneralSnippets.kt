package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.list.constructors
import com.lemonappdev.konsist.api.ext.list.indexOfFirstInstance
import com.lemonappdev.konsist.api.ext.list.indexOfLastInstance
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withValueModifier
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import java.util.*
import javax.inject.Inject

class GeneralSnippets {
    fun `files in 'ext' package must have name ending with 'Ext'`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("..ext..")
            .assert { it.hasNameEndingWith("Ext") }
    }

    fun `properties are declared before functions`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assert {
                val lastKoPropertyDeclarationIndex = it
                    .declarations()
                    .indexOfLastInstance<KoPropertyDeclaration>()

                val firstKoFunctionDeclarationIndex = it
                    .declarations()
                    .indexOfFirstInstance<KoFunctionDeclaration>()

                lastKoPropertyDeclarationIndex <= firstKoFunctionDeclarationIndex
            }
    }

    fun `every constructor parameter has name derived from parameter type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .parameters
            .assert {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }

    fun `every class constructor has alphabetically ordered parameters`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .assert {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = names.sorted()
                names == sortedNames
            }
    }

    fun `companion object is last declaration in the class`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assert {
                val companionObject = it.objects().lastOrNull { obj ->
                    obj.hasModifiers(KoModifier.COMPANION)
                }

                companionObject != null && it.declarations().last() == companionObject
            }
    }

    fun `companion objects are last declarations in the class`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assert {
                val companionObjects = it.objects().filter { obj ->
                    obj.hasModifiers(KoModifier.COMPANION)
                }

                if (companionObjects.isEmpty()) {
                    return@assert true
                }

                it.declarations().takeLast(companionObjects.size) == companionObjects
            }
    }

    fun `every value class has parameter named 'value'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withValueModifier()
            .mapNotNull { it.primaryConstructor }
            .assert { it.hasParameterNamed("value") }
    }

    fun `no empty files allowed`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { it.text.isEmpty() }
    }

    fun `no field should have 'm' prefix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .properties()
            .assertNot {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    fun `no class should use field injection`() {
        Konsist
            .scopeFromProject()
            .classes()
            .properties()
            .assertNot { it.hasAnnotationOf<Inject>() }
    }

    fun `no class should use Java util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { it.hasImports("java.util.logging..") }
    }

    fun `package name must match file path`() {
        Konsist
            .scopeFromProject()
            .packages
            .assert { it.hasMatchingPath }
    }

    fun `no wildcard imports allowed`() {
        Konsist
            .scopeFromProject()
            .imports
            .assertNot { it.isWildcard }
    }

    fun `forbid the usage of 'forbiddenString' in file`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { it.text.contains("forbiddenString") }
    }
}
