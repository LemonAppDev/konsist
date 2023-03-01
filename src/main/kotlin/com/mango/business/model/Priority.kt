package com.mango.business.model

@Suppress("detekt.MagicNumber")
enum class Priority(private val value: Int) {
    PRIORITY_1(1),
    PRIORITY_2(2),
    PRIORITY_3(3),
    PRIORITY_4(4),
    PRIORITY_5(5),
    ;

    companion object {
        fun getByValue(value: Int?): Priority? {
            if (value == null) {
                return null
            }

            return values().firstOrNull {
                it.value == value
            } ?: throw IllegalArgumentException("Invalid priority value: $value ")
        }
    }
}
