package com.mango.domain.activity.model

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ProjectActivityTypeTest {
    @ParameterizedTest(name = "given {1} returns {2}")
    @MethodSource("provideValues")
    fun `given value return ProjectActivityType`(
        value: String,
        projectActivityType: ProjectActivityType,
    ) {
        // when
        val actual = ProjectActivityType.getByValue(value)

        // then
        actual shouldBeEqualTo projectActivityType
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("create", ProjectActivityType.CREATE),
            arguments("delete", ProjectActivityType.DELETE),
        )
    }

    @Test
    fun `getByValue() throw exception when value is incorrect`() {
        // given
        val value = "incorrectValue"

        // when
        val actual = { ProjectActivityType.getByValue(value) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid project activity type value: $value "
    }
}
