package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.ir.types.IdSignatureValues.result

fun <E : KoBaseProvider> List<E>.assert(function: (E) -> Boolean?) {
    assert(function, positiveCheck = true, isList = true)
}

fun <E : KoBaseProvider> List<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, positiveCheck = false, isList = true)
}

fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoBaseProvider> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseProvider> List<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean, isList: Boolean) {
    var lastDeclaration: KoBaseProvider? = null

    try {
        if (isList) {
            checkIfLocalListIsEmpty(this, "Declaration", getTestMethodNameFromFourthIndex())
        } else {
            checkIfLocalListIsEmpty(this, "Declaration", getTestMethodNameFromFifthIndex())
        }

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(this, isList)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it)
        }

        if (isList) {
            getResult(notSuppressedDeclarations, result, positiveCheck, getTestMethodNameFromFifthIndex())
        } else {
            getResult(notSuppressedDeclarations, result, positiveCheck, getTestMethodNameFromSixthIndex())
        }

        getResult(notSuppressedDeclarations, result, positiveCheck, getTestMethodNameFromFifthIndex())
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

private fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    this.toList().assert(function, positiveCheck, false)
}

private fun <E : KoBaseProvider> checkIfAnnotatedWithSuppress(localList: List<E>, isList: Boolean): List<E> {
    // In this declarations structure test name is at index 6
    // We pass this name to checkIfSuppressed() because when declarations are nested, this index is changing
    val testMethodName = if (isList) {
        getTestMethodNameFromSixthIndex()
    } else {
        getTestMethodNameFromSeventhIndex()
    }
    val declarations: MutableMap<E, Boolean> = mutableMapOf()

    // First we need to exclude (if exist) file suppress test annotation
    localList
        .filterNot {
            it is KoAnnotationDeclaration &&
                (
                    it.text.endsWith("Suppress(\"konsist.$testMethodName\")") ||
                        it.text.endsWith("Suppress(\"$testMethodName\")")
                    )
        }
        .forEach { declarations[it] = checkIfSuppressed(it, testMethodName) }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfSuppressed(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoAnnotationProvider) {
        val annotationParameter = declaration
            .annotations
            .firstOrNull { it.name == "Suppress" }
            ?.text
            ?.removePrefix("@Suppress(\"")
            ?.removeSuffix("\")")
        if (annotationParameter == testMethodName || annotationParameter == "konsist.$testMethodName") {
            true
        } else {
            checkParentAndSuppress(declaration, testMethodName)
        }
    } else {
        checkParentAndSuppress(declaration, testMethodName)
    }

private fun checkParentAndSuppress(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoParentProvider && declaration.parent != null) {
        declaration.parent?.let { checkIfSuppressed(it, testMethodName) } ?: false
    } else if (fileAnnotationParameter((declaration as KoContainingFileProvider).containingFile) == testMethodName) {
        true
    } else {
        fileAnnotationParameter(declaration.containingFile) == "konsist.$testMethodName"
    }

private fun fileAnnotationParameter(file: KoFile) = file
    .annotations
    .firstOrNull { it.name == "Suppress" }
    ?.text
    ?.removePrefix("@file:Suppress(\"")
    ?.removeSuffix("\")")
