package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.core.declaration.KoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeOperatorsTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `plus operator`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeDeclarationTest.kt")

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
                    "KoScopeOperatorsTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val scope2 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `plusAssign operator`() {
        // given
        val scope1 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")
        val scope2 = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")

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
                    "KoScopeForClassTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForCompanionObjectTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeOperatorsTest.kt",
                    "KoScopeOperatorsTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }
}
