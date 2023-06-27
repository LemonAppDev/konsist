package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withName
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutNameEndingWith
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.params.ParameterizedTest

class LibrarySnippets {
    fun `forbid string in files`() {
        Konsist.scopeFromProject()
            .files()
//            .assertNot { it.text.contains("Forbidden string") }
        TODO("remove comment after adding assert for files")
    }

    fun `every api declarartion has KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarations(includeNested = true)
            .assert { it.hasKDoc() }
    }

    fun `every api declaration has complete KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarations(includeNested = true)
            .assert { it.hasValidKDoc() }
    }

    fun `test classes should have all members private besides tests`() {
        Konsist.scopeFromTest()
            .classes(includeNested = true)
            // we must exclude test classes
            .withoutNameEndingWith("Test")
            .withoutAnnotationsOf(
                Test::class,
                ParameterizedTest::class,
                TestFactory::class,
                RepeatedTest::class,
                TestTemplate::class,
            )
            .assert { it.hasPrivateModifier() }
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

    fun `test classes should reside in the same package as tested class`() {
        Konsist.scopeFromProduction()
            .classes()
            .filter { it.hasTest() }
            .assert {
                Konsist.scopeFromTest()
                    .classes()
                    .withName("${it.name}Test")
                    .first()
                    .packagee == it.packagee
            }
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
}
