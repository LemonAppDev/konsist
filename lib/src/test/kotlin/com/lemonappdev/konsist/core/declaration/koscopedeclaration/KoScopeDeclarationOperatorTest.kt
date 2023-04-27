package com.lemonappdev.konsist.core.declaration.koscopedeclaration

import com.lemonappdev.konsist.core.declaration.KoScopeDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeDeclarationOperatorTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `plus operator`() {
        // given
        val scope1 = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 =
            KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationForDeclarationTest.kt")

        // when
        val sut = scope1 + scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeDeclarationForClassTest.kt",
                    "KoScopeDeclarationForCompanionObjectTest.kt",
                    "KoScopeDeclarationForDeclarationTest.kt",
                    "KoScopeDeclarationForDeclarationTest.kt",
                    "KoScopeDeclarationForFunctionTest.kt",
                    "KoScopeDeclarationForImportTest.kt",
                    "KoScopeDeclarationForInterfaceTest.kt",
                    "KoScopeDeclarationForObjectTest.kt",
                    "KoScopeDeclarationForPackageTest.kt",
                    "KoScopeDeclarationForPropertyTest.kt",
                    "KoScopeDeclarationForTypeAliasTest.kt",
                    "KoScopeDeclarationOperatorTest.kt",
                    "KoScopeDeclarationTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 =
            KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationForDeclarationTest.kt")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeDeclarationForClassTest.kt",
                    "KoScopeDeclarationForCompanionObjectTest.kt",
                    "KoScopeDeclarationForFunctionTest.kt",
                    "KoScopeDeclarationForImportTest.kt",
                    "KoScopeDeclarationForInterfaceTest.kt",
                    "KoScopeDeclarationForObjectTest.kt",
                    "KoScopeDeclarationForPackageTest.kt",
                    "KoScopeDeclarationForPropertyTest.kt",
                    "KoScopeDeclarationForTypeAliasTest.kt",
                    "KoScopeDeclarationOperatorTest.kt",
                    "KoScopeDeclarationTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 =
            KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/kofiledeclaration/KoFileDeclarationTest.kt")

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
        val scope1 = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 =
            KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationForDeclarationTest.kt")

        // when
        scope1 += scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeDeclarationForClassTest.kt",
                    "KoScopeDeclarationForCompanionObjectTest.kt",
                    "KoScopeDeclarationForDeclarationTest.kt",
                    "KoScopeDeclarationForDeclarationTest.kt",
                    "KoScopeDeclarationForFunctionTest.kt",
                    "KoScopeDeclarationForImportTest.kt",
                    "KoScopeDeclarationForInterfaceTest.kt",
                    "KoScopeDeclarationForObjectTest.kt",
                    "KoScopeDeclarationForPackageTest.kt",
                    "KoScopeDeclarationForPropertyTest.kt",
                    "KoScopeDeclarationForTypeAliasTest.kt",
                    "KoScopeDeclarationOperatorTest.kt",
                    "KoScopeDeclarationTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val scope2 =
            KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationForDeclarationTest.kt")

        // when
        scope1 -= scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeDeclarationForClassTest.kt",
                    "KoScopeDeclarationForCompanionObjectTest.kt",
                    "KoScopeDeclarationForFunctionTest.kt",
                    "KoScopeDeclarationForImportTest.kt",
                    "KoScopeDeclarationForInterfaceTest.kt",
                    "KoScopeDeclarationForObjectTest.kt",
                    "KoScopeDeclarationForPackageTest.kt",
                    "KoScopeDeclarationForPropertyTest.kt",
                    "KoScopeDeclarationForTypeAliasTest.kt",
                    "KoScopeDeclarationOperatorTest.kt",
                    "KoScopeDeclarationTest.kt",
                )
            )
    }
}
