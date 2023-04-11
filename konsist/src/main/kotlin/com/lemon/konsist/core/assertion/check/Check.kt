package com.lemon.konsist.core.assertion.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.core.declaration.KoDeclaration
import com.lemon.konsist.core.declaration.KoNamedDeclaration
import com.lemon.konsist.exception.KoCheckFailedException
import com.lemon.konsist.exception.KoInternalException
import com.lemon.konsist.exception.KoPreconditionFailedException

private const val EMPTY_DECLARATION_LIST_MESSAGE =
    "Declaration list is empty. Please make sure that list of declarations contain items before calling “check” method."

@Suppress("detekt.TooGenericExceptionCaught", "detekt.ThrowsCount")
fun <E : KoBaseDeclaration?> Collection<E>.check(function: (E) -> Boolean) {
    var lastDeclaration: KoBaseDeclaration? = null

    try {
        val localNotNull = this.filterNotNull()

        if (localNotNull.isEmpty()) {
            throw KoPreconditionFailedException(EMPTY_DECLARATION_LIST_MESSAGE)
        }

        val allChecksPassed = localNotNull.all {
            lastDeclaration = it as? KoDeclaration
            function(it)
        }

        if (!allChecksPassed) {
            val failedDeclarations = localNotNull.filter { !function(it) }
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: KoCheckFailedException) {
        throw e
    } catch (e: KoInternalException) {
        throw e
    } catch (e: Exception) {
        throw KoInternalException(e.message, e, lastDeclaration)
    }
}

@Suppress("detekt.TooGenericExceptionCaught", "detekt.ThrowsCount")
fun <E : KoBaseDeclaration?> Collection<E>.checkNot(function: (E) -> Boolean) {
    var lastDeclaration: KoBaseDeclaration? = null

    try {
        val localNotNull = this.filterNotNull()

        if (localNotNull.isEmpty()) {
            throw KoPreconditionFailedException(EMPTY_DECLARATION_LIST_MESSAGE)
        }

        val noneChecksPassed = localNotNull.none {
            lastDeclaration = it as? KoDeclaration
            function(it)
        }

        if (!noneChecksPassed) {
            val failedDeclarations = localNotNull.filter { function(it) }
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: KoCheckFailedException) {
        throw e
    } catch (e: KoInternalException) {
        throw e
    } catch (e: Exception) {
        throw KoInternalException(e.message, e, lastDeclaration)
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
