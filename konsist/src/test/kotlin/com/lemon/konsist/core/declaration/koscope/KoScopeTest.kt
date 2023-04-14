package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.core.declaration.KoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `from file`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeTest.kt")

        // then
        sut
            .files()
            .toList()
            .run {
                size shouldBeEqualTo 1
                first().name shouldBeEqualTo "KoScopeTest.kt"
                first().path shouldBeEqualTo "${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeTest.kt"
            }
    }

    @Test
    fun `from project`() {
        // given
        val sut = KoScope.fromProjectFiles()

        // then
        sut
            .files()
            .toList()
            .run {
                isNotEmpty() shouldBeEqualTo true
                none { it.path.startsWith("//") shouldBeEqualTo false } shouldBeEqualTo true
                all { it.path.startsWith(konsistModulePath) } shouldBeEqualTo false
            }
    }

    @Test
    fun `from package`() {
        // given
        val sut = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")

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
    fun `'from package' using two dots syntax`() {
        // given
        val sut = KoScope.fromPackage("com.lemon.konsist..declaration.koscope")

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
    fun `from path`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScope.fromPath("${testSourceSetPath}lemon/konsist/core/declaration/koscope/")

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
    fun `from module`() {
        // given
        val projectPath = konsistModulePath.dropLastWhile { it != '/' }
        val sut = KoScope.fromProjectFiles(module = "konsist")

        // then
        sut
            .files()
            .toList()
            .run {
                isNotEmpty() shouldBeEqualTo true
                none { it.path.startsWith("//") shouldBeEqualTo false } shouldBeEqualTo true
                all { it.path.startsWith(projectPath) } shouldBeEqualTo true
            }
    }

    @Test
    fun `toString method`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")

        // then
        sut.toString() shouldBeEqualTo """
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForClassTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForCompanionObjectTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForFunctionTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForImportTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForInterfaceTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForObjectTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForPackageTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForPropertyTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeForTypeAliasTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeOperatorTest.kt
            ${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeTest.kt
        """.trimIndent()
    }
}
