package com.lemon.konsist.core.assertion.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.core.declaration.KoNamedDeclaration
import com.lemon.konsist.exception.KoCheckFailedException
import com.lemon.konsist.exception.KoInternalException
import com.lemon.konsist.exception.KoPreconditionFailedException

private const val EMPTY_DECLARATION_LIST_MESSAGE =
    "Declaration list is empty. Please make sure that list of declarations contain items before calling “check” method."

private const val CHECK_FAILED_MESSAGE_PREFIX = "Check failed for the following declarations:"

fun <E : KoBaseDeclaration?> Collection<E>.check(function: (E) -> Boolean) {
    try {
        val localNotNull = this.filterNotNull()

        if (localNotNull.isEmpty()) {
            throw KoPreconditionFailedException(EMPTY_DECLARATION_LIST_MESSAGE)
        }

        val allChecksPassed = localNotNull.all { function(it) }

        if (!allChecksPassed) {
            val failedDeclarations = localNotNull.filter { !function(it) }
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: Exception) {
        if (e is KoCheckFailedException) {
            throw e
        } else {
            throw KoInternalException(e.message, e)
        }
    }
}

fun <E : KoBaseDeclaration?> Collection<E>.checkNot(function: (E) -> Boolean) {
    try {
        val localNotNull = this.filterNotNull()

        if (localNotNull.isEmpty()) {
            throw KoPreconditionFailedException(EMPTY_DECLARATION_LIST_MESSAGE)
        }

        val noneChecksPassed = localNotNull.none { function(it) }

        if (!noneChecksPassed) {
            val failedDeclarations = localNotNull.filter { function(it) }
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: Exception) {
        if (e is KoCheckFailedException) {
            throw e
        } else {
            throw KoInternalException(e.message, e)
        }
    }
}

private fun getCheckFailedMessage(failedDeclarations: List<KoBaseDeclaration>): String {
    val testMethodName = Thread.currentThread().stackTrace[3].methodName
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = if (it is KoNamedDeclaration) it.name else ""
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.location} ($name $declarationType)"
    }
    return "Check '$testMethodName' failed for the following declarations:\n$failedDeclarationsMessage"
}
