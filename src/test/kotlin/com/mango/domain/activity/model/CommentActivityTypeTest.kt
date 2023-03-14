package com.mango.domain.activity.model

import com.mango.domain.activity.model.CommentActivityType.ADD_COMMENT
import com.mango.domain.activity.model.CommentActivityType.Companion.getByValue
import com.mango.domain.activity.model.CommentActivityType.DELETE_COMMENT
import com.mango.domain.activity.model.CommentActivityType.UPDATE_COMMENT
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class CommentActivityTypeTest {
    @ParameterizedTest(name = "given {1} returns {2}")
    @MethodSource("provideValues")
    fun `given value return CommentActivityType`(
        value: String,
        commentActivityType: CommentActivityType,
    ) {
        // when
        val actual = getByValue(value)

        // then
        actual shouldBeEqualTo commentActivityType
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("add", ADD_COMMENT),
            arguments("delete", DELETE_COMMENT),
            arguments("update", UPDATE_COMMENT),
        )
    }

    @Test
    fun `getByValue() throw exception when value is incorrect`() {
        // given
        val value = "incorrectValue"

        // when
        val actual = { getByValue(value) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid comment activity type value: $value "
    }
}
