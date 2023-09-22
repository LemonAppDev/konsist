package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal fun <E : KoBaseProvider> List<E?>.assert(
    strict: Boolean,
    additionalMessage: String?,
    function: (E) -> Boolean?,
    positiveCheck: Boolean,
) {
    var lastDeclaration: KoBaseProvider? = null

    try {
        val fifthIndexMethodName = getTestMethodNameFromFifthIndex()

        val testMethodName = if (fifthIndexMethodName.contains("\$default")) {
            getTestMethodNameFromSixthIndex()
        } else {
            fifthIndexMethodName
        }

        val assertMethodName = getTestMethodNameFromFourthIndex()

        if (strict) {
            checkIfLocalListIsEmpty(this, assertMethodName)
            checkIfLocalListHasOnlyNullElements(this, assertMethodName)
        }

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(this.filterNotNull(), testMethodName)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it) ?: positiveCheck
        }

        getResult(notSuppressedDeclarations, result, positiveCheck, testMethodName, additionalMessage)
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

@Deprecated("Will be removed in v1.0.0", ReplaceWith("assert"))
internal fun <E : KoBaseProvider> List<E>.assert(
    additionalMessage: String? = null,
    function: (E) -> Boolean?,
    positiveCheck: Boolean,
) {
    var lastDeclaration: KoBaseProvider? = null

    try {
        val testMethodName = if (additionalMessage != null) {
            getTestMethodNameFromFifthIndex()
        } else {
            getTestMethodNameFromSixthIndex()
        }

        checkIfLocalListIsEmpty(this, getTestMethodNameFromFourthIndex())

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(this, testMethodName)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it) ?: positiveCheck
        }

        getResult(notSuppressedDeclarations, result, positiveCheck, testMethodName, additionalMessage)
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

fun checkIfLocalListHasOnlyNullElements(localList: List<*>, testMethodName: String) {
    val hasOnlyNUllElements = localList.all { it == null }
    if (hasOnlyNUllElements && (localList.size > 1)) {
        throw KoPreconditionFailedException(
            "Declaration list contains only null elements. Please make sure that list of declarations contain items " +
                "before calling the '$testMethodName' method.",
        )
    } else if (hasOnlyNUllElements && (localList.size == 1)) {
        throw KoPreconditionFailedException(
            "Method '$testMethodName' was called on a null value. Please ensure that the declaration is not null before " +
                "calling this method.",
        )
    }
}

fun checkIfLocalListIsEmpty(localList: List<*>, testMethodName: String) {
    if (localList.isEmpty()) {
        throw KoPreconditionFailedException(
            "Declaration list is empty. Please make sure that list of declarations contain items " +
                "before calling the '$testMethodName' method.",
        )
    }
}

private fun <E : KoBaseProvider> checkIfAnnotatedWithSuppress(localList: List<E>, testMethodName: String): List<E> {
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
        .forEach { declarations[it] = checkIfDeclarationIsAnnotatedWithSuppress(it, testMethodName) }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfDeclarationIsAnnotatedWithSuppress(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoAnnotationProvider) {
        checkIfSuppressed(declaration, testMethodName) || checkIfParentIsAnnotatedWithSuppress(
            declaration,
            testMethodName,
        )
    } else {
        checkIfParentIsAnnotatedWithSuppress(declaration, testMethodName)
    }

private fun checkIfParentIsAnnotatedWithSuppress(declaration: KoBaseProvider, testMethodName: String): Boolean =
    if (declaration is KoContainingDeclarationProvider) {
        declaration.containingDeclaration?.let { checkIfDeclarationIsAnnotatedWithSuppress(it, testMethodName) }
            ?: false
    } else {
        false
    }

private fun checkIfSuppressed(item: KoAnnotationProvider, testMethodName: String): Boolean {
    val annotationParameter = item
        .annotations
        .firstOrNull { it.name == "Suppress" }
        ?.text
        ?.removePrefix("@file:Suppress(")
        ?.removePrefix("@Suppress(")
        ?.substringBeforeLast(")")
        ?.split(",")
        ?.map { it.trim() }
        ?.map { it.removePrefix("\"") }
        ?.map { it.removeSuffix("\"") }
        ?: emptyList()

    return annotationParameter.any { it == testMethodName } || annotationParameter.any { it == "konsist.$testMethodName" }
}

private fun getResult(
    items: List<*>,
    result: Map<Boolean, List<Any>>,
    positiveCheck: Boolean,
    testMethodName: String,
    additionalMessage: String?,
) {
    val allChecksPassed = (result[positiveCheck]?.size ?: 0) == items.size

    if (!allChecksPassed) {
        val failedItems = result[!positiveCheck] ?: emptyList()
        throw KoCheckFailedException(getCheckFailedMessage(failedItems, testMethodName, additionalMessage))
    }
}

private fun getCheckFailedMessage(failedItems: List<*>, testMethodName: String, additionalMessage: String?): String {
    var types = ""
    val failedDeclarationsMessage = failedItems.joinToString("\n") {
        val konsistDeclarationClassNamePrefix = "Ko"
        val konsistDeclarationClassNameSuffix = "Core"

        when (it) {
            is KoFileDeclaration -> {
                types = "files"
                val name = it.name
                val declarationType = it::class
                    .simpleName
                    ?.substringAfter(konsistDeclarationClassNamePrefix)
                    ?.substringBeforeLast(konsistDeclarationClassNameSuffix)

                "${it.path} ($name $declarationType)"
            }

            is KoBaseProvider -> {
                types = "declarations"
                val name = (it as? KoNameProvider)?.name
                val declarationType = it::class
                    .simpleName
                    ?.substringAfter(konsistDeclarationClassNamePrefix)
                    ?.substringBeforeLast(konsistDeclarationClassNameSuffix)

                "${(it as? KoLocationProvider)?.location} ($name $declarationType)"
            }

            else -> {
                ""
            }
        }
    }

    val customMessage = if (additionalMessage != null) "\n${additionalMessage}\n" else " "
    val times = if (failedItems.size == 1) "time" else "times"
    return "Assert '$testMethodName' was violated (${failedItems.size} $times).${customMessage}Invalid $types:\n$failedDeclarationsMessage"
}
