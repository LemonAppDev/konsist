package com.lemonappdev.konsist.architecture.assertarchitecture.architecture15

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture15Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture15/project",
        )

    private val app =
        Layer(
            "App",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture15.project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture15.project.domain..",
        )

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture15.project.data..",
        )

    private val projectPath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture15/project"

    private val appFilePath = "$projectPath/AppClass.kt"

    private val domainFilePath = "$projectPath/domain/DomainClass.kt"

    private val dataFilePath = "$projectPath/data/DataClass.kt"

    @Test
    fun `single package layer does not overlap layers in its subpackages`() {
        // then
        shouldNotThrowAny {
            scope.assertArchitecture {
                app.dependsOn(domain)
                data.dependsOn(domain)
                domain.dependsOnNothing()
            }
        }
    }

    @Test
    fun `single package layer dependencies are checked`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    app.dependsOnNothing()
                    data.dependsOn(domain)
                    domain.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'single package layer dependencies are checked' test has failed. \n" +
                    "'App' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $appFilePath\n" +
                    "    └── Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture15.project.domain.DomainClass " +
                    "($appFilePath:3:1)",
            )
    }

    @Test
    fun `files in subpackages reside outside single package layer`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    app.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'files in subpackages reside outside single package layer' test has failed. \n" +
                    "All files are required to reside in a layer, but these files reside in no layer:\n" +
                    "├── File $dataFilePath\n" +
                    "└── File $domainFilePath",
            )
    }
}
