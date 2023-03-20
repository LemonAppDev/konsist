package com.mango.domain.activity.model

enum class ProjectActivityType(val value: String) {
    CREATE("create"),
    DELETE("delete"),
    TASK_ADDED("task_added"),
    TASK_REMOVED("task_removed"),
    TASK_MOVED("task_moved"),
    ;

    companion object {
        fun getByValue(value: String) = ProjectActivityType.values().firstOrNull {
            it.value == value
        } ?: throw IllegalArgumentException("Invalid project activity type value: $value ")
    }
}
