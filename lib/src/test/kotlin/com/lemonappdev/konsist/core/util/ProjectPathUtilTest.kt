package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class ProjectPathUtilTest {
    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "app",
            "app/src/main/kotlin/com/lemonappdev",
            "app/src/main/kotlin/io/something/target",
            "app/src/main/kotlin/io/something/build",
            "app/src/test/kotlin/com/lemonappdev/build/target",
            "src/main/kotlin/com/lemonappdev/target",
            "src/main/kotlin/com/lemonappdev/build",
            "feature/data/src/main/kotlin/com/lemonappdev/target",
            "buildSrc/src/main/kotlin",
            "build-logic/convention/src/main/kotlin",
            "targets/src/main/kotlin",
        ],
    )
    fun `isIgnoredDirectory returns false for project directory`(relativePath: String) {
        // given
        val directoryPath = "$PROJECT_ROOT_PATH/$relativePath"

        // when
        val actual = ProjectPathUtil.isIgnoredDirectory(PROJECT_ROOT_PATH, directoryPath)

        // then
        actual shouldBeEqualTo false
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            // Gradle build output
            "build",
            "build/generated/source/kapt/main",
            "app/build",
            "app/build/generated/ksp/main/kotlin",
            "app/build/tmp/kapt3/stubs/main",
            "feature/data/build/generated/source/buildConfig/main",
            "buildSrc/build/generated-sources/kotlin-dsl-accessors/kotlin/gradle/kotlin/dsl/accessors",
            "build-logic/convention/build/generated-sources/kotlin-dsl-plugins/kotlin",
            "app/build/src/main/kotlin",
            // Maven build output
            "target",
            "target/generated-sources/annotations",
            "app/target/generated-sources/kapt/compile",
            // Hidden directories
            ".git",
            ".gradle",
            ".gradle/8.14/kotlin-dsl/accessors",
            ".idea",
            ".kotlin/sessions",
            "app/.gradle",
            "app/.idea",
            "app/src/main/kotlin/.hidden",
            ".worktrees/feature",
            ".worktrees/feature/app/src/main/kotlin/com/lemonappdev",
            // node_modules directories
            "node_modules",
            "node_modules/.pnpm/package/node_modules/package/src/main/kotlin",
            "web/node_modules/package",
        ],
    )
    fun `isIgnoredDirectory returns true for ignored directory`(relativePath: String) {
        // given
        val directoryPath = "$PROJECT_ROOT_PATH/$relativePath"

        // when
        val actual = ProjectPathUtil.isIgnoredDirectory(PROJECT_ROOT_PATH, directoryPath)

        // then
        actual shouldBeEqualTo true
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "/home/user/.hidden/project",
            "/home/user/.worktrees/project/.worktrees/feature",
            "/home/user/node_modules/project",
            "/home/user/build/project",
            "/home/user/target/project",
            "/home/user/src/build/project",
            "/tmp/project (1)",
            "/tmp/project+c",
            "/tmp/project[x]",
            "/tmp/project.*",
        ],
    )
    fun `isIgnoredDirectory checks only path relative to project root`(projectRootPath: String) {
        // when
        val actual =
            listOf(
                projectRootPath,
                "$projectRootPath/app",
                "$projectRootPath/app/src/main/kotlin/com/lemonappdev/target",
                "$projectRootPath/app/build",
                "$projectRootPath/app/target/generated-sources",
                "$projectRootPath/.gradle",
                "$projectRootPath/node_modules",
            ).map { ProjectPathUtil.isIgnoredDirectory(projectRootPath, it) }

        // then
        actual shouldBeEqualTo listOf(false, false, false, true, true, true, true)
    }

    @Test
    fun `isIgnoredDirectory handles windows separators`() {
        // given
        val projectRootPath = "C:\\Users\\user\\.hidden\\project (1)"

        // when
        val actual =
            listOf(
                projectRootPath,
                "$projectRootPath\\app\\src\\main\\kotlin\\com\\lemonappdev\\build",
                "$projectRootPath\\app\\build\\generated",
                "$projectRootPath\\.gradle",
            ).map { ProjectPathUtil.isIgnoredDirectory(projectRootPath, it) }

        // then
        actual shouldBeEqualTo listOf(false, false, true, true)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForResideInModuleAndSourceSet")
    fun `resideInModuleAndSourceSet`(
        projectPath: String,
        moduleName: String?,
        sourceSetName: String?,
        expected: Boolean,
    ) {
        // when
        val actual = ProjectPathUtil.resideInModuleAndSourceSet(projectPath, moduleName, sourceSetName)

        // then
        actual shouldBeEqualTo expected
    }

    companion object {
        private const val PROJECT_ROOT_PATH = "/home/user/project"

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForResideInModuleAndSourceSet() =
            listOf(
                // module and source set not specified
                arguments("/app/src/main/kotlin/AppClass.kt", null, null, true),
                arguments("/buildSrc/RootBuildSrcClass.kt", null, null, true),
                // module
                arguments("/app/src/main/kotlin/AppClass.kt", "app", null, true),
                arguments("/app/src/main/kotlin/AppClass.kt", "data", null, false),
                arguments("/app/src/main/kotlin/AppClass.kt", "root", null, false),
                arguments("/app/src/main/kotlin/AppClass.kt", "ap", null, false),
                arguments("/src/main/kotlin/RootClass.kt", "root", null, true),
                arguments("/src/main/kotlin/RootClass.kt", "app", null, false),
                arguments("/feature/data/src/main/kotlin/DataClass.kt", "feature/data", null, true),
                arguments("/feature/data/src/main/kotlin/DataClass.kt", "feature\\data", null, true),
                arguments("\\feature\\data\\src\\main\\kotlin\\DataClass.kt", "feature/data", null, true),
                arguments("/feature/data/src/main/kotlin/DataClass.kt", "data", null, false),
                // module name containing regex special characters is matched literally
                arguments("/my.module/src/main/kotlin/AppClass.kt", "my.module", null, true),
                arguments("/myXmodule/src/main/kotlin/AppClass.kt", "my.module", null, false),
                arguments("/lib+core/src/main/kotlin/AppClass.kt", "lib+core", null, true),
                arguments("/lib (1)/src/main/kotlin/AppClass.kt", "lib (1)", null, true),
                arguments("/lib[x]/src/main/kotlin/AppClass.kt", "lib[x]", null, true),
                arguments("/app/src/main/kotlin/AppClass.kt", ".*", null, false),
                // source set
                arguments("/app/src/main/kotlin/AppClass.kt", null, "main", true),
                arguments("/app/src/main/kotlin/AppClass.kt", null, "test", false),
                arguments("/src/test/kotlin/RootClassTest.kt", null, "test", true),
                arguments("/app/src/main/kotlin/AppClass.kt", null, ".*", false),
                // module and source set
                arguments("/app/src/main/kotlin/AppClass.kt", "app", "main", true),
                arguments("/app/src/main/kotlin/AppClass.kt", "app", "test", false),
                arguments("/app/src/main/kotlin/AppClass.kt", "data", "main", false),
                arguments("/src/main/kotlin/RootClass.kt", "root", "main", true),
                arguments("/src/main/kotlin/RootClass.kt", "root", "test", false),
                arguments("\\app\\src\\main\\kotlin\\AppClass.kt", "app", "main", true),
            )
    }
}
