package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SIX = 6
private const val INDEX_SEVEN = 7

internal fun getTestMethodNameFromFourthIndex() = getTestMethodName(INDEX_FOUR)

internal fun getTestMethodNameFromFifthIndex() = getTestMethodName(INDEX_FIVE)

internal fun getTestMethodNameFromSixthIndex() = getTestMethodName(INDEX_SIX)

internal fun getTestMethodNameFromSeventhIndex() = getTestMethodName(INDEX_SEVEN)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
