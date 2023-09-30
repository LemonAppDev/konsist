package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class JUnitSnippets {
    fun `classes with 'Test' Annotation should have 'Test' suffix`() {
        Konsist
            .scopeFromSourceSet("test")
            .classes()
            .filter {
                it.functions().any { func -> func.hasAnnotationOf(Test::class) }
            }
            .assertTrue { it.hasNameEndingWith("Tests") }
    }

    fun `no class should use JUnit4 Test annotation`() {
        Konsist
            .scopeFromProject()
            .classes()
            .functions()
            .assertFalse { it.hasAnnotationWithName("org.junit.Test") }
    }
}
