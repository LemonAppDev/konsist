package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromProductionTest {
    @Test
    fun `scopeFromProduction`() {
        // given
        val sut = Konsist
            .scopeFromProduction()
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, main source set`() {
        // given
        val sut = Konsist
            .scopeFromProduction(sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, integrationTest source set`() {
        // given
        val func = { Konsist.scopeFromProduction(sourceSetName = "integrationTest") }

        // then
        val message = "Source set 'integrationTest' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProduction, test source set`() {
        // given
        val func = { Konsist.scopeFromProduction(sourceSetName = "test") }

        // then
        val message = "Source set 'test' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProduction, app module`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, app module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "app", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, root module`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, root module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "root", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, app module, integrationTest source set`() {
        // given
        val func = { Konsist.scopeFromProduction(moduleName = "app", sourceSetName = "integrationTest") }

        // then
        val message = "Source set 'integrationTest' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProduction, app module, test source set`() {
        // given
        val func = { Konsist.scopeFromProduction(moduleName = "app", sourceSetName = "test") }

        // then
        val message = "Source set 'test' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProduction, data module`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, data module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromProduction(moduleName = "data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProduction, data module, integrationTest source set`() {
        // given
        val func = { Konsist.scopeFromProduction(moduleName = "data", sourceSetName = "integrationTest") }

        // then
        val message = "Source set 'integrationTest' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProduction, data module, test source set`() {
        // given
        val func = { Konsist.scopeFromProduction(moduleName = "data", sourceSetName = "test") }

        // then
        val message = "Source set 'test' is a test source set, but it should be production source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
