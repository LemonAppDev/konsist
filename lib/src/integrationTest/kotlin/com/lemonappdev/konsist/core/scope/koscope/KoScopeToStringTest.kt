package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeToStringTest {
    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `toString method`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        sut
            .toString()
            .shouldBeEqualTo(
                "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForAnnotationTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForClassTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForFileTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForFunctionTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForImportTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForInterfaceTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForNamedDeclarationTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForObjectTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForPackageTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForPropertyTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForTypeAliasTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeOperatorTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeTest.kt\n" +
                        "$konsistModulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeToStringTest.kt"
            )
    }
}
