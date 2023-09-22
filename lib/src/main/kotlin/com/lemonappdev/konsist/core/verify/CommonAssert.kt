package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SIX = 6
private const val INDEX_SEVEN = 7

internal fun getTestMethodNameFromFourthIndex() = getTestMethodNameWithStackWalker(INDEX_FOUR)

internal fun getTestMethodNameFromFifthIndex() = getTestMethodNameWithStackWalker(INDEX_FIVE)

internal fun getTestMethodNameFromSixthIndex() = getTestMethodNameWithStackWalker(INDEX_SIX)

internal fun getTestMethodNameFromSeventhIndex() = getTestMethodNameWithStackWalker(INDEX_SEVEN)

private fun getTestMethodNameWithStackWalker(index: Int): String =
    StackWalker.getInstance()
        .walk {
            it.skip(index.toLong() - 1).findFirst().get()
        }
        .methodName

@Deprecated(
    message = "Using StackWalker",
    replaceWith = ReplaceWith("getTestMethodNameWithStackWalker"),
)
@Suppress("UnusedPrivateMember")
private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
