package com.lemonappdev.konsist.declaration.kofunction

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionDeclarationForKoBaseTestClassProviderTest {
    @Test
    fun `function-has-not-test`() {
        // given
        val sut =
            Konsist
                .scopeFromProduction("app")
                .functions()
                .first { it.name == "appFunction" }

        // then
        assertSoftly(sut) {
            testClasses { it.name == "AppFunctionTest" } shouldBeEqualTo emptyList()
            countTestClasses { it.name == "AppFunctionTest" } shouldBeEqualTo 0
            hasTestClass { it.name == "AppFunctionTest" } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-test`() {
        // given
        val sut =
            Konsist
                .scopeFromProduction("app")
                .functions()
                .first { it.name == "appFunction" }

        // then
        assertSoftly(sut) {
            testClasses { it.name == "AppClassTest" }.map { it.name } shouldBeEqualTo listOf("AppClassTest")
            countTestClasses { it.name == "AppClassTest" } shouldBeEqualTo 1
            hasTestClass { it.name == "AppClassTest" } shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `function-with-test-with-declared-module-name-and-source-set`(
        moduleName: String?,
        sourceSetName: String?,
        value: Boolean,
    ) {
        // given
        val sut =
            Konsist
                .scopeFromProduction("app")
                .functions()
                .first { it.name == "appFunction" }

        // then
        assertSoftly(sut) {
            hasTestClass(moduleName = moduleName, sourceSetName = sourceSetName) { it.name == "AppClassTest" } shouldBeEqualTo value
        }
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
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
