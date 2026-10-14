package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PathUtilTest {
    @Test
    fun `getProjectPath returns path starting with separator for project in directory`() {
        // when
        val result =
            PathUtil.getProjectPath(
                path = PathUtil.toOsSeparator("/Users/user/project/app/src/main/kotlin/SampleClass.kt"),
                rootProjectPath = "/Users/user/project",
            )

        // then
        result shouldBeEqualTo PathUtil.toOsSeparator("/app/src/main/kotlin/SampleClass.kt")
    }

    @Test
    fun `getProjectPath returns path starting with separator for project in windows drive root`() {
        // when
        val result =
            PathUtil.getProjectPath(
                path = """X:\src\main\kotlin\SampleClass.kt""",
                rootProjectPath = """X:\""",
            )

        // then
        result shouldBeEqualTo """\src\main\kotlin\SampleClass.kt"""
    }

    @Test
    fun `getProjectPath returns path starting with separator for project in unix root`() {
        // when
        val result =
            PathUtil.getProjectPath(
                path = "/src/main/kotlin/SampleClass.kt",
                rootProjectPath = "/",
            )

        // then
        result shouldBeEqualTo "/src/main/kotlin/SampleClass.kt"
    }

    @Test
    fun `getProjectPath returns root module name for file in windows drive root project`() {
        // given
        val projectPath =
            PathUtil.getProjectPath(
                path = """X:\src\main\kotlin\SampleClass.kt""",
                rootProjectPath = """X:\""",
            )

        // when
        val result = ModuleUtil.getModuleName(projectPath)

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `getSourceSetName returns source set name for file in module`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/app/src/test/kotlin/SampleClass.kt"))

        // then
        result shouldBeEqualTo "test"
    }

    @Test
    fun `getSourceSetName returns source set name for file in root module`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/src/main/kotlin/SampleClass.kt"))

        // then
        result shouldBeEqualTo "main"
    }

    @Test
    fun `getSourceSetName returns first source set name for file in nested src directory`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/app/src/main/kotlin/src/test/SampleClass.kt"))

        // then
        result shouldBeEqualTo "main"
    }

    @Test
    fun `getSourceSetName returns source set name for file in module located in src directory`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/src/app/src/test/kotlin/SampleClass.kt"))

        // then
        result shouldBeEqualTo "test"
    }

    @Test
    fun `getSourceSetName returns source set name for file in java directory of module located in src directory`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/src/app/src/main/java/SampleClass.kt"))

        // then
        result shouldBeEqualTo "main"
    }

    @Test
    fun `getSourceSetName returns empty string for file outside src directory`() {
        // when
        val result = PathUtil.getSourceSetName(PathUtil.toOsSeparator("/app/SampleClass.kt"))

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `isBuildConfigPath returns true for file in buildSrc source set`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/buildSrc/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns true for file in buildSrc source set for windows path`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = """\buildSrc\src\main\kotlin\SampleClass.kt""")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns true for file directly in buildSrc directory`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/buildSrc/SampleClass.kt")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns true for lowercase buildsrc directory`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/buildsrc/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns true for buildSrc of nested build`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/build-logic/buildSrc/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns false for module file`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/app/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `isBuildConfigPath returns false for directory name starting with buildSrc`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/buildSrcTools/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `isBuildConfigPath returns false for buildsrc package`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/app/src/main/kotlin/com/buildsrc/SampleClass.kt")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `isBuildConfigPath returns false for file named buildSrc`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/app/buildSrc.kt")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `isBuildConfigPath returns true for buildSrc located in src directory`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/src/buildSrc/src/main/kotlin/SampleClass.kt")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigPath returns false for buildsrc package of module located in src directory`() {
        // when
        val result = PathUtil.isBuildConfigPath(projectPath = "/src/app/src/main/kotlin/com/buildsrc/SampleClass.kt")

        // then
        result shouldBeEqualTo false
    }
}
