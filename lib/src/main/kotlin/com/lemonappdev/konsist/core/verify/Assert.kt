package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoNamedDeclarationImpl
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

fun <E : KoBaseDeclarationImpl> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoBaseDeclarationImpl> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseDeclarationImpl> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastDeclaration: KoBaseDeclarationImpl? = null

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
            lastDeclaration = it as? KoDeclarationImpl
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

private fun <E : KoBaseDeclarationImpl> getCheckFailedMessage(failedDeclarations: List<E>): String {
    val testMethodName = Thread.currentThread().stackTrace[4].methodName
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = if (it is KoNamedDeclarationImpl) it.name else ""
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.location} ($name $declarationType)"
    }

    return "Check '$testMethodName' has failed. Invalid declarations:\n$failedDeclarationsMessage"
}
