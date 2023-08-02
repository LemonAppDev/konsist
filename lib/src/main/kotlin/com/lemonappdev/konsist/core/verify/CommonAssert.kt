package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SEVEN = 7

/**
 * In this call stack hierarchy test name is at index 4.
 */
internal fun getTestMethodNameFromFourthIndex() = getTestMethodName(INDEX_FOUR)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromFifthIndex() = getTestMethodName(INDEX_FIVE)

/**
 * In this call stack hierarchy test name is at index 7.
 */
internal fun getTestMethodNameFromSeventhIndex() = getTestMethodName(INDEX_SEVEN)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
