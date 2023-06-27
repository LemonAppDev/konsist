package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot

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
}
