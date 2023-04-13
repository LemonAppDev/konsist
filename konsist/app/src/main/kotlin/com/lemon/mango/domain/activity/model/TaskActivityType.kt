package com.lemon.mango.domain.activity.model

enum class TaskActivityType(val value: String) {
    CREATE("create"),
    DELETE("delete"),
    UPDATE_DESCRIPTION("update_description"),
    UPDATE_ASSIGNEE("update_assignee"),
    UPDATE_COMPLETE_DATE("update_complete_date"),
    UPDATE_DUE_DATE("update_due_date"),
    UPDATE_NAME("update_name"),
    UPDATE_PARENT_TASK("update_parent_task"),
    UPDATE_PRIORITY("update_priority"),
    UPDATE_PROJECT("update_project"),
    UPDATE_TARGET_DATE("update_target_date"),
    ;

    companion object {
        fun getByValue(value: String) = TaskActivityType.values().firstOrNull {
            it.value == value
        } ?: throw IllegalArgumentException("Invalid task activity type value: $value ")
    }
}
