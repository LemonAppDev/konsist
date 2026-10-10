package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromFilesTest {

    @Test
    fun `scopeFromFiles(set)`() {
        // given
        val files = setOf(
            "/app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt",
            "/app/src/main/kotlin/com/lemonappdev/fixture/data/AppDataClass.kt"
        )
        val sut = Konsist.scopeFromFiles(files)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromFiles(list)`() {
        // given
        val files = listOf(
            "/app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt",
            "/app/src/main/kotlin/com/lemonappdev/fixture/data/AppDataClass.kt"
        )
        val sut = Konsist.scopeFromFiles(files)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromFiles(set) throws exception if path does not exist`() {
        // given
        val files = setOf(
            "app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt"
        )

        val func = { Konsist.scopeFromFiles(files) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFiles(list) throws exception if path does not exist`() {
        // given
        val files = listOf(
            "app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt"
        )

        val func = { Konsist.scopeFromFiles(files) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFiles(set) throws exception if path points to directory`() {
        // given
        val files = setOf(
            "app/src/main/kotlin/com/lemonappdev/fixture"
        )

        val func = { Konsist.scopeFromFiles(files) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/fixture"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFiles(list) throws exception if path points to directory`() {
        // given
        val files = listOf(
            "app/src/main/kotlin/com/lemonappdev/fixture"
        )

        val func = { Konsist.scopeFromFiles(files) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/fixture"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
