package com.lemon.mango.common

import com.lemon.konsist.core.check.check
import com.lemon.mango.mangoScope
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
