package com.lemonappdev.konsist.core.verify

private const val atFourthIndex = 4
private const val atSixthIndex = 6
private const val atEightIndex = 8

/**
 * In this call stack hierarchy test name is at index 4.
 */
internal fun getTestMethodNameFromFourthIndex() = getTestMethodName(atFourthIndex)

/**
 * In this call stack hierarchy test name is at index 6.
 */
internal fun getTestMethodNameFromSixthIndex() = getTestMethodName(atSixthIndex)

/**
 * In this call stack hierarchy test name is at index 8.
 */
internal fun getTestMethodNameFromEightIndex() = getTestMethodName(atEightIndex)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
