package com.lemonappdev.konsist.core.declaration.koscope

import com.lemonappdev.konsist.core.declaration.KoScope
import org.amshove.kluent.assertSoftly
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
        val sut = KoScope.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscope/KoScopeTest.kt")

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "KoScopeTest.kt"
            first().filePath shouldBeEqualTo "${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeTest.kt"
        }
    }

    @Test
    fun `from project`() {
        // given
        val sut = KoScope.fromProjectFiles()

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            isNotEmpty() shouldBeEqualTo true
            none { it.filePath.startsWith("//") shouldBeEqualTo false } shouldBeEqualTo true
            all { it.filePath.startsWith(konsistModulePath) } shouldBeEqualTo false
        }
    }

    @Test
    fun `from package`() {
        // given
        val sut = KoScope.fromPackage("com.lemonappdev.konsist.core.declaration.koscope")

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
        val sut = KoScope.fromPackage("com.lemonappdev.konsist..declaration.koscope")

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
        val sut = KoScope.fromPath("${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/")

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
        val sut = KoScope.fromProjectFiles(module = "lib")

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            isNotEmpty() shouldBeEqualTo true
            none { it.filePath.startsWith("//") shouldBeEqualTo false } shouldBeEqualTo true
            all { it.filePath.startsWith(projectPath) } shouldBeEqualTo true
        }
    }

    @Test
    fun `toString method`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScope.fromPackage("com.lemonappdev.konsist.core.declaration.koscope")

        // then
        sut.toString() shouldBeEqualTo """
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForClassTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForCompanionObjectTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForDeclarationTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForFunctionTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForImportTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForInterfaceTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForObjectTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForPackageTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForPropertyTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeForTypeAliasTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeOperatorTest.kt
            ${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/KoScopeTest.kt
        """.trimIndent()
    }
}
