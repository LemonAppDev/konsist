package com.lemon.mango.domain.activity.model

import com.lemon.mango.domain.activity.model.TaskActivityType.CREATE
import com.lemon.mango.domain.activity.model.TaskActivityType.Companion.getByValue
import com.lemon.mango.domain.activity.model.TaskActivityType.DELETE
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_ASSIGNEE
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_COMPLETE_DATE
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_DESCRIPTION
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_DUE_DATE
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_NAME
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_PARENT_TASK
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_PRIORITY
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_PROJECT
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_TARGET_DATE
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class TaskActivityTypeTest {
    @ParameterizedTest(name = "given {1} returns {2}")
    @MethodSource("provideValues")
    fun `given value return TaskActivityType`(
        value: String,
        taskActivityType: TaskActivityType,
    ) {
        // when
        val actual = getByValue(value)

        // then
        actual shouldBeEqualTo taskActivityType
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("create", CREATE),
            arguments("delete", DELETE),
            arguments("update_description", UPDATE_DESCRIPTION),
            arguments("update_assignee", UPDATE_ASSIGNEE),
            arguments("update_complete_date", UPDATE_COMPLETE_DATE),
            arguments("update_due_date", UPDATE_DUE_DATE),
            arguments("update_name", UPDATE_NAME),
            arguments("update_parent_task", UPDATE_PARENT_TASK),
            arguments("update_priority", UPDATE_PRIORITY),
            arguments("update_project", UPDATE_PROJECT),
            arguments("update_target_date", UPDATE_TARGET_DATE),
        )
    }

    @Test
    fun `getByValue() throw exception when value is incorrect`() {
        // given
        val value = "incorrectValue"

        // when
        val actual = { getByValue(value) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid task activity type value: $value "
    }
}
