package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert
import org.junit.jupiter.api.Test

class TestLargeProject {
    @Test
    fun `parse-large-projects`() {
        Konsist.scopeFromDirectory("/Users/natalia/IdeaProjects/LargeKotlinProjects/kotlinx.coroutines", true)
            .declarations()
            .assert { it.name != "" }
    }
}
