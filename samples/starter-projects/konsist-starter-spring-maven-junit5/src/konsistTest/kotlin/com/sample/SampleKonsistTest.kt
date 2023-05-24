package com.sample

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.core.verify.assert
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication

class SampleKonsistTest {
    @Test
    fun `spring application class name ends with 'SpringBootApplication'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf<SpringBootApplication>()
            .assert { it.name.endsWith("SpringBootApplication") }
    }
}
