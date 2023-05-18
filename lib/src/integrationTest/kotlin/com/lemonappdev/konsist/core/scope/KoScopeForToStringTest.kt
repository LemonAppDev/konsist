package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeForToStringTest {
    private val beginningOfPath = File("")
        .absoluteFile
        .path + "/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope"

    @Test
    fun `toString method`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        sut
            .toString()
            .shouldBeEqualTo(
                "$beginningOfPath/koscope/KoScopeForAnnotationTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForClassTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForDeclarationTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForEqualsTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForFileTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForFunctionTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForHashCodeTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForImportTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForInterfaceTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForNamedDeclarationTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForObjectTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForOperatorTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForPackageTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForPropertyTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForSliceTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForToStringTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeForTypeAliasTest.kt\n" +
                        "$beginningOfPath/koscope/KoScopeTest.kt"
            )
    }
}
