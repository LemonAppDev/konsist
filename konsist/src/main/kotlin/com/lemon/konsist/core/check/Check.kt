package com.lemon.konsist.core.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.core.declaration.KoDeclaration
import com.lemon.konsist.core.declaration.KoNamedDeclaration
import com.lemon.konsist.exception.KoCheckFailedException
import com.lemon.konsist.exception.KoException
import com.lemon.konsist.exception.KoInternalException
import com.lemon.konsist.exception.KoPreconditionFailedException

fun <E : KoBaseDeclaration> Sequence<E>.check(function: (E) -> Boolean) {
    check(function, true)
}

fun <E : KoBaseDeclaration> Sequence<E>.checkNot(function: (E) -> Boolean) {
    check(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseDeclaration> Sequence<E>.check(function: (E) -> Boolean, positiveCheck: Boolean) {
    var lastDeclaration: KoBaseDeclaration? = null

    try {
        val localList = this.toList()

        if (localList.isEmpty()) {
            val checkMethodName = Thread.currentThread().stackTrace[2].methodName
            throw KoPreconditionFailedException(
                "Declaration list is empty. Please make sure that list of declarations contain items " +
                    "before calling '$checkMethodName' method.",
            )
        }

        val result = localList.groupBy {
            lastDeclaration = it as? KoDeclaration
            function(it)
        }

        val allChecksPassed = (result[positiveCheck]?.size ?: 0) == localList.size

        if (!allChecksPassed) {
            val failedDeclarations = result[!positiveCheck] ?: emptyList()
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message, e, lastDeclaration)
    }
}

private fun <E : KoBaseDeclaration> getCheckFailedMessage(failedDeclarations: List<E>): String {
    val testMethodName = Thread.currentThread().stackTrace[4].methodName
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = if (it is KoNamedDeclaration) it.name else ""
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.location} ($name $declarationType)"
    }

    return "Check '$testMethodName' has failed. Invalid declarations:\n$failedDeclarationsMessage"
}
