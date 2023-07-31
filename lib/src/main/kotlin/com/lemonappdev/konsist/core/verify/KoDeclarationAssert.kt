package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException

fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoBaseProvider> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastDeclaration: KoBaseProvider? = null

    try {
        val localList = this.toList()

        checkIfLocalListIsEmpty(localList, "Declaration", getTestMethodNameFromFourthIndex())

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(localList)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it)
        }

        getResult(notSuppressedDeclarations, result, positiveCheck, getTestMethodNameFromFifthIndex())
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

private fun <E : KoBaseProvider> checkIfAnnotatedWithSuppress(localList: List<E>): List<E> {
    // In this declarations structure test name is at index 6
    // We pass this name to checkIfSuppressed() because when declarations are nested, this index is changing
    val testMethodName = getTestMethodNameFromSixthIndex()
    val declarations: MutableMap<E, Boolean> = mutableMapOf()

    // First we need to exclude (if exist) file suppress test annotation
    localList
        .filterNot {
            it is KoAnnotationDeclaration &&
                (
                    it.name == "Suppress" &&
                        it.text.contains("\"konsist.$testMethodName\"") ||
                        it.text.contains("\"$testMethodName\"")
                    )
        }
        .forEach { declarations[it] = checkIfSuppressed(it, testMethodName) }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfSuppressed(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoAnnotationProvider) {
        checkIfSuppressed(declaration, testMethodName, "@Suppress(") || checkParentAndSuppress(declaration, testMethodName)
    } else {
        checkParentAndSuppress(declaration, testMethodName)
    }

private fun checkParentAndSuppress(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoParentProvider && declaration.parent != null) {
        declaration.parent?.let { checkIfSuppressed(it, testMethodName) } ?: false
    } else {
        checkIfSuppressed((declaration as KoContainingFileProvider).containingFile, testMethodName, "@file:Suppress(")
    }
