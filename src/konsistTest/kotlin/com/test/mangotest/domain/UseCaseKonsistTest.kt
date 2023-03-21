package com.test.mangotest.domain

import com.konsistcore.core.assertion.check.check
import com.test.mangotest.mangoScope
import org.junit.jupiter.api.Test

class UseCaseKonsistTest {
    private val sut = mangoScope
        .classes()
        .filter { it.name.endsWith("UseCase") }

    @Test
    fun `UseCase class should have one public invoke operator method`() {
        sut.check { it.hasFunction("invoke") && it.isPublic }
    }

    @Test
    fun `UseCase class should reside in __domain__usecase__ package`() {
        sut.check { it.resideInAPackages("..domain..usecase..") }
    }
}
