package com.lemonappdev.konsist.core.filesystem

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ModuleDirectoryDetectorTest {
    @Test
    fun `should return directory containing gradle build file`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "build.gradle.kts",
            "feature/build.gradle.kts",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature")

        // then
        result shouldBeEqualTo "/feature"
    }

    @Test
    fun `should return closest module directory for directory without build file`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
            "feature/build.gradle.kts",
            "feature/config/Config.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature/config")

        // then
        result shouldBeEqualTo "/feature"
    }

    @Test
    fun `should return nested module directory instead of parent module directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
            "feature/build.gradle.kts",
            "feature/payment/build.gradle.kts",
            "feature/payment/Script.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature/payment")

        // then
        result shouldBeEqualTo "/feature/payment"
    }

    @Test
    fun `should return project root directory for directory without module directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
            "samples/Sample.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/samples")

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `should return project root directory for project root directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "")

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `should return directory containing maven build file`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "pom.xml",
            "feature/pom.xml",
            "feature/config/Config.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature/config")

        // then
        result shouldBeEqualTo "/feature"
    }

    @Test
    fun `should return directory containing groovy gradle build file`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle",
            "feature/build.gradle",
            "feature/config/Config.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature/config")

        // then
        result shouldBeEqualTo "/feature"
    }

    @Test
    fun `should return directory containing src directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
            "feature/src/main/kotlin/Feature.kt",
            "feature/config/Config.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/feature/config")

        // then
        result shouldBeEqualTo "/feature"
    }

    @Test
    fun `should return null for project without module directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "samples/Sample.kt",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/samples")

        // then
        result shouldBeEqualTo null
    }

    @Test
    fun `should return null for directory that does not exist`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
        )

        // when
        val result = ModuleDirectoryDetector.getModuleDirectory(projectRootDir.path, "/missing")

        // then
        result shouldBeEqualTo null
    }

    @Test
    fun `should return true for directory containing kotlin directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/src/test/kotlin/AppTest.kt",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/app/src/test")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should return true for directory containing java directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/src/test/java/AppTest.java",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/app/src/test")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should return true for directory containing resources directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/src/test/resources/config.json",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/app/src/test")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should return false for directory without kotlin, java and resources directories`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "src/app/build.gradle.kts",
            "src/app/src/main/kotlin/App.kt",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/src/app")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should return false for directory containing file named kotlin`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/src/test/kotlin",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/app/src/test")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should return false for source set directory that does not exist`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "settings.gradle.kts",
        )

        // when
        val result = ModuleDirectoryDetector.isSourceSetDirectory(projectRootDir.path, "/app/src/test")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should return true for module directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "src/app/build.gradle.kts",
        )

        // when
        val result = ModuleDirectoryDetector.isModuleDirectory(File(projectRootDir, "src/app"))

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should return true for directory containing src directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/src/main/kotlin/App.kt",
        )

        // when
        val result = ModuleDirectoryDetector.isModuleDirectory(File(projectRootDir, "app"))

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should return false for package directory`(
        @TempDir projectRootDir: File,
    ) {
        // given
        createFiles(
            projectRootDir,
            "app/build.gradle.kts",
            "app/src/main/kotlin/com/sample/build/Build.kt",
        )

        // when
        val result = ModuleDirectoryDetector.isModuleDirectory(File(projectRootDir, "app/src/main/kotlin/com/sample"))

        // then
        result shouldBeEqualTo false
    }

    private fun createFiles(
        projectRootDir: File,
        vararg paths: String,
    ) {
        paths.forEach { path ->
            File(projectRootDir, path).apply {
                parentFile.mkdirs()
                createNewFile()
            }
        }
    }
}
