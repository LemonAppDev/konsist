package com.lemonappdev.konsist.core.verify

/**
 * In this assert call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodName(): String? {
    val stackTraceIndexOfTestMethod = 5
    return Thread.currentThread().stackTrace[stackTraceIndexOfTestMethod].methodName
}
