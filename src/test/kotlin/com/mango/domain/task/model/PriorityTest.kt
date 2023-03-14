package com.mango.domain.task.model

import com.mango.domain.task.model.Priority.PRIORITY_1
import com.mango.domain.task.model.Priority.PRIORITY_2
import com.mango.domain.task.model.Priority.PRIORITY_3
import com.mango.domain.task.model.Priority.PRIORITY_4
import com.mango.domain.task.model.Priority.PRIORITY_5
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class PriorityTest {
    @ParameterizedTest(name = "given {1} returns {2}")
    @MethodSource("provideValues")
    fun `given value return Priority`(
        value: Int,
        priority: Priority,
    ) {
        // when
        val actual = Priority.getByValue(value)

        // then
        actual shouldBeEqualTo priority
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(1, PRIORITY_1),
            arguments(2, PRIORITY_2),
            arguments(3, PRIORITY_3),
            arguments(4, PRIORITY_4),
            arguments(5, PRIORITY_5),
        )
    }

    @Test
    fun `getByValue() throw exception when value is 6`() {
        // given
        val value = 6

        // when
        val actual = { Priority.getByValue(value) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid priority value: $value "
    }

    @Test
    fun `getByValue() return null when value is null`() {
        // given
        val value = null

        // when
        val actual = Priority.getByValue(value)

        // then
        actual shouldBeEqualTo null
    }
}
