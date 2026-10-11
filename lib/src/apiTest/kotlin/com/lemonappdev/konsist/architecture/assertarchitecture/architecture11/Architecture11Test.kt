package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
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

    private val projectPackage = "com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project"

    private val domain = Layer("Domain", "$projectPackage.domain..")

    private val presentation = Layer("Presentation", "$projectPackage.presentation..")

    private val data = Layer("Data", "$projectPackage.data..")

    private val filepath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/" +
            "architecture11/project/presentation/PresentationClass.kt"

    private val domainClassReference = "Reference $projectPackage.domain.DomainClass ($filepath:7:22)"

    private val dataClassReference = "Reference $projectPackage.data.DataClass ($filepath:10:9)"

    private val dataFunctionReference = "Reference $projectPackage.data.dataFunction ($filepath:14:9)"

    @Test
    fun `passes when fully qualified references point to declared layers`() {
        // when
        val func = {
            scope.assertArchitecture {
                presentation.dependsOn(domain, data, strict = true)
                domain.dependsOnNothing()
                data.dependsOnNothing()
            }
        }

        // then
        func shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `dependsOn failure lists fully qualified references of not declared layer`() {
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
                "'dependsOn failure lists fully qualified references of not declared layer' test has failed. \n" +
                    "'Presentation' layer depends on 'Data' layer, but this dependency is not declared. " +
                    "Files that depend on 'Data' layer:\n" +
                    "└── File $filepath\n" +
                    "    ├── $dataClassReference\n" +
                    "    └── $dataFunctionReference",
            )
    }

    @Test
    fun `doesNotDependOn failure lists fully qualified references of forbidden layer`() {
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
                "'doesNotDependOn failure lists fully qualified references of forbidden layer' test has failed. \n" +
                    "'Presentation' layer should not depend on 'Data' layer but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    ├── $dataClassReference\n" +
                    "    └── $dataFunctionReference",
            )
    }

    @Test
    fun `dependsOnNothing failure lists fully qualified references of other layers`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.files.assertArchitecture {
                    presentation.dependsOnNothing()
                    domain.dependsOnNothing()
                    data.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'dependsOnNothing failure lists fully qualified references of other layers' test has failed. \n" +
                    "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    ├── $domainClassReference\n" +
                    "    ├── $dataClassReference\n" +
                    "    └── $dataFunctionReference",
            )
    }

    @Test
    fun `property chain matching wildcard layer package is not a dependency`() {
        // given
        val wildcardData = Layer("Data", "..data..")

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(wildcardData)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'property chain matching wildcard layer package is not a dependency' test has failed. \n" +
                    "'Presentation' layer should not depend on 'Data' layer but has dependencies in files:\n" +
                    "└── File $filepath\n" +
                    "    ├── $dataClassReference\n" +
                    "    └── $dataFunctionReference",
            )
    }
}
