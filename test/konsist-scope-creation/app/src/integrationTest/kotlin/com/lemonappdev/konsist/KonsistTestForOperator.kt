package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.Konsist.scopeFromPackage
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider
import com.lemonappdev.konsist.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTestForOperator {
    private val konsistModulePath = File("")
        .absoluteFile
        .path

    @Test
    fun `plus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(moduleName = "data")

        // when
        val sut = (scope1 + scope2)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
            ),
        )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile("$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt")

        // when
        val sut = scope1 - scope2

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForEqualsTest.kt",
                    "KoScopeForFileTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForHashCodeTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForOperatorTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForSliceTest.kt",
                    "KoScopeForToStringTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 =
            Konsist.scopeFromFile(
                "$testSourceSetPath/lemonappdev/konsist/core/declaration/kofiledeclaration/KoFileDeclarationForImportTest.kt",
            )

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
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile(
            "$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt",
        )

        // when
        scope1 += scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForEqualsTest.kt",
                    "KoScopeForFileTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForHashCodeTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForOperatorTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForSliceTest.kt",
                    "KoScopeForToStringTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val testSourceSetPath = "$konsistModulePath/src/integrationTest/kotlin/com/"
        val scope2 = Konsist.scopeFromFile(
            "$testSourceSetPath/lemonappdev/konsist/core/scope/koscope/KoScopeForDeclarationTest.kt",
        )

        // when
        scope1 -= scope2

        // then
        scope1
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForEqualsTest.kt",
                    "KoScopeForFileTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForHashCodeTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForOperatorTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForSliceTest.kt",
                    "KoScopeForToStringTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeTest.kt",
                ),
            )
    }
}
