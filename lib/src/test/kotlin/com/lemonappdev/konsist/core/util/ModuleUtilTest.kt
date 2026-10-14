package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
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
    fun `should return file directory for file outside src directory without module directory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/samples/SampleClass.kt",
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "samples"
    }

    @Test
    fun `should return file directory for file outside src directory without module directory for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\samples\SampleClass.kt""",
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "samples"
    }

    @Test
    fun `should return nested file directory for file outside src directory without module directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/samples/SampleClass.kt",
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "feature/samples"
    }

    @Test
    fun `should return closest module for file outside src directory in module subdirectory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/config/SampleClass.kt",
                getModuleDirectory = mapOf("/feature/config" to "/feature")::get,
            )

        // then
        result shouldBeEqualTo "feature"
    }

    @Test
    fun `should return closest module for file outside src directory in module subdirectory for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\feature\config\SampleClass.kt""",
                getModuleDirectory = mapOf("/feature/config" to "/feature")::get,
            )

        // then
        result shouldBeEqualTo "feature"
    }

    @Test
    fun `should return nested module for file outside src directory in nested module directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/payment/SampleClass.kt",
                getModuleDirectory = mapOf("/feature/payment" to "/feature/payment")::get,
            )

        // then
        result shouldBeEqualTo "feature/payment"
    }

    @Test
    fun `should return root for file outside src directory in root module subdirectory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/samples/SampleClass.kt",
                getModuleDirectory = mapOf("/samples" to "")::get,
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should not look for module directory for file in src directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/payment/src/main/kotlin/SampleClass.kt",
                getModuleDirectory = { error("Module directory should not be looked up") },
            )

        // then
        result shouldBeEqualTo "feature/payment"
    }

    @Test
    fun `should return root for file in project root directory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/SampleClass.kt",
                getModuleDirectory = { null },
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
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should return module name for module located in src directory for unix path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/app/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "src/app"
    }

    @Test
    fun `should return module name for module located in src directory for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\src\app\src\main\kotlin\com\app\SampleClass.kt""",
            )

        // then
        result shouldBeEqualTo "src/app"
    }

    @Test
    fun `should return module name for module named src`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/src/main/kotlin/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "src"
    }

    @Test
    fun `should return nested module name for module located in src directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/feature/payment/src/test/java/com/app/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "src/feature/payment"
    }

    @Test
    fun `should return module name for file in package named src`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/app/src/main/kotlin/com/src/main/kotlin/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "app"
    }

    @Test
    fun `should return root for root module file in source set without kotlin directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/app/SampleClass.kt",
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "root"
    }

    @Test
    fun `should return module located in src directory for file outside its src directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/tools/SampleClass.kt",
                getModuleDirectory = mapOf("/src/tools" to "/src/tools")::get,
            )

        // then
        result shouldBeEqualTo "src/tools"
    }

    @Test
    fun `should return module located in src directory for file in its src directory outside source root`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/src/tools/src/test/testData/SampleClass.kt",
                getModuleDirectory = mapOf("/src/tools/src/test/testData" to "/src/tools")::get,
            )

        // then
        result shouldBeEqualTo "src/tools"
    }

    @Test
    fun `should return closest module for file in src directory outside source root for windows path`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = """\src\tools\src\test\testData\SampleClass.kt""",
                getModuleDirectory = mapOf("/src/tools/src/test/testData" to "/src/tools")::get,
            )

        // then
        result shouldBeEqualTo "src/tools"
    }

    @Test
    fun `should return directory containing first src directory for file outside source root without module directory`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/src/test/testData/SampleClass.kt",
                getModuleDirectory = { null },
            )

        // then
        result shouldBeEqualTo "feature"
    }

    @Test
    fun `should return module containing resources for file in resources`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/feature/src/test/resources/SampleClass.kt",
                getModuleDirectory = { error("Module directory should not be looked up") },
            )

        // then
        result shouldBeEqualTo "feature"
    }

    @Test
    fun `should return module containing resources for file in project located in resources`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/app/src/functionalTest/resources/project/SampleClass.kt",
                getModuleDirectory = mapOf("/app/src/functionalTest/resources/project" to "/app/src/functionalTest/resources/project")::get,
            )

        // then
        result shouldBeEqualTo "app"
    }

    @Test
    fun `should return module containing resources for file in source set of project located in resources`() {
        // when
        val result =
            ModuleUtil.getModuleName(
                projectPath = "/app/src/functionalTest/resources/project/src/main/kotlin/SampleClass.kt",
            )

        // then
        result shouldBeEqualTo "app"
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

    @Test
    fun `should match parent module for gradle project path with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature", name = ":feature..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match nested module for gradle project path with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment", name = ":feature..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match three level nested module for module name with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment/api", name = "feature/..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match nested module for module name with windows separator and trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment", name = """feature\..""")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match module with the same name prefix for module name with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "featureflags", name = ":feature..")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match other module for module name with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "app", name = ":feature..")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match parent module for nested module name with trailing wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature", name = ":feature:payment..")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match module for module name with leading wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "data", name = "..data")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match nested module for gradle project path with leading wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/data", name = ":..:data")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match module with the same name suffix for module name with leading wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/metadata", name = "..data")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match nested module for module name with wildcard in the middle`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment/data", name = "feature..data")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match direct child module for module name with wildcard in the middle`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/data", name = "feature..data")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match module outside parent for module name with wildcard in the middle`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "data", name = "feature..data")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match root module for wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "root", name = "..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match nested module for gradle root project path with wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment", name = ":..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match module name containing regex characters literally for module name with wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature (1)/data", name = "feature (1)..")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not treat regex characters as wildcards for module name with wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "featureXx/data", name = "feature.x..")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should match module name case-sensitively for module name with wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature", name = "Feature..")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match nested module for module name without wildcard`() {
        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment", name = "feature")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should throw for module name with trailing three dots`() {
        // given
        val func = { ModuleUtil.residesInModule(moduleName = "feature/data", name = ":feature...") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Module name ':feature...' contains more than two consecutive dots. Use '..' as a wildcard " +
            "for any number of module directories, e.g. ':feature..' or ':feature..:data'."
    }

    @Test
    fun `should throw for module name with leading three dots`() {
        // given
        val func = { ModuleUtil.residesInModule(moduleName = "feature/data", name = "...data") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Module name '...data' contains more than two consecutive dots. Use '..' as a wildcard " +
            "for any number of module directories, e.g. ':feature..' or ':feature..:data'."
    }

    @Test
    fun `should throw for module name with three dots in the middle`() {
        // given
        val func = { ModuleUtil.residesInModule(moduleName = "feature/data", name = ":feature...:data") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Module name ':feature...:data' contains more than two consecutive dots. Use '..' as a wildcard " +
            "for any number of module directories, e.g. ':feature..' or ':feature..:data'."
    }

    @Test
    fun `should throw for module name with four dots`() {
        // given
        val func = { ModuleUtil.residesInModule(moduleName = "feature/data", name = "feature....data") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Module name 'feature....data' contains more than two consecutive dots. Use '..' as a wildcard " +
            "for any number of module directories, e.g. ':feature..' or ':feature..:data'."
    }

    @Test
    fun `should return the same matcher for the same module name`() {
        // when
        val result1 = ModuleUtil.getModuleNameMatcher(":feature..")
        val result2 = ModuleUtil.getModuleNameMatcher(":feature..")

        // then
        result1 shouldBe result2
    }

    @Test
    fun `should match module for cached matcher`() {
        // given
        ModuleUtil.residesInModule(moduleName = "feature", name = "feature..data")

        // when
        val result = ModuleUtil.residesInModule(moduleName = "feature/payment/data", name = "feature..data")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should throw for invalid module name on every call`() {
        // given
        val func = { ModuleUtil.getModuleNameMatcher(":invalid...") }
        func shouldThrow IllegalArgumentException::class

        // then
        func shouldThrow IllegalArgumentException::class
    }
}
