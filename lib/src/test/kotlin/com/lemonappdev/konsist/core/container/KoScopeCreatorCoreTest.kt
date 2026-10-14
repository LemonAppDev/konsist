package com.lemonappdev.konsist.core.container

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeCreatorCoreTest {
    @Test
    fun `should match file from module when windows root path contains brackets`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "C:/Projects (1)/app/feature/src/main/kotlin/SampleClass.kt",
                projectRootPath = """C:\Projects (1)\app""",
                moduleName = "feature",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from source set when root path contains regex characters`() {
        // given
        val regex =
            KoScopeCreatorCore.getSourceSetPathRegex(
                projectRootPath = "/Users/user/c++ [new] project",
                sourceSetName = "main",
            )

        // when
        val result = "/Users/user/c++ [new] project/app/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module and source set when root path contains brackets`() {
        // given
        val filePath = "/Users/user/project (1)/src/test/kotlin/SampleClass.kt"

        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = filePath,
                projectRootPath = "/Users/user/project (1)",
                moduleName = "root",
            ) &&
                filePath.matches(
                    KoScopeCreatorCore.getSourceSetPathRegex(projectRootPath = "/Users/user/project (1)", sourceSetName = "test"),
                )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from nested module and source set with windows separators`() {
        // given
        val filePath = "C:/Users/user/project/feature/data/src/main/kotlin/SampleClass.kt"

        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = filePath,
                projectRootPath = """C:\Users\user\project""",
                moduleName = """feature\data""",
            ) &&
                filePath.matches(
                    KoScopeCreatorCore.getSourceSetPathRegex(projectRootPath = """C:\Users\user\project""", sourceSetName = "main"),
                )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not treat root path regex characters as wildcards`() {
        // given
        val regex =
            KoScopeCreatorCore.getSourceSetPathRegex(
                projectRootPath = "/Users/user/my.project",
                sourceSetName = "main",
            )

        // when
        val result = "/Users/user/myXproject/app/src/main/kotlin/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match file from other module`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project (1)/data/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project (1)",
                moduleName = "app",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file from module when module name has trailing slash`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature/auth/",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name has trailing windows separator`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "C:/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = """C:\Users\user\project""",
                moduleName = """feature\auth\""",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name has leading slash`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "/feature/auth",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module and source set when source set name has trailing slash`() {
        // given
        val filePath = "/Users/user/project/app/src/main/kotlin/SampleClass.kt"

        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = filePath,
                projectRootPath = "/Users/user/project",
                moduleName = "app",
            ) &&
                filePath.matches(
                    KoScopeCreatorCore.getSourceSetPathRegex(projectRootPath = "/Users/user/project", sourceSetName = "main/"),
                )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name is gradle project path`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = ":feature:auth",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module when module name is gradle project path without leading colon`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature:auth",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module when module name is gradle root project path`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = ":",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match file from nested module when module name is gradle root project path`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/auth/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = ":",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file from root module when root path is windows drive root`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "X:/src/main/kotlin/SampleClass.kt",
                projectRootPath = """X:\""",
                moduleName = "root",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from module and source set when root path is windows drive root`() {
        // given
        val filePath = "X:/feature/src/main/kotlin/SampleClass.kt"

        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = filePath,
                projectRootPath = """X:\""",
                moduleName = "feature",
            ) &&
                filePath.matches(
                    KoScopeCreatorCore.getSourceSetPathRegex(projectRootPath = """X:\""", sourceSetName = "main"),
                )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file from root module and source set when root path is unix root`() {
        // given
        val filePath = "/src/test/kotlin/SampleClass.kt"

        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = filePath,
                projectRootPath = "/",
                moduleName = "root",
            ) &&
                filePath.matches(
                    KoScopeCreatorCore.getSourceSetPathRegex(projectRootPath = "/", sourceSetName = "test"),
                )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match file from nested module when parent module has own source set`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/payment/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match file from parent module source set when module name is nested module`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature/payment",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file located outside src directory in module directory`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file located outside src directory in nested module directory`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/payment/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = ":feature:payment",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match file located in subdirectory of module directory outside src directory`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/feature/samples/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "feature",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match file located in project root directory when module name is root`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = "root",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match file located outside src directory with windows separators`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "C:/Users/user/project/feature/SampleClass.kt",
                projectRootPath = """C:\Users\user\project""",
                moduleName = ":feature",
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match any file when module name is blank`() {
        // when
        val result =
            KoScopeCreatorCore.residesInModule(
                filePath = "/Users/user/project/src/main/kotlin/SampleClass.kt",
                projectRootPath = "/Users/user/project",
                moduleName = " ",
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match file located outside src directory when source set name is given`() {
        // given
        val regex =
            KoScopeCreatorCore.getSourceSetPathRegex(
                projectRootPath = "/Users/user/project",
                sourceSetName = "main",
            )

        // when
        val result = "/Users/user/project/feature/SampleClass.kt".matches(regex)

        // then
        result shouldBeEqualTo false
    }
}
