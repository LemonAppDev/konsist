package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.toCanonicalPath
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
            .scopeFromFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toCanonicalPath())
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt").toCanonicalPath(),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toCanonicalPath()) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${fileSeparator}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
