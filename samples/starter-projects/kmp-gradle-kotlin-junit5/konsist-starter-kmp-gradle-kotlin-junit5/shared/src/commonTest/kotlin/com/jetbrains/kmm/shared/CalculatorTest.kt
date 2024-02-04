package com.jetbrains.kmm.shared

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {

    @Test
    fun testSum() {
        assertEquals(3, Calculator.sum(1, 2))
    }

}