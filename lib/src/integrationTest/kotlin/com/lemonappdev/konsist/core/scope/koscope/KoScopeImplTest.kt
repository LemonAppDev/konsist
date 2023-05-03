package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.api.Ko
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoTest {

    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `from file`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val sut =
            Ko.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeImplTest.kt")

        // then
        assertSoftly(
            sut
                .files()
                .toList(),
        ) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "KoScopeImplTest.kt"
            first()
                .filePath
                .shouldBeEqualTo(
                    "${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/KoScopeImplTest.kt",
                )
        }
    }

    @Test
    fun `from project`() {
        // given
        val sut = Ko.scopeFromProject()

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
        val sut = Ko.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

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
    fun `'from package' using two dots syntax`() {
        // given
        val sut = Ko.scopeFromPackage("com.lemonappdev.konsist..scope.koscope")

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
    fun `from path`() {
        // given
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val sut = Ko.scopeFromPath("${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/")

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
    fun `from module`() {
        // given
        val projectPath = konsistModulePath.dropLastWhile { it != '/' }
        val sut = Ko.scopeFromProject(module = "lib")

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
        val sut = Ko.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")

        // then
        val prefix = "${testSourceSetPath}lemonappdev/konsist/core/scope/koscope/"
        sut.toString() shouldBeEqualTo """
            ${prefix}KoScopeImplForClassTest.kt
            ${prefix}KoScopeImplForCompanionObjectTest.kt
            ${prefix}KoScopeImplForDeclarationTest.kt
            ${prefix}KoScopeImplForFunctionTest.kt
            ${prefix}KoScopeImplForImportTest.kt
            ${prefix}KoScopeImplForInterfaceTest.kt
            ${prefix}KoScopeImplForObjectTest.kt
            ${prefix}KoScopeImplForPackageTest.kt
            ${prefix}KoScopeImplForPropertyTest.kt
            ${prefix}KoScopeImplForTypeAliasTest.kt
            ${prefix}KoScopeImplOperatorTest.kt
            ${prefix}KoScopeImplTest.kt
        """.trimIndent()
    }
}
