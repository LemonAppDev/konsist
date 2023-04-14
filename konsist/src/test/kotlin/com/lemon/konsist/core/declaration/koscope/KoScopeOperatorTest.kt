package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.core.declaration.KoScope
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
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt")

        // when
        val sut = scope1 + scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForClassTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForClassTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/kofile/KoFileTest.kt")

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
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt")

        // when
        scope1 += scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForClassTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt")

        // when
        scope1 -= scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForClassTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }
}
