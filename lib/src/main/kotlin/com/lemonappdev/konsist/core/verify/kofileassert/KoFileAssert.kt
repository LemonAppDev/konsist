package com.lemonappdev.konsist.core.verify.kofileassert

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

fun <E : KoFile> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoFile> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoFile> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastFile: KoFile? = null

    try {
        val localList = this.toList()

        if (localList.isEmpty()) {
            val checkMethodName = Thread.currentThread().stackTrace[2].methodName
            throw KoPreconditionFailedException(
                "Files list is empty. Please make sure that list of files contain items " +
                        "before calling the '$checkMethodName' method.",
            )
        }

        val notSuppressedFiles = localList
            .filterNot { checkIfSuppressed(it, Thread.currentThread().stackTrace[4].methodName) }

        val result = notSuppressedFiles.groupBy {
            lastFile = it
            function(it)
        }

        val allChecksPassed = (result[positiveCheck]?.size ?: 0) == notSuppressedFiles.size

        if (!allChecksPassed) {
            val failedDeclarations = result[!positiveCheck] ?: emptyList()
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoException(e.message.orEmpty(), e)
    }
}

private fun <E : KoFile> getCheckFailedMessage(failedDeclarations: List<E>): String {
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = it.name
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.path} ($name $declarationType)"
    }

    return "Assert '${getTestMethodName()}' has failed. Invalid declarations (${failedDeclarations.size}):\n$failedDeclarationsMessage"
}

/**
 * In this call stack hierarchy test name is at index 5.
 */
private fun getTestMethodName(): String? {
    val stackTraceIndexOfTestMethod = 5
    return Thread.currentThread().stackTrace[stackTraceIndexOfTestMethod].methodName
}

private fun checkIfSuppressed(file: KoFile, testMethodName: String): Boolean {
    val annotationParameter = (file as KoFileImpl)
        .annotations
        .firstOrNull { it.name == "Suppress" }
        ?.text
        ?.removePrefix("@file:Suppress(\"")
        ?.removeSuffix("\")")

    return annotationParameter == testMethodName || annotationParameter == "konsist.$testMethodName"
}
