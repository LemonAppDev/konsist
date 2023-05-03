package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoOperatorTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `plus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeImplForDeclarationTest.kt")

        // when
        val sut = scope1 + scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeImplForClassTest.kt",
                    "KoScopeImplForCompanionObjectTest.kt",
                    "KoScopeImplForDeclarationTest.kt",
                    "KoScopeImplForDeclarationTest.kt",
                    "KoScopeImplForFunctionTest.kt",
                    "KoScopeImplForImportTest.kt",
                    "KoScopeImplForInterfaceTest.kt",
                    "KoScopeImplForObjectTest.kt",
                    "KoScopeImplForPackageTest.kt",
                    "KoScopeImplForPropertyTest.kt",
                    "KoScopeImplForTypeAliasTest.kt",
                    "KoScopeImplOperatorTest.kt",
                    "KoScopeImplTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeImplForDeclarationTest.kt")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeImplForClassTest.kt",
                    "KoScopeImplForCompanionObjectTest.kt",
                    "KoScopeImplForFunctionTest.kt",
                    "KoScopeImplForImportTest.kt",
                    "KoScopeImplForInterfaceTest.kt",
                    "KoScopeImplForObjectTest.kt",
                    "KoScopeImplForPackageTest.kt",
                    "KoScopeImplForPropertyTest.kt",
                    "KoScopeImplForTypeAliasTest.kt",
                    "KoScopeImplOperatorTest.kt",
                    "KoScopeImplTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/kofiledeclaration/KoFileDeclarationTest.kt")

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
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeImplForDeclarationTest.kt")

        // when
        scope1 += scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeImplForClassTest.kt",
                    "KoScopeImplForCompanionObjectTest.kt",
                    "KoScopeImplForDeclarationTest.kt",
                    "KoScopeImplForDeclarationTest.kt",
                    "KoScopeImplForFunctionTest.kt",
                    "KoScopeImplForImportTest.kt",
                    "KoScopeImplForInterfaceTest.kt",
                    "KoScopeImplForObjectTest.kt",
                    "KoScopeImplForPackageTest.kt",
                    "KoScopeImplForPropertyTest.kt",
                    "KoScopeImplForTypeAliasTest.kt",
                    "KoScopeImplOperatorTest.kt",
                    "KoScopeImplTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeImplForDeclarationTest.kt")

        // when
        scope1 -= scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeImplForClassTest.kt",
                    "KoScopeImplForCompanionObjectTest.kt",
                    "KoScopeImplForFunctionTest.kt",
                    "KoScopeImplForImportTest.kt",
                    "KoScopeImplForInterfaceTest.kt",
                    "KoScopeImplForObjectTest.kt",
                    "KoScopeImplForPackageTest.kt",
                    "KoScopeImplForPropertyTest.kt",
                    "KoScopeImplForTypeAliasTest.kt",
                    "KoScopeImplOperatorTest.kt",
                    "KoScopeImplTest.kt",
                ),
            )
    }
}
