package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.sequence.withoutModifiers
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot

class TestingSnippets {
    fun `every class has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .assert { it.hasTest() }
    }

    fun `every class - except data and value class - has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withoutModifiers(KoModifier.DATA, KoModifier.VALUE)
            .assert { it.hasTest() }
    }

    fun `test classes should have test subject named sut`() {
        Konsist.scopeFromTest()
            .classes()
            .assert {
                val type = it.name.removeSuffix("Test")
                val sut = it
                    .properties()
                    .firstOrNull { property -> property.name == "sut" }

                sut != null && (sut.explicitType?.name == type || sut.text.contains("$type("))
            }
    }

    fun `test classes should have all members private besides tests`() {
        Konsist.scopeFromTest()
            .classes()
            .flatMap { it.declarations() }
            .filterIsInstance<KoDeclaration>()
            .filterNot {
                it.annotations.any { annotation ->
                    annotation
                        .name
                        .lowercase()
                        .contains("test")
                }
            }
            .assert { it.hasPrivateModifier() }
    }

    fun `junit 4 'Test' annotation is not allowed for functions`() {
        Konsist.scopeFromTest()
            .functions(includeNested = true)
            .assertNot { it.hasAnnotations("org.junit.Test") }
    }
}
