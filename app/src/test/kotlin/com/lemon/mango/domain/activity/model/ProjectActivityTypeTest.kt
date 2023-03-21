package com.lemon.mango.domain.activity.model

import com.lemon.mango.domain.activity.model.ProjectActivityType.CREATE
import com.lemon.mango.domain.activity.model.ProjectActivityType.Companion.getByValue
import com.lemon.mango.domain.activity.model.ProjectActivityType.DELETE
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_ADDED
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_MOVED
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_REMOVED
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
        val actual = getByValue(value)

        // then
        actual shouldBeEqualTo projectActivityType
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("create", CREATE),
            arguments("delete", DELETE),
            arguments("task_added", TASK_ADDED),
            arguments("task_removed", TASK_REMOVED),
            arguments("task_moved", TASK_MOVED),
        )
    }

    @Test
    fun `getByValue() throw exception when value is incorrect`() {
        // given
        val value = "incorrectValue"

        // when
        val actual = { getByValue(value) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid project activity type value: $value "
    }
}
