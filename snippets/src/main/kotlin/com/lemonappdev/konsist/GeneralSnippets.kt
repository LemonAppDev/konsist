package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import java.util.*

class GeneralSnippets {
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

    fun `every class constructor has alphabetically ordered parameters`() {
        Konsist.scopeFromProject()
            .classes()
            .flatMap { it.allConstructors }
            .assert {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = it.parameters.map { parameter -> parameter.name }.sorted()
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

    fun `no wildcard imports allowed`() {
        Konsist.scopeFromProject()
            .imports()
            .assertNot { it.isWildcard }
    }

    fun `no class should use Java util logging`() {
        Konsist.scopeFromProject()
            .files()
        TODO("remove comment after add files assert")
//            .assert { it.hasImports("java.util.logging..")}
    }

    fun `every constructor parameter has name derived from parameter type`() {
        Konsist.scopeFromProject()
            .classes()
            .mapNotNull { it.primaryConstructor }
            .flatMap { it.parameters }
            .assert { it.name.toTitleCase() == it.type.sourceType }
    }
}

private fun String.toTitleCase() = replaceFirstChar { it.titlecase(Locale.getDefault()) }