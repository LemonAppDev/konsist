package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.core.scope.KoScope
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
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val sut =
            KoScope
                .fromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeTest.kt")

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "KoScopeTest.kt"
            first()
                .filePath
                .shouldBeEqualTo(
                    "${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/KoScopeTest.kt",
                )
        }
    }

    @Test
    fun `from project`() {
        // given
        val sut = KoScope.fromProject()

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
        val sut = KoScope.fromPackage("com.lemonappdev.konsist.core.scope.koscope")

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
        val sut = KoScope.fromPackage("com.lemonappdev.konsist..scope.koscope")

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
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val sut = KoScope
            .fromPath("${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/")

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
        val sut = KoScope.fromProject(module = "lib")

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
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val sut = KoScope.fromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        val prefix = "${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/"
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
