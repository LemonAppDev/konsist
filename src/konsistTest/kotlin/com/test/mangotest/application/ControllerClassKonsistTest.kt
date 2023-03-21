package com.test.mangotest.application

import com.konsistcore.core.assertion.check.check
import com.test.mangotest.mangoScope
import org.junit.jupiter.api.Test
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class ControllerClassKonsistTest {
    private val sut = mangoScope
        .classes()
        .filter { it.hasAnnotation(RestController::class) }

    @Test
    fun `classes annotated with 'RestController' should reside in __application__controller__ package`() {
        sut.check { it.resideInAPackages("..application..controller..") }
    }

    @Test
    fun `classes annotated with 'RestController' should have 'Controller' suffix`() {
        sut.check { it.name.endsWith("Controller") }
    }

    @Test
    fun `classes annotated with 'RestController' should have 'RequestMapping' annotation`() {
        sut.check { it.hasAnnotation(RequestMapping::class) }
    }
}
