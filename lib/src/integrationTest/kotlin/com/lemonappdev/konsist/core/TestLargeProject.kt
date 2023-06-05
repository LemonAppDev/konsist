package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class TestLargeProject {
    @Test
    fun `parse-large-projects`() {
        Konsist.scopeFromDirectory("/Users/natalia/IdeaProjects/LargeKotlinProjects/kotlinx.coroutines", true)
            .objects()
            .first { it.fullyQualifiedName.contains("AgentPremain") }
            .name shouldNotBeEqualTo ""
    }
}
