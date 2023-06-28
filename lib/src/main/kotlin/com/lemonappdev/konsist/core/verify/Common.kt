package com.lemonappdev.konsist.core.verify

internal fun getTestMethodName(index: Int): String? {
    return Thread.currentThread().stackTrace[index].methodName
}
