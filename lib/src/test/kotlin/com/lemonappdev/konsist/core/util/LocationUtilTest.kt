package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class LocationUtilTest {
    @Test
    fun `should match unix path with unix separator`() {
        // when
        val result =
            LocationUtil.resideInLocation(
                desiredLocation = "..feature/data..",
                currentLocation = "/feature/data/src/main/kotlin/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match windows path with unix separator`() {
        // when
        val result =
            LocationUtil.resideInLocation(
                desiredLocation = "..feature/data..",
                currentLocation = """\feature\data\src\main\kotlin\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match unix path with windows separator`() {
        // when
        val result =
            LocationUtil.resideInLocation(
                desiredLocation = """..feature\data..""",
                currentLocation = "/feature/data/src/main/kotlin/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match windows path with windows separator`() {
        // when
        val result =
            LocationUtil.resideInLocation(
                desiredLocation = """..feature\domain..""",
                currentLocation = """C:\project\feature\domain\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match windows path with different unix path`() {
        // when
        val result =
            LocationUtil.resideInLocation(
                desiredLocation = "..feature/data..",
                currentLocation = """\feature\domain\src\main\kotlin\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo false
    }
}
