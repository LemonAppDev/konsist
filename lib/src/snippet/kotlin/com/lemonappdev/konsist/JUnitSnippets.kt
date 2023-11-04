package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.declarations
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationOf
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

class JUnitSnippets {
    @Test
    fun `classes with 'Test' Annotation should have 'Test' suffix`() {
        Konsist
            .scopeFromSourceSet("test")
            .classes()
            .filter {
                it.functions().any { func -> func.hasAnnotationOf(Test::class) }
            }
            .assertTrue { it.hasNameEndingWith("Tests") }
    }

    @Test
    fun `test classes should have test subject named sut`() {
        Konsist
            .scopeFromTest()
            .classes()
            .assertTrue {
                val type = it.name.removeSuffix("Test")
                val sut =
                    it
                        .properties()
                        .firstOrNull { property -> property.name == "sut" }

                sut != null && (sut.type?.name == type || sut.text.contains("$type("))
            }
    }

    @Test
    fun `test classes should have all members private besides tests`() {
        Konsist
            .scopeFromTest()
            .classes()
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .withoutAnnotationOf(Test::class, ParameterizedTest::class, RepeatedTest::class)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .assertTrue { it.hasPrivateModifier }
    }

    @Test
    fun `no class should use JUnit4 Test annotation`() {
        Konsist
            .scopeFromProject()
            .classes()
            .functions()
            .assertFalse {
                it.annotations.any { annotation ->
                    annotation.fullyQualifiedName == "org.junit.Test"
                }
            }
    }
}
