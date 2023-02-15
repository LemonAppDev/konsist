package com.example.mango.api

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GreetingControllerTest {
    private val sut = GreetingController()

    @Test
    fun `greeting without param`() {
        // when
        val actual = sut.greeting()

        // then
        actual shouldBeEqualTo Greeting(1, "Hello World")
    }

    @Test
    fun `greeting with param`() {
        // given
        val name = "aga"

        // when
        val actual = sut.greeting(name)

        // then
        actual shouldBeEqualTo Greeting(1, "Hello $name")
    }

    @Test
    fun `greeting increments id`() {
        // given
        sut.greeting()

        // when
        val actual = sut.greeting()

        // then
        actual shouldBeEqualTo Greeting(2, "Hello World")
    }
}
