package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutNameEndingWith
import com.lemonappdev.konsist.core.verify.assert
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
}
