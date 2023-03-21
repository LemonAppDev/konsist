package com.test.mangotest.common

import com.konsistcore.core.assertion.check.check
import com.test.mangotest.mangoScope
import org.junit.jupiter.api.Test

class ValueClassKonsistTest {
    @Test
    fun `value class has parameter named 'value'`() {
        mangoScope
            .classes()
            .filter { it.isValue }
            .mapNotNull { it.primaryConstructor }
            .check { it.hasParameterNamed("value") }
    }
}
