package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeCreatorTest {
    @Test
    fun `scopeFromProject`() {
        Konsist
            .scopeFromProject()
            .files()
            .toList()
            .map { it.filePath }
            .shouldBeEqualTo(
                listOf(
                    "$applicationKotlinMainSourceSetDirectory/com/lemonappdev/sample/AppClass.kt",
                    "$applicationKotlinMainSourceSetDirectory/com/lemonappdev/sample/data/AppDataClass.kt",
                    "$applicationKotlinTestSourceSetDirectory/com/lemonappdev/konsist/KoScopeCreatorTest.kt",
                    "$applicationKotlinTestSourceSetDirectory/com/lemonappdev/sample/AppClassTest.kt",
                    "$applicationKotlinTestSourceSetDirectory/com/lemonappdev/sample/data/AppDataClassTest.kt",
                    "$libraryKotlinMainSourceSetDirectory/com/lemonappdev/sample/LibClass.kt",
                    "$libraryKotlinMainSourceSetDirectory/com/lemonappdev/sample/data/LibDataClass.kt",
                    "$libraryKotlinTestSourceSetDirectory/com/lemonappdev/sample/LibClassTest.kt",
                    "$libraryKotlinTestSourceSetDirectory/com/lemonappdev/sample/data/LibDataClassTest.kt",
                ),
            )
    }

    companion object {
        private val projectRootDirectory = File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

        private val applicationKotlinMainSourceSetDirectory = "$projectRootDirectory/application/src/main/kotlin"

        private val applicationKotlinTestSourceSetDirectory = "$projectRootDirectory/application/src/test/kotlin"

        private val libraryKotlinMainSourceSetDirectory = "$projectRootDirectory/library/src/main/kotlin"

        private val libraryKotlinTestSourceSetDirectory = "$projectRootDirectory/library/src/test/kotlin"
    }
}
