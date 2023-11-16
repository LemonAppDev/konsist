package com.lemonappdev.konsist.declaration.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForKoTestClassProviderTest {
    @Test
    fun `class-has-not-test`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("buildSrc/".toOsSeparator())
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            testClasses() shouldBeEqualTo emptyList()
            testClasses { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo emptyList()
            numTestClasses() shouldBeEqualTo 0
            countTestClasses { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo 0
            hasTestClasses() shouldBeEqualTo false
            hasTestClass { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-test-with-sut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppClass" }

        // then
        assertSoftly(sut) {
            testClasses().map { it.name } shouldBeEqualTo listOf("AppClassTest")
            testClasses("cut") shouldBeEqualTo emptyList()
            testClasses { it.name == "AppClassTest" && it.hasPropertyWithName("sut") }.map { it.name } shouldBeEqualTo listOf("AppClassTest")
            testClasses { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo emptyList()
            numTestClasses() shouldBeEqualTo 1
            numTestClasses("cut") shouldBeEqualTo 0
            countTestClasses { it.name == "AppClassTest" && it.hasPropertyWithName("sut") } shouldBeEqualTo 1
            countTestClasses { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo 0
            hasTestClasses() shouldBeEqualTo true
            hasTestClasses("cut") shouldBeEqualTo false
            hasTestClass { it.name == "AppClassTest" && it.hasPropertyWithName("sut") } shouldBeEqualTo true
            hasTestClass { it.name == "AppClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-test-with-sut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppDataClass" }

        // then
        assertSoftly(sut) {
            testClasses().map { it.name } shouldBeEqualTo listOf("AppDataClassTest")
            testClasses("cut") shouldBeEqualTo emptyList()
            testClasses { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .map { it.name }
                .shouldBeEqualTo(listOf("AppDataClassTest"))
            testClasses { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(emptyList())
            numTestClasses() shouldBeEqualTo 1
            numTestClasses("cut") shouldBeEqualTo 0
            countTestClasses { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(1)
            countTestClasses { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(0)
            hasTestClasses() shouldBeEqualTo true
            hasTestClasses("cut") shouldBeEqualTo false
            hasTestClass { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(true)
            hasTestClass { it.name == "AppDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(false)
        }
    }

    @Test
    fun `class-has-test-with-cut-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibClass" }

        // then
        assertSoftly(sut) {
            testClasses() shouldBeEqualTo emptyList()
            testClasses("cut").map { it.name } shouldBeEqualTo listOf("LibClassTest")
            testClasses { it.name == "LibClassTest" && it.hasPropertyWithName("sut") }shouldBeEqualTo emptyList()
            testClasses { it.name == "LibClassTest" && it.hasPropertyWithName("cut") }.map { it.name }  shouldBeEqualTo listOf("LibClassTest")
            numTestClasses() shouldBeEqualTo 0
            numTestClasses("cut") shouldBeEqualTo 1
            countTestClasses { it.name == "LibClassTest" && it.hasPropertyWithName("sut") } shouldBeEqualTo 0
            countTestClasses { it.name == "LibClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo 1
            hasTestClasses() shouldBeEqualTo false
            hasTestClasses("cut") shouldBeEqualTo true
            hasTestClass { it.name == "LibClassTest" && it.hasPropertyWithName("sut") } shouldBeEqualTo false
            hasTestClass { it.name == "LibClassTest" && it.hasPropertyWithName("cut") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-test-with-cut-variable`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first { it.name == "LibDataClass" }

        // then
        assertSoftly(sut) {
            testClasses() shouldBeEqualTo emptyList()
            testClasses("cut").map { it.name } shouldBeEqualTo listOf("LibDataClassTest")
            testClasses { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(emptyList())
            testClasses { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .map { it.name }
                .shouldBeEqualTo(listOf("LibDataClassTest"))
            numTestClasses() shouldBeEqualTo 0
            numTestClasses("cut") shouldBeEqualTo 1
            countTestClasses { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(0)
            countTestClasses { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(1)
            hasTestClasses() shouldBeEqualTo false
            hasTestClasses("cut") shouldBeEqualTo true
            hasTestClass { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(false)
            hasTestClass { it.name == "LibDataClassTest" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(true)
        }
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
        assertSoftly(sut) {
            hasTestClasses(moduleName = moduleName, sourceSetName = sourceSetName) shouldBeEqualTo value
        }
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
