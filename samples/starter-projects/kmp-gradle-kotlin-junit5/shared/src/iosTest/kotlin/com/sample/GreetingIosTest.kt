package com.sample

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingIosTest {
    @Test
    fun greetingMentionsIos() {
        assertTrue(Greeting().greet().contains("iOS"))
    }
}
