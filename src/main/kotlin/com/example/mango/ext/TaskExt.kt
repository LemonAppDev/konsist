package com.example.mango.ext

import com.example.mango.data.factory.LocalDateTimeFactory
import com.example.mango.data.model.Task

val Task.isCompleted: Boolean get() = completeDate != null

fun Task.markAsComplete(localDateTimeFactory: LocalDateTimeFactory) {
    completeDate = localDateTimeFactory.create()
}
