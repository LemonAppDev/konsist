package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeOperatorTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `plus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt")

        // when
        val sut = scope1 + scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeToStringTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeToStringTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 =
            Konsist.scopeFromFile(
                "$testSourceSetPath/lemonappdev/konsist/core/declaration/kofiledeclaration/KoFileDeclarationForImportTest.kt",
            )

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .toList()
            .shouldBeEqualTo(scope1.files().toList())
    }

    @Test
    fun `plusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile(
            "$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt",
        )

        // when
        scope1 += scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeToStringTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile(
            "$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt",
        )

        // when
        scope1 -= scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeToStringTest.kt",
                ),
            )
    }
}
