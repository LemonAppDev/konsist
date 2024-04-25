package com.lemonappdev.konsist.declaration.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@Deprecated("Will be removed in v0.16.0")
class KoClassDeclarationForKoHasTestClassProviderTest {
    @Test
    fun `class-with-test-with-default-parameters`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first()

        // then
        sut.hasTestClasses() shouldBeEqualTo true
    }

    @Test
    fun `class-without-test-with-default-parameters`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("buildSrc/".toOsSeparator())
            .classes()
            .first()

        // then
        sut.hasTestClasses() shouldBeEqualTo false
    }

    @Test
    fun `class-with-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibClass" }

        // then
        sut.hasTestClasses("Spec") shouldBeEqualTo true
    }

    @Test
    fun `class-without-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first()

        // then
        sut.hasTestClasses("Spec") shouldBeEqualTo false
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-with-test-with-declared-module-name-and-source-set`(
        moduleName: String?,
        sourceSetName: String?,
        value: Boolean,
    ) {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first()

        // then
        sut.hasTestClasses(moduleName = moduleName, sourceSetName = sourceSetName) shouldBeEqualTo value
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("app", null, true),
            arguments("data", null, false),
            arguments(null, "integrationTest", true),
            arguments(null, "test", false),
            arguments("app", "integrationTest", true),
            arguments("app", "test", false),
            arguments("data", "integrationTest", false),
        )
    }
}
