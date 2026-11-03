package com.lemonappdev.konsist.architecture.assertarchitecture.architecture14

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture14Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture14/project",
        )

    private val presentation = Layer("Presentation", "..presentation..")

    private val domain = Layer("Domain", "..domain..")

    private val data = Layer("Data", "..data..")

    private val projectPackage = "com.lemonappdev.konsist.architecture.assertarchitecture.architecture14.project"

    private val projectPath =
        "file://$rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture14/project"

    private val domainFilePath = "$projectPath/domain/DomainClass.kt"

    private val dataFilePath = "$projectPath/data/DataClass.kt"

    @Test
    fun `passes when imported class name matches other layer package`() {
        // then
        scope.assertArchitecture(allowFilesOutsideLayers = true) {
            presentation.dependsOnNothing()
            data.include()
        }
    }

    @Test
    fun `fails when imported class name matches own layer package`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(allowFilesOutsideLayers = true) {
                    domain.dependsOnNothing()
                    presentation.include()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when imported class name matches own layer package' test has failed. \n" +
                    "'Domain' layer should not depend on anything but has dependencies in files:\n" +
                    "└── File $domainFilePath\n" +
                    "    └── Import $projectPackage.presentation.Domain ($domainFilePath:3:1)",
            )
    }

    @Test
    fun `fails when wildcard, nested class and top-level function imports reside in not allowed layer`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(allowFilesOutsideLayers = true) {
                    data.doesNotDependOn(domain)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when wildcard, nested class and top-level function imports reside in not allowed layer' " +
                    "test has failed. \n" +
                    "'Data' layer should not depend on 'Domain' layer but has dependencies in files:\n" +
                    "└── File $dataFilePath\n" +
                    "    ├── Import $projectPackage.domain ($dataFilePath:5:1)\n" +
                    "    ├── Import $projectPackage.domain.DomainClass.Nested ($dataFilePath:6:1)\n" +
                    "    └── Import $projectPackage.domain.domainFunction ($dataFilePath:7:1)",
            )
    }
}
