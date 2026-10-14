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
}
