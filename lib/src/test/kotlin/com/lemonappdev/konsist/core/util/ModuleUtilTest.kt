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
            )

        // then
        result shouldBeEqualTo "feature/data"
    }

    @Test
    fun `should return three level nested module name for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/payment/api/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "feature/payment/api"
    }

    @Test
    fun `should return three level nested module name for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\feature\payment\api\src\main\kotlin\com\app\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "feature/payment/api"
    }

    @Test
    fun `should return single module name for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\app\src\main\kotlin\com\app\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "app"
    }

    @Test
    fun `should return root for root module file for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\src\main\kotlin\com\app\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should return single module name for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/app/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "app"
    }

    @Test
    fun `should return root for root module file for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should return module name when it matches root directory name for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/project/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "project"
    }

    @Test
    fun `should return module name when it matches root directory name for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\project\src\main\kotlin\com\app\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "project"
    }

    @Test
    fun `should return file directory for file outside src directory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/samples/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "samples"
    }

    @Test
    fun `should return file directory for file outside src directory for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\samples\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "samples"
    }

    @Test
    fun `should return nested file directory for file outside src directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/samples/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "feature/samples"
    }

    @Test
    fun `should return root for file in project root directory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should return root for file in project root directory for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should normalize gradle project path`() {
        // when
        val result = ModuleUtil.normalizeModuleName(":feature:auth")

        // then
        result shouldBeEqualTo "feature/auth"
    }

    @Test
    fun `should normalize gradle project path without leading colon`() {
        // when
        val result = ModuleUtil.normalizeModuleName("feature:auth")

        // then
        result shouldBeEqualTo "feature/auth"
    }

    @Test
    fun `should normalize three level gradle project path`() {
        // when
        val result = ModuleUtil.normalizeModuleName(":feature:payment:api")

        // then
        result shouldBeEqualTo "feature/payment/api"
    }

    @Test
    fun `should normalize gradle root project path to root`() {
        // when
        val result = ModuleUtil.normalizeModuleName(":")

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should normalize module name with unix separators`() {
        // when
        val result = ModuleUtil.normalizeModuleName("/feature/auth/")

        // then
        result shouldBeEqualTo "feature/auth"
    }

    @Test
    fun `should normalize module name with windows separators`() {
        // when
        val result = ModuleUtil.normalizeModuleName("""\feature\auth\""")

        // then
        result shouldBeEqualTo "feature/auth"
    }

    @Test
    fun `should keep root module name`() {
        // when
        val result = ModuleUtil.normalizeModuleName("root")

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should convert blank module name to empty string`() {
        // when
        val result = ModuleUtil.normalizeModuleName(" ")

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `should convert module name containing only separators to empty string`() {
        // when
        val result = ModuleUtil.normalizeModuleName("/")

        // then
        result shouldBeEqualTo ""
    }
}
