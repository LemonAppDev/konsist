package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ModuleUtilTest {
    @Test
    fun `should return nested module name with unix separator for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/data/src/main/kotlin/com/app/SampleClass.kt",
                rootProjectPath = "/Users/user/project",
            )

        // then
        result shouldBeEqualTo "feature/data"
    }

    @Test
    fun `should return nested module name with unix separator for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\feature\data\src\main\kotlin\com\app\SampleClass.kt""",
                rootProjectPath = """C:\Users\user\project""",
            )

        // then
        result shouldBeEqualTo "feature/data"
    }

    @Test
    fun `should return single module name for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\app\src\main\kotlin\com\app\SampleClass.kt""",
                rootProjectPath = """C:\Users\user\project""",
            )

        // then
        result shouldBeEqualTo "app"
    }

    @Test
    fun `should return root for root module file`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\src\main\kotlin\com\app\SampleClass.kt""",
                rootProjectPath = """C:\Users\user\project""",
            )

        // then
        result shouldBeEqualTo "root"
    }
}
