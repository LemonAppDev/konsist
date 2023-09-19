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

private fun checkIfLocalListIsEmpty(localList: List<*>, testMethodName: String) {
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
