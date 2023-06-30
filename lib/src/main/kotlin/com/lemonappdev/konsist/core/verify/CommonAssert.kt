package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

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
 * In this call stack hierarchy test name is at index 6.
 */
internal fun getTestMethodNameFromSixthIndex() = getTestMethodName(INDEX_SIX)

/**
 * In this call stack hierarchy test name is at index 8.
 */
internal fun getTestMethodNameFromEighthIndex() = getTestMethodName(INDEX_EIGHT)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName

internal fun getResult(items: List<*>, result: Map<Boolean?, List<Any>>, positiveCheck: Boolean, testMethodName: String) {
    val allChecksPassed = (result[positiveCheck]?.size ?: 0) == items.size

    if (!allChecksPassed) {
        val failedItems = result[!positiveCheck] ?: emptyList()
        throw KoCheckFailedException(getCheckFailedMessage(failedItems, testMethodName))
    }
}

private fun getCheckFailedMessage(failedItems: List<*>, testMethodName: String): String {
    var types = ""
    val failedDeclarationsMessage = failedItems.joinToString("\n") {
        when (it) {
            is KoFile -> {
                types = "files"
                val name = it.name
                val konsistDeclarationClassNamePrefix = "Ko"
                val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

                "${it.path} ($name $declarationType)"
            }

            is KoBaseDeclaration -> {
                types = "declarations"
                val name = if (it is KoNamedDeclaration) it.name else ""
                val konsistDeclarationClassNamePrefix = "Ko"
                val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

                "${it.location} ($name $declarationType)"
            }

            else -> {
                ""
            }
        }
    }

    return "Assert '$testMethodName' has failed. Invalid $types (${failedItems.size}):\n$failedDeclarationsMessage"
}

internal fun checkIfLocalListIsEmpty(localList: List<*>, type: String, testMethodName: String) {
    if (localList.isEmpty()) {
        throw KoPreconditionFailedException(
            "$type list is empty. Please make sure that list of ${type.lowercase()}s contain items " +
                "before calling the '$testMethodName' method.",
        )
    }
}
