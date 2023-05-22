/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CalculatorTests {

    @Test
    internal fun `1 + 1 = 2`() = assertEquals(2, Calculator().add(1, 1), "1 + 1 should equal 2")

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(
        "0 ,   1,   1",
        "1 ,   2,   3",
        "49,  51, 100",
        "1 , 100, 101",
    )
    internal fun add(first: Int, second: Int, expectedResult: Int) {
        val calculator = Calculator()
        assertEquals(expectedResult, calculator.add(first, second)) {
            "$first + $second should equal $expectedResult"
        }
    }
}
