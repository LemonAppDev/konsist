package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.sep
import com.lemonappdev.konsist.helper.ext.toNormalizedPath
import com.lemonappdev.konsist.helper.ext.toNormalizedPaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromFileTest {
    @Test
    fun `scopeFromFile with absolutePath true`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt".toNormalizedPath(), absolutePath = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt").toNormalizedPaths(),
        )
    }

    @Test
    fun `scopeFromFile with absolutePath false`() {
        // given
        val sut = Konsist
            .scopeFromFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toNormalizedPath(), absolutePath = false)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt").toNormalizedPaths(),
        )
    }

    @Test
    fun `scopeFromFile with absolutePath true throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/NonExistingTest.kt".toNormalizedPath(), absolutePath = true) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${sep}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with absolutePath false throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toNormalizedPath(), absolutePath = false) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${sep}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with absolutePath true throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/sample/".toNormalizedPath(), absolutePath = true) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${sep}sample${sep}"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with absolutePath false throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/sample".toNormalizedPath(), absolutePath = false) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${sep}sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
