package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SIX = 6
private const val INDEX_SEVEN = 7

/**
 * In this call stack hierarchy test name is at index 4.
 */
internal fun getTestMethodNameFromFourthIndex() = getTestMethodNameWithStackWalker(INDEX_FOUR)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromFifthIndex() = getTestMethodNameWithStackWalker(INDEX_FIVE)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromSixthIndex() = getTestMethodNameWithStackWalker(INDEX_SIX)

/**
 * In this call stack hierarchy test name is at index 7.
 */
internal fun getTestMethodNameFromSeventhIndex() = getTestMethodNameWithStackWalker(INDEX_SEVEN)

/**
 * Use StalkWalker to get the testMethodName
 */
private fun getTestMethodNameWithStackWalker(index: Int): String =
    StackWalker.getInstance()
        .walk {
            it.skip(index.toLong() - 1).findFirst().get()
        }
        .methodName

@Deprecated(message = "Using StackWalker",
    replaceWith = ReplaceWith("getTestMethodNameWithStackWalker")
)
@Suppress("UnusedPrivateMember")
private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
