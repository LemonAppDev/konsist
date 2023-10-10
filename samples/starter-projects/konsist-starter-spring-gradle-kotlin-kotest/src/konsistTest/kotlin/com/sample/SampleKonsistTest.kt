package com.sample

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import io.kotest.core.spec.style.FreeSpec

class SampleKonsistTest : FreeSpec({
    val useCases = Konsist
        .scopeFromProject()
        .classes()
        .withNameEndingWith("UseCase")

    "use case should have test" {
        useCases.assertTrue(testName = this.testCase.name.testName) { it.hasTestClass() }
    }

    "use case should reside in ..domain.usecase.. package" {
        useCases.assertTrue(testName = this.testCase.name.testName) { it.resideInPackage("..domain.usecase..") }
    }
})
