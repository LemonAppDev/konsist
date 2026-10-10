package com.lemonappdev.konsist.core.container

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeCreatorCoreTest {
    @Test
    fun `should match file from module when windows root path contains brackets`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = """C:\Projects (1)\app""",
                moduleName = "feature",
                sourceSetName = null,
            )

        // when
        val result = "C:/Projects (1)/app/feature/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from source set when root path contains regex characters`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/c++ [new] project",
                moduleName = null,
                sourceSetName = "main",
            )

        // when
        val result = "/Users/user/c++ [new] project/app/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module when root path contains brackets`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project (1)",
                moduleName = "root",
                sourceSetName = "test",
            )

        // when
        val result = "/Users/user/project (1)/src/test/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from nested module with windows separators`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = """C:\Users\user\project""",
                moduleName = """feature\data""",
                sourceSetName = "main",
            )

        // when
        val result = "C:/Users/user/project/feature/data/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not treat root path regex characters as wildcards`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/my.project",
                moduleName = null,
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/myXproject/app/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match file from other module`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project (1)",
                moduleName = "app",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project (1)/data/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file from module when module name has trailing slash`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = "feature/auth/",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name has trailing windows separator`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = """C:\Users\user\project""",
                moduleName = """feature\auth\""",
                sourceSetName = null,
            )

        // when
        val result = "C:/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name has leading slash`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = "/feature/auth",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from source set when source set name has trailing slash`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = "app",
                sourceSetName = "main/",
            )

        // when
        val result = "/Users/user/project/app/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name is gradle project path`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = ":feature:auth",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name is gradle project path without leading colon`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = "feature:auth",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module when module name is gradle root project path`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = ":",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match file from nested module when module name is gradle root project path`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/Users/user/project",
                moduleName = ":",
                sourceSetName = null,
            )

        // when
        val result = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file from root module when root path is windows drive root`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = """X:\""",
                moduleName = "root",
                sourceSetName = null,
            )

        // when
        val result = "X:/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module and source set when root path is windows drive root`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = """X:\""",
                moduleName = "feature",
                sourceSetName = "main",
            )

        // when
        val result = "X:/feature/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module when root path is unix root`() {
        // given
        val regex =
            KoScopeCreatorCore.getPathRegex(
                projectRootPath = "/",
                moduleName = "root",
                sourceSetName = "test",
            )

        // when
        val result = "/src/test/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }
}
