package com.lemon.mango.application

import com.lemon.konsist.core.check.check
import com.lemon.mango.mangoScope
import org.junit.jupiter.api.Test
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class ControllerClassKonsistTest {
    private val sut = mangoScope
        .classes()
        .filter { it.hasAnnotation(RestController::class) }

    @Test
    fun `classes annotated with 'RestController' should reside in __application__controller__ package`() {
        sut.check { it.resideInPackages("..application..controller..") }
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
