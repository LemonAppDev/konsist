package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SIX = 6
private const val INDEX_EIGHT = 8

/**
 * In this call stack hierarchy test name is at index 4.
 */
internal fun getTestMethodNameFromFourthIndex() = getTestMethodName(INDEX_FOUR)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromFifthIndex() = getTestMethodName(INDEX_FIVE)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromSixthIndex() = getTestMethodName(INDEX_SIX)

/**
 * In this call stack hierarchy test name is at index 8.
 */
internal fun getTestMethodNameFromEightIndex() = getTestMethodName(INDEX_EIGHT)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
