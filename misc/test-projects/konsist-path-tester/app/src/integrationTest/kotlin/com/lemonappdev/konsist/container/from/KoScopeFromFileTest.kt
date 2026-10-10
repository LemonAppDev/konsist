package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromFileTest {

    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile(
                "/app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt".toOsSeparator(),
                "/app/src/main/kotlin/com/lemonappdev/fixture/data/AppDataClass.kt".toOsSeparator()
            )
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
    fun `scopeFromFile accepts unix separators`() {
        // given
        val sut = Konsist
            .scopeFromFile("app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt")
            .mapToFilePaths()

        // then
        sut shouldBeEqualTo listOf("$appMainSourceSetDirectory/fixture/AppClass.kt".toOsSeparator())
    }

    @Test
    fun `scopeFromFile accepts windows separators`() {
        // given
        val sut = Konsist
            .scopeFromFile("""app\src\main\kotlin\com\lemonappdev\fixture\AppClass.kt""")
            .mapToFilePaths()

        // then
        sut shouldBeEqualTo listOf("$appMainSourceSetDirectory/fixture/AppClass.kt".toOsSeparator())
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toOsSeparator()) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${fileSeparator}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/fixture".toOsSeparator()) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${fileSeparator}fixture"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
