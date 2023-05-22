package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForHasTestTest {
    @Test
    fun `class-with-test-with-default-parameters`() {
        // given
        val sut = getSnippetFile("class-with-test-with-default-parameters")
            .classes()
            .first()

        // then
        sut.hasTest() shouldBeEqualTo true
    }

    @Test
    fun `class-without-test-with-default-parameters`() {
        // given
        val sut = getSnippetFile("class-without-test-with-default-parameters")
            .classes()
            .first()

        // then
        sut.hasTest() shouldBeEqualTo false
    }

    @Test
    fun `class-with-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = getSnippetFile("class-with-test-with-declared-test-file-name-suffix")
            .classes()
            .first()

        // then
        sut.hasTest("OtherSuffix") shouldBeEqualTo false
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-with-test-with-declared-module-name-and-source-set`(
        moduleName: String?,
        sourceSetName: String?,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-with-test-with-declared-module-name-and-source-set")
            .classes()
            .first()

        // then
        sut.hasTest(moduleName = moduleName, sourceSetName = sourceSetName) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forhastest/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("lib", null, true),
            arguments("app", null, false),
            arguments(null, "integrationTest", true),
            arguments(null, "test", false),
            arguments("lib", "integrationTest", true),
            arguments("app", "integrationTest", false),
            arguments("lib", "test", false),
        )
    }
}
