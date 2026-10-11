package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture11Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project",
        )

    private val app =
        Layer(
            "App",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project..",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain..",
        )

    private val anyDomain = Layer("AnyDomain", "..domain..")

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data..",
        )

    private val projectPath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project"

    private val domainFile = "File $projectPath/domain/DomainClass.kt"

    private val dataFile = "File $projectPath/data/DataClass.kt"

    @Test
    fun `nested layer fails precondition`() {
        // when
        val result =
            shouldThrow<KoPreconditionFailedException> {
                scope.assertArchitecture {
                    app.dependsOnNothing()
                    domain.include()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "Layers overlap. Each file must belong to only one layer:\n" +
                    "└── Layers 'App', 'Domain' share files:\n" +
                    "    └── $domainFile",
            )
    }

    @Test
    fun `wildcard layer overlapping other layer fails precondition`() {
        // when
        val result =
            shouldThrow<KoPreconditionFailedException> {
                scope.assertArchitecture {
                    domain.dependsOnNothing()
                    anyDomain.include()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "Layers overlap. Each file must belong to only one layer:\n" +
                    "└── Layers 'Domain', 'AnyDomain' share files:\n" +
                    "    └── $domainFile",
            )
    }

    @Test
    fun `overlap failure lists files grouped by overlapping layers`() {
        // when
        val result =
            shouldThrow<KoPreconditionFailedException> {
                scope.assertArchitecture {
                    app.doesNotDependOn(domain, data)
                    anyDomain.include()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "Layers overlap. Each file must belong to only one layer:\n" +
                    "├── Layers 'App', 'Data' share files:\n" +
                    "│   └── $dataFile\n" +
                    "└── Layers 'App', 'Domain', 'AnyDomain' share files:\n" +
                    "    └── $domainFile",
            )
    }

    @Test
    fun `non overlapping layers pass precondition`() {
        // then
        shouldNotThrowAny {
            scope.assertArchitecture {
                domain.dependsOnNothing()
                data.dependsOnNothing()
            }
        }
    }

    @Test
    fun `exact package layer does not overlap its subpackage layers`() {
        // given
        val exactApp =
            Layer(
                "App",
                "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project",
            )

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    exactApp.dependsOnNothing()
                    domain.include()
                }
            }

        // then
        result.message shouldBeEqualTo getAppDependsOnNothingMessage("exact package layer does not overlap its subpackage layers")
    }

    @Test
    fun `layer does not overlap its excluded packages`() {
        // given
        val appWithoutDomainAndData =
            Layer(
                "App",
                "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project..",
                excludedPackages =
                    setOf(
                        "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain..",
                        "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data..",
                    ),
            )

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    appWithoutDomainAndData.dependsOnNothing()
                    domain.include()
                    data.include()
                }
            }

        // then
        result.message shouldBeEqualTo getAppDependsOnNothingMessage("layer does not overlap its excluded packages")
    }

    private fun getAppDependsOnNothingMessage(testName: String): String {
        val appFilePath = "$projectPath/AppClass.kt"

        return "'$testName' test has failed. \n" +
            "'App' layer should not depend on anything but has dependencies in files:\n" +
            "└── File $appFilePath\n" +
            "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain." +
            "DomainClass ($appFilePath:3:1)"
    }
}
