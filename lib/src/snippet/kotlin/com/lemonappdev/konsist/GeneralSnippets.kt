package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.sequence.withValueModifier
import com.lemonappdev.konsist.core.ext.indexOfFirst
import com.lemonappdev.konsist.core.ext.indexOfLast
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import java.util.*

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
            .assert { it.hasAnnotations("javax.inject.Inject") }
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
            .assert { it.hasMatchingFilePath }
    }

    fun `Kotlin member order - properties are before functions`() {
        Konsist.scopeFromProject()
            .classes()
            .assert {
                val lastKoPropertyDeclarationIndex = it
                    .declarations()
                    .indexOfLast<KoPropertyDeclaration>()

                val firstKoFunctionDeclarationIndex = it
                    .declarations()
                    .indexOfFirst<KoFunctionDeclaration>()

                lastKoPropertyDeclarationIndex < firstKoFunctionDeclarationIndex
            }
    }

    fun `no wildcard imports allowed`() {
        Konsist.scopeFromProject()
            .imports()
            .assertNot { it.isWildcard }
    }

    fun `value class has parameter named 'value'`() {
        Konsist.scopeFromProject()
            .classes()
            .withValueModifier()
            .mapNotNull { it.primaryConstructor }
            .assert { it.hasParameterNamed("value") }
    }
}
