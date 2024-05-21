package com.sample

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.verify.assertTrue
import io.kotest.core.spec.style.FreeSpec
import org.springframework.boot.autoconfigure.SpringBootApplication

class SampleKonsistTest : FreeSpec({
    "spring application class name ends with 'SpringBootApplication'" {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllAnnotationsOf(SpringBootApplication::class)
            .assertTrue { it.name.endsWith("SpringBootApplication") }
    }
})

