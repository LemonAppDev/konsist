package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.declarations
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifier
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutSomeModifiers
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationOf
import com.lemonappdev.konsist.api.ext.list.withoutSomeAnnotationsOf
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

class TestSnippets {
    fun `every class has test`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .assert { it.hasTestClass() }
    }

    fun `every class - except data and value class - has test`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withoutModifier(KoModifier.DATA, KoModifier.VALUE)
            .assert { it.hasTestClass() }
    }

    fun `test classes should have test subject named sut`() {
        Konsist
            .scopeFromTest()
            .classes()
            .assert {
                val type = it.name.removeSuffix("Test")
                val sut = it
                    .properties()
                    .firstOrNull { property -> property.name == "sut" }

                sut != null && (sut.type?.name == type || sut.text.contains("$type("))
            }
    }

    fun `test classes should have all members private besides tests`() {
        Konsist
            .scopeFromTest()
            .classes()
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .withoutAnnotationOf(Test::class, ParameterizedTest::class, RepeatedTest::class)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .assert { it.hasPrivateModifier }
    }

    fun `don't use JUnit4 Test annotation`() {
        Konsist
            .scopeFromProject()
            .classes()
            .functions()
            .assertNot { it.hasAnnotationWithName("org.junit.Test") } // should be only org.junit.jupiter.api.Test
    }
}
