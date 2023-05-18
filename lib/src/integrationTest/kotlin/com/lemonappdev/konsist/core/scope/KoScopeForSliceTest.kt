package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeForSliceTest {
    private val beginningOfPath = File("")
        .absoluteFile
        .path + "/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope"

    @Test
    fun `slice-method1`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        val actual = sut.slice { it.name.startsWith("KoScopeForF") }

        actual
            .toString()
            .shouldBeEqualTo(
                "$beginningOfPath/koscope/KoScopeForFileTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForFunctionTest.kt"
            )
    }

    @Test
    fun `slice-method2`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        val actual = sut.slice { it.hasImports("java.io.File") }

        actual
            .toString()
            .shouldBeEqualTo(
                "$beginningOfPath/koscope/KoScopeForOperatorTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForSliceTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForToStringTest.kt"
            )
    }
}
