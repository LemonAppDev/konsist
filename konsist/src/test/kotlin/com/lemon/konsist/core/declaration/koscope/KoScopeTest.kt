package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.core.declaration.KoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeTest {

    private val projectPath = File("")
        .absoluteFile
        .path

    @Test
    fun `from file`() {
        // given
        val testSourceSetPath = "$projectPath/src/test/kotlin/com/"
        val sut = KoScope.fromFile("$testSourceSetPath/lemon/konsist/core/declaration/koscope/KoScopeTest.kt")

        // then
        sut.files().run {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "KoScopeTest.kt"
            first().path shouldBeEqualTo "${testSourceSetPath}lemon/konsist/core/declaration/koscope/KoScopeTest.kt"
        }
    }

    @Test
    fun `from project`() {
        // given
        val sut = KoScope.fromProject()

        // then
        sut.files().run {
            none { it.path.startsWith("//") shouldBeEqualTo false } shouldBeEqualTo true
            all { it.path.startsWith(projectPath) } shouldBeEqualTo true
        }
    }

    @Test
    fun `from package`() {
        // given
        val sut = KoScope.fromPackage("com.lemon.konsist.core.declaration.koscope")

        // then
        sut.files().map { it.name } shouldBeEqualTo listOf(
            "KoScopeForClassTest.kt",
            "KoScopeForCompanionObjectTest.kt",
            "KoScopeForDeclarationTest.kt",
            "KoScopeForFunctionTest.kt",
            "KoScopeForImportTest.kt",
            "KoScopeForInterfaceTest.kt",
            "KoScopeForObjectTest.kt",
            "KoScopeForPackageTest.kt",
            "KoScopeForPropertyTest.kt",
            "KoScopeTest.kt",
        )
    }

    @Test
    fun `from path`() {
        // given
        val testSourceSetPath = "$projectPath/src/test/kotlin/com/"
        val sut = KoScope.fromPath("${testSourceSetPath}lemon/konsist/core/declaration/koscope/")

        // then
        sut.files().map { it.name } shouldBeEqualTo listOf(
            "KoScopeForClassTest.kt",
            "KoScopeForCompanionObjectTest.kt",
            "KoScopeForDeclarationTest.kt",
            "KoScopeForFunctionTest.kt",
            "KoScopeForImportTest.kt",
            "KoScopeForInterfaceTest.kt",
            "KoScopeForObjectTest.kt",
            "KoScopeForPackageTest.kt",
            "KoScopeForPropertyTest.kt",
            "KoScopeTest.kt",
        )
    }
}
