package com.lemonappdev.konsist.architecture.assertarchitecture.architecture10

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.verify.KoKotestExtension
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldStartWith

class Architecture10KotestTest :
    FunSpec({
        extension(KoKotestExtension)

        test("architecture test name derived from kotest test with extension") {
            // given
            val scope =
                Konsist.scopeFromDirectory(
                    "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture10/project",
                )

            val presentation =
                Layer(
                    "Presentation",
                    "com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.presentation..",
                )

            val data =
                Layer(
                    "Data",
                    "com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.data..",
                )

            // when
            val result =
                shouldThrow<KoAssertionFailedException> {
                    scope.assertArchitecture { presentation.doesNotDependOn(data) }
                }

            // then
            result.message?.shouldStartWith("'architecture test name derived from kotest test with extension' test has failed.")
        }
    })
