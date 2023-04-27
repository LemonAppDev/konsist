package com.lemonappdev.konsist.core.declaration.koscopedeclaration

import com.lemonappdev.konsist.core.declaration.KoScopeDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeDeclarationTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `from file`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscope/KoScopeTest.kt")

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
        val sut = KoScopeDeclaration.fromProject()

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
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscope")

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
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist..declaration.koscope")

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
        val sut = KoScopeDeclaration.fromPathCodebase("${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/")

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
        val sut = KoScopeDeclaration.fromProject(module = "lib")

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
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscope")

        // then
        val prefix = "${testSourceSetPath}lemonappdev/konsist/core/declaration/koscope/"
        sut.toString() shouldBeEqualTo """
            ${prefix}KoScopeForClassTest.kt
            ${prefix}KoScopeForCompanionObjectTest.kt
            ${prefix}KoScopeForDeclarationTest.kt
            ${prefix}KoScopeForFunctionTest.kt
            ${prefix}KoScopeForImportTest.kt
            ${prefix}KoScopeForInterfaceTest.kt
            ${prefix}KoScopeForObjectTest.kt
            ${prefix}KoScopeForPackageTest.kt
            ${prefix}KoScopeForPropertyTest.kt
            ${prefix}KoScopeForTypeAliasTest.kt
            ${prefix}KoScopeOperatorTest.kt
            ${prefix}KoScopeTest.kt
        """.trimIndent()
    }
}
