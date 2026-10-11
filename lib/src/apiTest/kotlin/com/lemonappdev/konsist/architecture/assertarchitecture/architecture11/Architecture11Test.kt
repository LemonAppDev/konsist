package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test

class Architecture11Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project",
        )

    private val core =
        Layer(
            "Core",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.core",
        )

    private val coreApi =
        Layer(
            "CoreApi",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.core.api..",
        )

    private val ui =
        Layer(
            "UI",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.ui..",
            excludedPackages = setOf("com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.ui.viewmodel.."),
        )

    private val viewModel =
        Layer(
            "ViewModel",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.ui.viewmodel..",
        )

    private val projectPath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture11/project"

    @Test
    fun `passes when all layer dependencies are declared`() {
        // when
        val func = {
            scope.assertArchitecture {
                ui.dependsOn(core, viewModel)
                core.dependsOn(coreApi)
                viewModel.dependsOn(coreApi)
                coreApi.dependsOnNothing()
            }
        }

        // then
        func shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `exact package layer does not contain imports of its subpackages`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    core.dependsOnNothing()
                    coreApi.dependsOnNothing()
                }
            }

        // then
        val filepath = "$projectPath/core/CoreClass.kt"

        result
            .message
            .shouldBeEqualTo(
                "'exact package layer does not contain imports of its subpackages' test has failed. \n" +
                    "'Core' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.core.api." +
                    "CoreApiClass ($filepath:3:1)",
            )
    }

    @Test
    fun `exact package layer contains imports of nested classes`() {
        // given
        val exactCoreApi =
            Layer(
                "CoreApi",
                "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.core.api",
            )

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    viewModel.doesNotDependOn(exactCoreApi)
                }
            }

        // then
        val filepath = "$projectPath/ui/viewmodel/UiViewModel.kt"

        result
            .message
            .shouldBeEqualTo(
                "'exact package layer contains imports of nested classes' test has failed. \n" +
                    "'ViewModel' layer should not depend on 'CoreApi' layer but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.core.api." +
                    "CoreApiClass.NestedClass ($filepath:3:1)",
            )
    }

    @Test
    fun `exact package layer does not contain subpackage imports of other layer`() {
        // when
        val func = {
            scope.assertArchitecture {
                viewModel.doesNotDependOn(core)
            }
        }

        // then
        func shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `exact package layer without files in exact package throws exception`() {
        // given
        val project =
            Layer(
                "Project",
                "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project",
            )

        // when
        val result =
            shouldThrow<KoPreconditionFailedException> {
                scope.assertArchitecture {
                    project.dependsOnNothing()
                }
            }

        // then
        result.message shouldBeEqualTo "Layer Project doesn't contain any files."
    }

    @Test
    fun `layer does not contain imports of excluded packages`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    ui.dependsOnNothing()
                    viewModel.dependsOnNothing()
                }
            }

        // then
        val filepath = "$projectPath/ui/UiClass.kt"

        result
            .message
            .shouldBeEqualTo(
                "'layer does not contain imports of excluded packages' test has failed. \n" +
                    "'UI' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.ui.viewmodel." +
                    "UiViewModel ($filepath:4:1)",
            )
    }

    @Test
    fun `layer does not contain files of excluded packages`() {
        // when
        val func = {
            scope.assertArchitecture {
                ui.doesNotDependOn(coreApi)
            }
        }

        // then
        func shouldNotThrow KoAssertionFailedException::class
    }
}
