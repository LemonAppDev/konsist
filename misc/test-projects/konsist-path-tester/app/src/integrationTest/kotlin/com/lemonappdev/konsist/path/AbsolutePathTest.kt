package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAbsolutePath
import com.lemonappdev.konsist.api.ext.list.withPath
import com.lemonappdev.konsist.api.ext.list.withoutAbsolutePath
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

// Absolute paths use the drive letter on Windows (e.g. "D:\a\konsist\..."), so these tests guard drive-letter paths
class AbsolutePathTest {
    private val dataMainFiles =
        listOf(
            "$dataMainSourceSetDirectory/fixture/LibClass.kt",
            "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
        ).toOsSeparator()

    private val files = Konsist.scopeFromProject().files

    @Test
    fun `withAbsolutePath with os separators`() {
        // given
        val path = "$dataMainSourceSetDirectory/.."

        // when
        val sut =
            files
                .withAbsolutePath(path.toOsSeparator())
                .map { it.path }

        // then
        sut shouldBeEqualTo dataMainFiles
    }

    @Test
    fun `withAbsolutePath with unix separators`() {
        // given
        val path = "$dataMainSourceSetDirectory/..".replace("\\", "/")

        // when
        val sut =
            files
                .withAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo dataMainFiles
    }

    @Test
    fun `withAbsolutePath with windows separators`() {
        // given
        val path = "$dataMainSourceSetDirectory/..".replace("/", "\\")

        // when
        val sut =
            files
                .withAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo dataMainFiles
    }

    @Test
    fun `withAbsolutePath with lowercase drive letter`() {
        // given
        // Windows: "D:\a\..." -> "d:\a\...", Unix: first char is '/', so path is unchanged
        val path = "$dataMainSourceSetDirectory/..".replaceFirstChar { it.lowercase() }

        // when
        val sut =
            files
                .withAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo dataMainFiles
    }

    @Test
    fun `withAbsolutePath with wildcard inside path`() {
        // given
        val path = "$projectRootDirectory/data/../fixture/data/.."

        // when
        val sut =
            files
                .withAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo
            listOf(
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator()
    }

    @Test
    fun `withAbsolutePath does not match project path`() {
        // given
        val path = "data/src/main/.."

        // when
        val sut =
            files
                .withAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withPath with absolutePath true`() {
        // given
        val path = "$dataMainSourceSetDirectory/.."

        // when
        val sut =
            files
                .withPath(path, absolutePath = true)
                .map { it.path }

        // then
        sut shouldBeEqualTo dataMainFiles
    }

    @Test
    fun `withoutAbsolutePath`() {
        // given
        val path = "$dataMainSourceSetDirectory/.."

        // when
        val sut =
            files
                .withoutAbsolutePath(path)
                .map { it.path }

        // then
        sut shouldBeEqualTo files.map { it.path } - dataMainFiles.toSet()
    }

    @Test
    fun `resideInPath with absolutePath true`() {
        // given
        val sut = files.first { it.name == "LibClass" }

        // then
        sut.resideInPath("$dataMainSourceSetDirectory/..", absolutePath = true) shouldBeEqualTo true
        sut.resideInPath("$projectRootDirectory/app/..", absolutePath = true) shouldBeEqualTo false
    }
}
