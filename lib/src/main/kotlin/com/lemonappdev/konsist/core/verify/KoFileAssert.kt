package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException

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

        checkIfLocalListIsEmpty(localList, "File", getTestMethodNameFromFourthIndex())

        val notSuppressedFiles = localList
            .filterNot { checkIfSuppressed(it, getTestMethodNameFromFifthIndex(), "@file:Suppress(") }

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
