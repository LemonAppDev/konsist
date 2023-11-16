package com.lemonappdev.konsist.declaration.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForKoHasTestClassProviderTest {
    @Test
    fun `class-has-not-test`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("buildSrc/".toOsSeparator())
            .classes()
            .first()

        // then
        sut.hasTestClass() shouldBeEqualTo false
    }

    @Test
    fun `class-has-test-with-sut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppClass"}

        // then
        sut.hasTestClass() shouldBeEqualTo true
    }

    @Test
    fun `class-has-not-test-with-cut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppClass"}

        // then
        sut.hasTestClass("cut") shouldBeEqualTo false
    }

    @Test
    fun `class-has-test-with-sut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppDataClass"}

        // then
        sut.hasTestClass() shouldBeEqualTo true
    }

    @Test
    fun `class-has-not-test-with-cut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppDataClass"}

        // then
        sut.hasTestClass("cut") shouldBeEqualTo false
    }

    @Test
    fun `class-has-test-with-cut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibClass" }

        // then
        sut.hasTestClass("cut") shouldBeEqualTo true
    }

    @Test
    fun `class-has-not-test-with-sut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibClass" }

        // then
        sut.hasTestClass() shouldBeEqualTo false
    }

    @Test
    fun `class-has-test-with-cut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibDataClass" }

        // then
        sut.hasTestClass("cut") shouldBeEqualTo true
    }

    @Test
    fun `class-has-not-test-with-sut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibDataClass" }

        // then
        sut.hasTestClass() shouldBeEqualTo false
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
        sut.hasTestClass(moduleName = moduleName, sourceSetName = sourceSetName) shouldBeEqualTo value
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
