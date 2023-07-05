package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot

class LibrarySnippets {
    fun `every api declaration has KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarations(includeNested = true)
            .assert { it.hasKDoc() }
    }

    fun `every api declaration has complete KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarations(includeNested = true)
            .assert { it.hasValidKDoc() }
    }

    fun `test classes should have test subject named sut`() {
        Konsist.scopeFromTest()
            .classes()
            .assert {
                val type = it.name.removeSuffix("Test")
                val sut = it
                    .properties()
                    .firstOrNull { property -> property.name == "sut" }

                sut != null && sut.type?.name == type
            }
    }

    fun `test classes should have all members private besides tests`() {
        Konsist.scopeFromTest()
            .classes()
            .flatMap { it.declarations() }
            .filterIsInstance<KoDeclaration>()
            .filterNot {
                it.annotations.any { annotation ->
                    annotation
                        .name
                        .lowercase()
                        .contains("test")
                }
            }
            .assert { it.hasPrivateModifier() }
    }

    fun `forbid string in files`() {
        Konsist.scopeFromProject()
            .files()
            .assertNot { it.text.contains("Forbidden string") }
    }

    fun `junit 'Test' annotation is not allowed for functions`() {
        Konsist.scopeFromTest()
            .functions(includeNested = true)
            .assertNot { it.hasAnnotations("org.junit.Test") }
    }

    fun `every class in the 'feature' module reside in package 'feature'`() {
        Konsist.scopeFromModule("feature")
            .classes(includeNested = true)
            .assert { it.resideInPackage("..feature..") }
    }

    fun `every public function in api package must have explicit return type`() {
        Konsist.scopeFromPackage("..api..")
            .functions(includeNested = true)
            .assert { it.hasReturnType() }
    }

    fun `every public property in api package must have specify type explicitly`() {
        Konsist.scopeFromPackage("..api..")
            .properties(includeNested = true)
            .assert { it.hasType() }
    }
}
