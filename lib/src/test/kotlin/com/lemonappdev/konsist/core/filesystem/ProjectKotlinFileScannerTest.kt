package com.lemonappdev.konsist.core.filesystem

import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ProjectKotlinFileScannerTest {
    @Test
    fun `should return kotlin files from nested directories`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "Root.kt",
            "app/src/main/kotlin/com/sample/AppClass.kt",
            "app/src/main/kotlin/com/sample/feature/FeatureClass.kt",
            "app/src/test/kotlin/com/sample/AppClassTest.kt",
            "lib/src/main/kotlin/LibClass.kt",
        )
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual.relativePaths(projectRootDir) shouldBeEqualTo
            setOf(
                "Root.kt",
                "app/src/main/kotlin/com/sample/AppClass.kt",
                "app/src/main/kotlin/com/sample/feature/FeatureClass.kt",
                "app/src/test/kotlin/com/sample/AppClassTest.kt",
                "lib/src/main/kotlin/LibClass.kt",
            )
    }

    @Test
    fun `should not return non kotlin files`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "build.gradle.kts",
            "README.md",
            "app/src/main/java/JavaClass.java",
            "app/src/main/kotlin/AppClass.kt",
        )
        File(projectRootDir, "app/src/main/kotlin/directory.kt").mkdirs()
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual.relativePaths(projectRootDir) shouldBeEqualTo setOf("app/src/main/kotlin/AppClass.kt")
    }

    @Test
    fun `should return kotlin files from directory with kotlin file extension`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(projectRootDir, "app/src/main/kotlin/directory.kt/NestedClass.kt")
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual.relativePaths(projectRootDir) shouldBeEqualTo setOf("app/src/main/kotlin/directory.kt/NestedClass.kt")
    }

    @Test
    fun `should not return kotlin files from ignored directories`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "build/generated/Generated.kt",
            "app/build/generated/ksp/main/kotlin/Generated.kt",
            "app/target/Generated.kt",
            ".gradle/Cached.kt",
            ".claude/worktrees/feature/app/src/main/kotlin/AppClass.kt",
            "web/node_modules/package/src/Package.kt",
            "app/src/main/kotlin/com/sample/build/BuildClass.kt",
            "app/src/main/kotlin/com/sample/target/TargetClass.kt",
        )
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual.relativePaths(projectRootDir) shouldBeEqualTo
            setOf(
                "app/src/main/kotlin/com/sample/build/BuildClass.kt",
                "app/src/main/kotlin/com/sample/target/TargetClass.kt",
            )
    }

    @Test
    fun `should return files in the same order as sequential walk`(
        @TempDir projectRootDir: File,
    ) {
        // given
        (1..5).forEach { module ->
            (1..5).forEach { pkg ->
                createFiles(projectRootDir, "module$module/src/main/kotlin/package$pkg/Class$pkg.kt", "Module$module.kt")
            }
        }
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual shouldBeEqualTo projectRootDir.walk().filter { it.isFile }.toList()
    }

    @Test
    fun `should return empty list when directory is empty`(
        @TempDir projectRootDir: File,
    ) {
        // given
        val sut = ProjectKotlinFileScanner(projectRootDir)

        // when
        val actual = runBlocking { sut.scan() }

        // then
        actual shouldBeEqualTo emptyList()
    }

    private fun createFiles(
        rootDir: File,
        vararg relativePaths: String,
    ) {
        relativePaths.forEach { relativePath ->
            File(rootDir, relativePath).apply {
                parentFile.mkdirs()
                createNewFile()
            }
        }
    }

    private fun List<File>.relativePaths(rootDir: File) = map { it.relativeTo(rootDir).invariantSeparatorsPath }.toSet()
}
