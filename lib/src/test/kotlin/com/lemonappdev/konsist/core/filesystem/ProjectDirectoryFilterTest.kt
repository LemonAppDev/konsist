package com.lemonappdev.konsist.core.filesystem

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

class ProjectDirectoryFilterTest {
    private val projectRootDir = File("/project/build/target/.hidden/root")

    private val sut = ProjectDirectoryFilter(projectRootDir)

    @ParameterizedTest
    @ValueSource(
        strings = [
            "build",
            "build/generated/ksp/main/kotlin",
            "app/build",
            "app/build/generated/source/kapt",
            "target",
            "app/target/generated-sources",
            ".git",
            ".gradle/caches",
            "app/.gradle",
            ".idea",
            ".claude/worktrees/feature/app/src/main/kotlin",
            "node_modules",
            "web/node_modules/package/src",
            "app/src/main/kotlin/node_modules",
            "app/src/main/kotlin/.hidden",
        ],
    )
    fun `should ignore directory`(relativePath: String) {
        // when
        val actual = sut.isIgnored(File(projectRootDir, relativePath))

        // then
        actual shouldBeEqualTo true
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "app",
            "app/src/main/kotlin",
            "app/src/main/kotlin/com/sample/build",
            "app/src/main/kotlin/com/sample/target",
            "app/src/main/kotlin/build/target",
            "src/main/kotlin/build",
            "buildSrc",
            "app/builder",
            "app/targets",
        ],
    )
    fun `should not ignore directory`(relativePath: String) {
        // when
        val actual = sut.isIgnored(File(projectRootDir, relativePath))

        // then
        actual shouldBeEqualTo false
    }
}
