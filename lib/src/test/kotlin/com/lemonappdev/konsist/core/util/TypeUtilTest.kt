package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TypeUtilTest {
    @Test
    fun `should match kotlin type alias with its jvm class`() {
        // when
        val result = TypeUtil.matchesKClass("kotlin.RuntimeException", RuntimeException::class)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match kotlin collection type alias with its jvm class`() {
        // when
        val result = TypeUtil.matchesKClass("kotlin.collections.ArrayList", ArrayList::class)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match kotlin mutable collection with its class`() {
        // when
        val result = TypeUtil.matchesKClass("kotlin.collections.MutableList", MutableList::class)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match kotlin mapped type with its class`() {
        // when
        val result = TypeUtil.matchesKClass("kotlin.CharSequence", CharSequence::class)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should match jvm class name with its class`() {
        // when
        val result = TypeUtil.matchesKClass("java.lang.RuntimeException", RuntimeException::class)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `should not match kotlin type alias with different class`() {
        // when
        val result = TypeUtil.matchesKClass("kotlin.RuntimeException", Exception::class)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `should not match null fully qualified name`() {
        // when
        val result = TypeUtil.matchesKClass(null, RuntimeException::class)

        // then
        result shouldBeEqualTo false
    }
}
