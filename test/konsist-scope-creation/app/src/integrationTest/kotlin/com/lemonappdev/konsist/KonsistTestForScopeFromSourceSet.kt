package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromSourceSet {
    @Test
    fun `scopeFromSourceSet for main source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromSourceSet for integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromSourceSet for test source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromSourceSet for main and test source sets`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("main", "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }
}
