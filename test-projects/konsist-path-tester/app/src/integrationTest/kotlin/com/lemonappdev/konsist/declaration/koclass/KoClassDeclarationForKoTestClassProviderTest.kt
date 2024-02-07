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
    fun `class-has-test-with-sut-test-property`() {
        // given
        val sut = Konsist
            .scopeFromProduction("app")
            .classes()
            .first { it.name == "AppClass" }

        // then
        assertSoftly(sut) {
            testClasses().map { it.name } shouldBeEqualTo listOf(
                "AppTestClass1",
                "AppTestClass2",
                "AppTestClass3",
                "AppTestClass4",
                "AppTestClass5",
                "AppTestClass6"
            )
            testClasses("cut") shouldBeEqualTo emptyList()
            testClasses { it.name == "AppTestClass1" && it.hasPropertyWithName("sut") }.map { it.name }
                .shouldBeEqualTo(listOf("AppTestClass1"))
            testClasses { it.name == "AppTestClass1" && it.hasPropertyWithName("cut") } shouldBeEqualTo emptyList()
            testClasses { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .map { it.name }
                .shouldBeEqualTo(listOf("AppTestClass5"))
            testClasses { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(emptyList())
            numTestClasses() shouldBeEqualTo 6
            numTestClasses("cut") shouldBeEqualTo 0
            countTestClasses { it.name == "AppTestClass1" && it.hasPropertyWithName("sut") } shouldBeEqualTo 1
            countTestClasses { it.name == "AppTestClass1" && it.hasPropertyWithName("cut") } shouldBeEqualTo 0
            countTestClasses { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(1)
            countTestClasses { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(0)
            hasTestClasses() shouldBeEqualTo true
            hasTestClasses("cut") shouldBeEqualTo false
            hasTestClass { it.name == "AppTestClass1" && it.hasPropertyWithName("sut") } shouldBeEqualTo true
            hasTestClass { it.name == "AppTestClass1" && it.hasPropertyWithName("cut") } shouldBeEqualTo false
            hasTestClass { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(true)
            hasTestClass { it.name == "AppTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
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
            testClasses("cut").map { it.name } shouldBeEqualTo listOf(
                "LibTestClass1",
                "LibTestClass2",
                "LibTestClass3",
                "LibTestClass4",
                "LibTestClass5",
                "LibTestClass6"
            )
            testClasses() shouldBeEqualTo emptyList()
            testClasses { it.name == "LibTestClass1" && it.hasPropertyWithName("cut") }.map { it.name }
                .shouldBeEqualTo(listOf("LibTestClass1"))
            testClasses { it.name == "LibTestClass1" && it.hasPropertyWithName("sut") } shouldBeEqualTo emptyList()
            testClasses { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .map { it.name }
                .shouldBeEqualTo(listOf("LibTestClass5"))
            testClasses { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(emptyList())
            numTestClasses("cut") shouldBeEqualTo 6
            numTestClasses("sut") shouldBeEqualTo 0
            countTestClasses { it.name == "LibTestClass1" && it.hasPropertyWithName("cut") } shouldBeEqualTo 1
            countTestClasses { it.name == "LibTestClass1" && it.hasPropertyWithName("sut") } shouldBeEqualTo 0
            countTestClasses { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(1)
            countTestClasses { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(0)
            hasTestClasses("cut") shouldBeEqualTo true
            hasTestClasses("sut") shouldBeEqualTo false
            hasTestClass { it.name == "LibTestClass1" && it.hasPropertyWithName("cut") } shouldBeEqualTo true
            hasTestClass { it.name == "LibTestClass1" && it.hasPropertyWithName("sut") } shouldBeEqualTo false
            hasTestClass { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("cut") } }
                .shouldBeEqualTo(true)
            hasTestClass { it.name == "LibTestClass5" && it.hasFunction { func -> func.hasVariableWithName("sut") } }
                .shouldBeEqualTo(false)
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
