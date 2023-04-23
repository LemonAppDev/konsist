package com.sample

import com.lemonappdev.konsist.core.check.assert
import com.lemonappdev.konsist.core.declaration.KoScope
import com.lemonappdev.konsist.core.ext.withAnnotationOf
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication

class SampleKonsistTest {
    @Test
    fun `all declarations reside in project package`() {
        KoScope
            .fromProjectFiles()
            .classes()
            .withAnnotationOf<SpringBootApplication>()
            .assert { it.name == "SampleApplication" }
    }
}
