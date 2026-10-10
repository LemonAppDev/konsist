package com.lemonappdev.konsist.architecture.assertarchitecture.architecture10

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture10Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture10/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.domain..",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.presentation..",
        )

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.data..",
        )

    private val filepath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/" +
            "architecture10/project/presentation/PresentationClass.kt"

    private val dataImport =
        "Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.data.DataClass ($filepath:3:1)"

    private val domainImport =
        "Import com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.domain.DomainClass ($filepath:4:1)"

    @Test
    fun `dependsOn failure lists only imports of not declared layer`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.dependsOn(domain)
                    domain.dependsOnNothing()
                    data.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'dependsOn failure lists only imports of not declared layer' test has failed. \n" +
                    "'Presentation' layer depends on 'Data' layer, but this dependency is not declared. " +
                    "Files that depend on 'Data' layer:\n" +
                    "└── File $filepath\n" +
                    "    └── $dataImport",
            )
    }

    @Test
    fun `doesNotDependOn failure lists only imports of forbidden layer`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(data)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'doesNotDependOn failure lists only imports of forbidden layer' test has failed. \n" +
                    "'Presentation' layer should not depend on 'Data' layer but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    └── $dataImport",
            )
    }

    @Test
    fun `dependsOnNothing failure lists only imports of other layers`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.dependsOnNothing()
                    domain.dependsOnNothing()
                    data.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'dependsOnNothing failure lists only imports of other layers' test has failed. \n" +
                    "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    ├── $dataImport\n" +
                    "    └── $domainImport",
            )
    }
}
