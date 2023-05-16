package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeToStringTest {
    @Test
    fun `toString method`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        sut
            .toString()
            .shouldBeEqualTo(
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForAnnotationTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForClassTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForFunctionTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForImportTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForInterfaceTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForNamedDeclarationTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForObjectTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForPackageTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForPropertyTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeForTypeAliasTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeOperatorTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeTest.kt\n" +
                    "/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/scope/koscope/KoScopeToStringTest.kt"
            )
    }
}
