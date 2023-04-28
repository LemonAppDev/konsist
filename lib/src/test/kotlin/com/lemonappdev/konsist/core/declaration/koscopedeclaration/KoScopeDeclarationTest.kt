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
        val sut = KoScopeDeclaration.fromFile("$testSourceSetPath/lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationTest.kt")

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "KoScopeDeclarationTest.kt"
            first().filePath shouldBeEqualTo "${testSourceSetPath}lemonappdev/konsist/core/declaration/koscopedeclaration/KoScopeDeclarationTest.kt"
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
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")

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
    fun `'from package' using two dots syntax`() {
        // given
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist..declaration.koscopedeclaration")

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
    fun `from path`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/test/kotlin/com/"
        val sut = KoScopeDeclaration.fromPathCodebase("${testSourceSetPath}lemonappdev/konsist/core/declaration/koscopedeclaration/")

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
        val sut = KoScopeDeclaration.fromPackage("com.lemonappdev.konsist.core.declaration.koscopedeclaration")

        // then
        val prefix = "${testSourceSetPath}lemonappdev/konsist/core/declaration/koscopedeclaration/"
        sut.toString() shouldBeEqualTo """
            ${prefix}KoScopeDeclarationForClassTest.kt
            ${prefix}KoScopeDeclarationForCompanionObjectTest.kt
            ${prefix}KoScopeDeclarationForDeclarationTest.kt
            ${prefix}KoScopeDeclarationForFunctionTest.kt
            ${prefix}KoScopeDeclarationForImportTest.kt
            ${prefix}KoScopeDeclarationForInterfaceTest.kt
            ${prefix}KoScopeDeclarationForObjectTest.kt
            ${prefix}KoScopeDeclarationForPackageTest.kt
            ${prefix}KoScopeDeclarationForPropertyTest.kt
            ${prefix}KoScopeDeclarationForTypeAliasTest.kt
            ${prefix}KoScopeDeclarationOperatorTest.kt
            ${prefix}KoScopeDeclarationTest.kt
        """.trimIndent()
    }
}
