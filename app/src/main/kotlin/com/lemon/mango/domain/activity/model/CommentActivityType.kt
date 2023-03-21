package com.lemon.mango.domain.activity.model

enum class CommentActivityType(val value: String) {
    ADD_COMMENT("add"),
    DELETE_COMMENT("delete"),
    UPDATE_COMMENT("update"),
    ;

    companion object {
        fun getByValue(value: String) = CommentActivityType.values().firstOrNull {
            it.value == value
        } ?: throw IllegalArgumentException("Invalid comment activity type value: $value ")
    }
}
