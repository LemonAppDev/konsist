package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class KoScopeFromDirectoryTest {
    @Test
    fun `scopeFromDirectory`() {
        // given
        val sut =
            Konsist
                .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/fixture/")
                .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectory accepts windows separators`() {
        // given
        val sut =
            Konsist
                .scopeFromDirectory("""app\src\main\kotlin\com\lemonappdev\fixture\""")
                .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromExternalDirectory accepts unix and windows separators`() {
        // given
        val unixPath = "$appMainSourceSetDirectory/fixture/".replace("\\", "/")
        val windowsPath = "$appMainSourceSetDirectory/fixture/".replace("/", "\\")

        // when
        val unixSut = Konsist.scopeFromExternalDirectory(unixPath).mapToFilePaths()
        val windowsSut = Konsist.scopeFromExternalDirectory(windowsPath).mapToFilePaths()

        // then
        val expected =
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator()
        unixSut shouldBeEqualTo expected
        windowsSut shouldBeEqualTo expected
    }

    @Test
    fun `scopeFromDirectory throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/") }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory throws exception if path points to file`() {
        // given
        val func =
            {
                Konsist.scopeFromDirectory(
                    "app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt",
                )
            }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}fixture${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory accepts dot slash prefix`() {
        // given
        val sut =
            Konsist
                .scopeFromDirectory("./app/src/main/kotlin/com/lemonappdev/fixture/")
                .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectory with dot slash prefix has module name app`() {
        // given
        val sut = Konsist.scopeFromDirectory("./app")

        // then
        sut.files.map { it.moduleName }.distinct() shouldBeEqualTo listOf("app")
    }

    @Test
    fun `scopeFromDirectory accepts parent directory segment`() {
        // given
        val sut =
            Konsist
                .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/fixture/data/../")
                .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectory excludes build and hidden directories`() {
        // given
        val generatedFilePath = "$projectRootDirectory/$GENERATED_FILE_PROJECT_PATH".toOsSeparator()
        val hiddenFilePath = "$projectRootDirectory/$HIDDEN_FILE_PROJECT_PATH".toOsSeparator()
        val appDirectoryPath = "$projectRootDirectory/app/".toOsSeparator()

        // when
        val sut =
            Konsist
                .scopeFromDirectory("app")
                .mapToFilePaths()

        // then
        File(generatedFilePath).exists() shouldBeEqualTo true
        File(hiddenFilePath).exists() shouldBeEqualTo true
        sut shouldContain "$appMainSourceSetDirectory/fixture/AppClass.kt".toOsSeparator()
        sut shouldNotContain generatedFilePath
        sut shouldNotContain hiddenFilePath
        sut shouldBeEqualTo
            Konsist
                .scopeFromProject()
                .mapToFilePaths()
                .filter { it.startsWith(appDirectoryPath) }
    }

    @Test
    fun `scopeFromDirectory includes files from explicitly passed build directory`() {
        // given
        val sut =
            Konsist
                .scopeFromDirectory("app/build/generated/konsist")
                .mapToFilePaths()

        // then
        sut shouldBeEqualTo listOf("$projectRootDirectory/$GENERATED_FILE_PROJECT_PATH".toOsSeparator())
    }

    @Test
    fun `scopeFromExternalDirectory excludes build and hidden directories`(
        @TempDir directory: File,
    ) {
        // given
        val sourceFile = directory.createKotlinFile("src/main/kotlin/SourceClass.kt")
        directory.createKotlinFile("build/generated/GeneratedClass.kt")
        directory.createKotlinFile(".hidden/HiddenClass.kt")
        directory.createKotlinFile("node_modules/NodeModulesClass.kt")

        // when
        val sut =
            Konsist
                .scopeFromExternalDirectory(directory.path)
                .mapToFilePaths()

        // then
        sut shouldBeEqualTo listOf(sourceFile.path)
    }

    private fun File.createKotlinFile(path: String): File =
        resolve(path).apply {
            parentFile.mkdirs()
            writeText("class $nameWithoutExtension\n")
        }

    companion object {
        // Created by the "generateBuildDirectoryFixture" Gradle task
        private const val GENERATED_FILE_PROJECT_PATH = "app/build/generated/konsist/com/lemonappdev/fixture/GeneratedClass.kt"

        private const val HIDDEN_FILE_PROJECT_PATH = "app/.hidden/HiddenClass.kt"
    }
}
