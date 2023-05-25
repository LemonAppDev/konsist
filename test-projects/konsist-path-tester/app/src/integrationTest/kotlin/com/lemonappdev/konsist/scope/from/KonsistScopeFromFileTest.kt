package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromFileTest {
    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot false`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt", resolvePathFromProjectRoot = false)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot true`() {
        // given
        val sut = Konsist
            .scopeFromFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt", resolvePathFromProjectRoot = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot false throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/NonExistingTest.kt", resolvePathFromProjectRoot = false) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot true throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt", resolvePathFromProjectRoot = true) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot false throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/sample/", resolvePathFromProjectRoot = false) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/sample/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile with resolvePathFromProjectRoot true throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/sample", resolvePathFromProjectRoot = true) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
