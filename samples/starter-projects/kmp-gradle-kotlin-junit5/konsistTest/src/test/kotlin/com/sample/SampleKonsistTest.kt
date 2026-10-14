package com.sample

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `classes with 'UseCase' suffix should reside in 'domain' and 'usecase' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue { it.residesInPackage("..domain.usecase..") }
    }

    @Test
    fun `every expect declaration has actual declaration in each platform source set`() {
        val actualFunctionNames = Konsist
            .scopeFromProduction()
            .functions()
            .filter { it.hasActualModifier }
            .groupBy { it.sourceSetName }
            .mapValues { (_, functions) -> functions.map { it.name } }

        Konsist
            .scopeFromSourceSet("commonMain")
            .functions()
            .filter { it.hasExpectModifier }
            .assertTrue { expectFunction ->
                listOf("androidMain", "iosMain").all { actualFunctionNames[it].orEmpty().contains(expectFunction.name) }
            }
    }
}
