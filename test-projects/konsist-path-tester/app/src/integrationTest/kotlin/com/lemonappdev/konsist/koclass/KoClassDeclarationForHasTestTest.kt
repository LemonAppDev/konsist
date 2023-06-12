package com.lemonappdev.konsist.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForHasTestTest {
    @Test
    fun `class-with-test-with-default-parameters`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/", absolutePath = true)
            .classes()
            .first()

        // then
        sut.hasTest() shouldBeEqualTo true
    }

    @Test
    fun `class-without-test-with-default-parameters`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$projectRootDirectory/buildSrc/", absolutePath = true)
            .classes()
            .first()

        // then
        sut.hasTest() shouldBeEqualTo false
    }

    @Test
    fun `class-with-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$dataTestSourceSetDirectory/sample/", absolutePath = true)
            .classes()
            .first()

        // then
        sut.hasTest("Spec") shouldBeEqualTo true
    }

    @Test
    fun `class-without-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/", absolutePath = true)
            .classes()
            .first()

        // then
        sut.hasTest("Spec") shouldBeEqualTo false
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
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/", absolutePath = true)
            .classes()
            .first()

        // then
        sut.hasTest(moduleName = moduleName, sourceSetName = sourceSetName) shouldBeEqualTo value
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
