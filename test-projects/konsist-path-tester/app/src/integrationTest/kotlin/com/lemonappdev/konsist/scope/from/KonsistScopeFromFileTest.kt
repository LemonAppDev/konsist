package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.sep
import com.lemonappdev.konsist.helper.ext.toCanonicalPaths
import com.lemonappdev.konsist.helper.ext.toCanonicalPathss
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromFileTest {

    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toCanonicalPaths())
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt").toCanonicalPathss(),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toCanonicalPaths()) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${sep}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/sample".toCanonicalPaths()) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${sep}sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
