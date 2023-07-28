package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException

fun <E : KoFile> List<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoFile> List<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

fun <E : KoFile> Sequence<E>.assert(function: (E) -> Boolean?) {
    this.toList().assert(function, true)
}

fun <E : KoFile> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    this.toList().assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoFile> List<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastFile: KoFile? = null

    try {
        checkIfLocalListIsEmpty(this, "File", getTestMethodNameFromFourthIndex())

        val notSuppressedFiles = this
            .filterNot { checkIfSuppressed(it, getTestMethodNameFromFifthIndex()) }

        val result = notSuppressedFiles.groupBy {
            lastFile = it
            function(it)
        }

        getResult(notSuppressedFiles, result, positiveCheck, getTestMethodNameFromFifthIndex())
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastFile)
    }
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
