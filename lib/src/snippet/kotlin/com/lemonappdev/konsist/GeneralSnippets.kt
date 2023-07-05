package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withValueModifier
import com.lemonappdev.konsist.core.ext.indexOfFirstInstance
import com.lemonappdev.konsist.core.ext.indexOfLastInstance
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import java.util.*
import javax.inject.Inject

class GeneralSnippets {
    fun `no empty files allowed`() {
        Konsist.scopeFromProject()
            .files()
            .assertNot { it.text.isEmpty() }
    }

    fun `no field should have 'm' prefix`() {
        Konsist.scopeFromProject()
            .classes()
            .assert {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    fun `no class should use field injection`() {
        Konsist.scopeFromProject()
            .classes()
            .assert { it.hasAnnotationOf<Inject>() }
    }

    fun `no class should use Java util logging`() {
        Konsist.scopeFromProject()
            .files()
            .assert { it.hasImports("java.util.logging..") }
    }

    fun `every constructor parameter has name derived from parameter type`() {
        Konsist.scopeFromProject()
            .classes()
            .flatMap { it.allConstructors }
            .flatMap { it.parameters }
            .assert {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }

    fun `every class constructor has alphabetically ordered parameters`() {
        Konsist.scopeFromProject()
            .classes()
            .flatMap { it.allConstructors }
            .assert {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = names.sorted()
                names == sortedNames
            }
    }

    fun `package name must match file path`() {
        Konsist.scopeFromProject()
            .packages()
            .assert {
                it
                    .filePath
                    .replace("/", ".")
                    .endsWith(it.qualifiedName)
            }
    }

    fun `properties are declared before functions`() {
        Konsist.scopeFromProject()
            .classes()
            .assert {
                val lastKoPropertyDeclarationIndex = it
                    .declarations()
                    .indexOfLastInstance<KoPropertyDeclaration>()

                val firstKoFunctionDeclarationIndex = it
                    .declarations()
                    .indexOfFirstInstance<KoFunctionDeclaration>()

                lastKoPropertyDeclarationIndex < firstKoFunctionDeclarationIndex
            }
    }

    fun `companion object is the last declaration in the class`() {
        Konsist.scopeFromProject()
            .classes()
            .assert {
                val companionObjectIndex = it
                    .declarations()
                    .indexOfLast { declaration ->
                        declaration is KoObjectDeclaration && declaration.hasModifiers(KoModifier.COMPANION)
                    }

                val lastIndex = it.numDeclarations() - 1

                companionObjectIndex == lastIndex
            }
    }

    fun `no wildcard imports allowed`() {
        Konsist.scopeFromProject()
            .imports()
            .assertNot { it.isWildcard }
    }

    fun `every value class has parameter named 'value'`() {
        Konsist.scopeFromProject()
            .classes()
            .withValueModifier()
            .mapNotNull { it.primaryConstructor }
            .assert { it.hasParameterNamed("value") }
    }

    fun `every class in the 'feature' module reside in package 'feature'`() {
        Konsist.scopeFromModule("feature")
            .classes(includeNested = true)
            .assert { it.resideInPackage("..feature..") }
    }
}
