package com.sample

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingAndroidTest {
    @Test
    fun greetingMentionsAndroid() {
        assertTrue(Greeting().greet().contains("Android"))
    }
}
