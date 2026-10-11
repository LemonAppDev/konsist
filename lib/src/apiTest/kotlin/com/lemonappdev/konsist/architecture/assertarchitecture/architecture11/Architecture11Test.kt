package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture11Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain..",
        )

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data..",
        )

    private val util =
        Layer(
            "Util",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.util..",
        )

    private val projectPath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project"

    private val noPackageFilePath = "$projectPath/Architecture11NoPackageClass.kt"

    private val utilFilePath = "$projectPath/util/UtilClass.kt"

    private val dataFilePath = "$projectPath/data/DataClass.kt"

    @Test
    fun `passes when files reside outside layers and requireAllFilesInLayers is not set`() {
        // then
        scope.assertArchitecture {
            domain.dependsOnNothing()
            data.dependsOn(domain)
        }
    }

    @Test
    fun `passes when all files reside in layers (lambda files)`() {
        // then
        scope
            .files
            .filter { it.packagee != null }
            .assertArchitecture {
                domain.dependsOnNothing()
                data.dependsOn(domain)
                util.include()
                requireAllFilesInLayers()
            }
    }

    @Test
    fun `fails when files reside outside layers (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    domain.dependsOnNothing()
                    data.dependsOn(domain)
                    requireAllFilesInLayers()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when files reside outside layers (lambda scope)' test has failed. \n" +
                    "All files are required to reside in a layer, but these files reside in no layer:\n" +
                    "├── File $noPackageFilePath\n" +
                    "└── File $utilFilePath",
            )
    }

    @Test
    fun `fails when file without package resides outside layers (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                data.dependsOn(domain)
                util.include()
                requireAllFilesInLayers()
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.files.assertArchitecture(layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when file without package resides outside layers (parameter files)' test has failed. \n" +
                    "All files are required to reside in a layer, but these files reside in no layer:\n" +
                    "└── File $noPackageFilePath",
            )
    }

    @Test
    fun `fails with dependency and outside layer violations`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    domain.dependsOnNothing()
                    data.dependsOnNothing()
                    requireAllFilesInLayers()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails with dependency and outside layer violations' test has failed. \n" +
                    "'Data' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $dataFilePath\n" +
                    "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain.DomainClass " +
                    "($dataFilePath:3:1)\n" +
                    "All files are required to reside in a layer, but these files reside in no layer:\n" +
                    "├── File $noPackageFilePath\n" +
                    "└── File $utilFilePath",
            )
    }
}
