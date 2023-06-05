package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import org.junit.jupiter.api.Test

class TestLargeProject {
    @Test
    fun `parse-large-projects`() {
        Konsist.scopeFromDirectory("/Users/natalia/IdeaProjects/LargeKotlinProjects/kotlinx.coroutines", true)
            .declarations()
    }
}
