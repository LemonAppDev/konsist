package com.lemon.mango.domain

import com.lemon.konsist.core.assertion.check.check
import com.lemon.mango.mangoScope
import org.junit.jupiter.api.Test

class UseCaseKonsistTest {
    private val sut = mangoScope
        .classes()
        .filter { it.name.endsWith("UseCase") }

    @Test
    fun `UseCase class should have one public invoke operator method`() {
        sut.check { it.containsFunction("invoke") && it.isPublicOrDefault }
    }

    @Test
    fun `UseCase class should reside in __domain__usecase__ package`() {
        sut.check { it.resideInPackages("..domain..usecase..") }
    }
}
