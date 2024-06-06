package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal fun <E : KoBaseProvider> List<E?>.assert(
    strict: Boolean,
    additionalMessage: String?,
    testName: String?,
    function: (E) -> Boolean?,
    positiveCheck: Boolean,
) {
    var lastDeclaration: KoBaseProvider? = null

    try {
        val fifthIndexMethodName = getTestMethodNameFromFifthIndex()

        val testMethodName =
            testName
                ?: if (fifthIndexMethodName.contains("\$default")) {
                    getTestMethodNameFromSixthIndex()
                } else {
                    fifthIndexMethodName
                }

        val assertMethodName = getTestMethodNameFromFourthIndex()

        if (strict) {
            checkIfLocalListIsEmpty(this, assertMethodName)
            checkIfLocalListHasOnlyNullElements(this, assertMethodName)
        }

        val localSuppressName = testName ?: testMethodName

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(this.filterNotNull(), localSuppressName)

        val result =
            notSuppressedDeclarations.groupBy {
                lastDeclaration = it
                function(it) ?: positiveCheck
            }

        getResult(notSuppressedDeclarations, result, positiveCheck, localSuppressName, additionalMessage)
    } catch (e: KoException) {
        throw e
    } catch (
        @Suppress("detekt.TooGenericExceptionCaught") e: Exception,
    ) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

internal fun <E : KoBaseProvider> List<E?>.assert(
    strict: Boolean,
    additionalMessage: String?,
    testName: String?,
    isEmptyOrNull: Boolean,
    onSingleElement: Boolean,
) {
    try {
        val fifthIndexMethodName = getTestMethodNameFromFifthIndex()

        val testMethodName =
            testName
                ?: if (fifthIndexMethodName.contains("\$default")) {
                    getTestMethodNameFromSixthIndex()
                } else {
                    fifthIndexMethodName
                }

        val localSuppressName = testName ?: testMethodName

        val declarationWithoutNull = filterNotNull()

        val suppressedDeclarations =
            declarationWithoutNull - checkIfAnnotatedWithSuppress(declarationWithoutNull, localSuppressName).toSet()

        val notSuppressedDeclarations = this - suppressedDeclarations.toSet()

        if (!onSingleElement) {
            val items = if (strict) notSuppressedDeclarations.filterNotNull() else notSuppressedDeclarations

            getEmptyResult(items, additionalMessage, isEmptyOrNull, testMethodName)
        } else {
            getNullResult(notSuppressedDeclarations.firstOrNull(), additionalMessage, isEmptyOrNull, testMethodName)
        }
    } catch (e: KoException) {
        throw e
    } catch (
        @Suppress("detekt.TooGenericExceptionCaught") e: Exception,
    ) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

fun checkIfLocalListHasOnlyNullElements(
    localList: List<*>,
    testMethodName: String,
) {
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

fun checkIfLocalListIsEmpty(
    localList: List<*>,
    testMethodName: String,
) {
    if (localList.isEmpty()) {
        throw KoPreconditionFailedException(
            "Declaration list is empty. Please make sure that list of declarations contain items " +
                "before calling the '$testMethodName' method.",
        )
    }
}

private fun <E : KoBaseProvider> checkIfAnnotatedWithSuppress(
    localList: List<E>,
    suppressName: String,
): List<E> {
    val declarations: MutableMap<E, Boolean> = mutableMapOf()

    // First we need to exclude (if exist) file suppress test annotation
    localList
        .filterNot {
            it is KoAnnotationDeclaration &&
                (
                    it.name == "Suppress" &&
                        it.hasTextContaining("\"konsist.$suppressName\"") ||
                        it.hasTextContaining("\"$suppressName\"")
                )
        }
        .forEach { declarations[it] = checkIfDeclarationIsAnnotatedWithSuppress(it as KoBaseDeclaration, suppressName) }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfDeclarationIsAnnotatedWithSuppress(
    declaration: KoBaseDeclaration,
    testMethodName: String,
): Boolean =
    when (declaration) {
        is KoFileDeclaration -> {
            checkIfSuppressed(declaration, testMethodName)
        }

        is KoAnnotationProvider -> {
            checkIfSuppressed(declaration, testMethodName) ||
                checkIfParentIsAnnotatedWithSuppress(declaration, testMethodName)
        }

        else -> {
            checkIfParentIsAnnotatedWithSuppress(declaration, testMethodName)
        }
    }

private fun checkIfParentIsAnnotatedWithSuppress(
    declaration: KoBaseDeclaration,
    testMethodName: String,
): Boolean =
    if (declaration is KoContainingDeclarationProvider) {
        checkIfDeclarationIsAnnotatedWithSuppress(declaration.containingDeclaration, testMethodName)
    } else {
        false
    }

private fun checkIfSuppressed(
    item: KoAnnotationProvider,
    testMethodName: String,
): Boolean {
    val annotationParameter =
        item
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
            .orEmpty()

    return annotationParameter.any { it == testMethodName } || annotationParameter.any { it == "konsist.$testMethodName" }
}

private fun getResult(
    items: List<*>,
    result: Map<Boolean, List<Any>>,
    positiveCheck: Boolean,
    testName: String,
    additionalMessage: String?,
): Unit {
    val allChecksPassed = (result[positiveCheck]?.size ?: 0) == items.size

    if (!allChecksPassed) {
        val failedItems = result[!positiveCheck].orEmpty()
        throw KoAssertionFailedException(getCheckFailedMessage(failedItems, testName, additionalMessage))
    }
}

private fun getCheckFailedMessage(
    failedItems: List<*>,
    testName: String,
    additionalMessage: String?,
): String {
    var types = ""
    val failedDeclarationsMessage =
        failedItems.joinToString("\n") {
            val konsistDeclarationClassNamePrefix = "Ko"
            val konsistDeclarationClassNameSuffix = "Core"

            when (it) {
                is KoFileDeclaration -> {
                    types = "files"
                    val name = it.name
                    val declarationType =
                        it::class
                            .simpleName
                            ?.substringAfter(konsistDeclarationClassNamePrefix)
                            ?.substringBeforeLast(konsistDeclarationClassNameSuffix)

                    "${it.path} ($name $declarationType)"
                }

                is KoBaseProvider -> {
                    types = "declarations"
                    val name = (it as? KoNameProvider)?.name
                    val declarationType =
                        it::class
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

    return "Assert '$testName' was violated (${failedItems.size} $times).${customMessage}Invalid $types:\n$failedDeclarationsMessage"
}

private fun getEmptyResult(
    items: List<*>,
    additionalMessage: String?,
    isEmpty: Boolean,
    testMethodName: String,
) {
    val itemsListIsEmpty = items.isEmpty()

    if (isEmpty != itemsListIsEmpty) {
        val negation = if (isEmpty) " not" else ""
        val values =
            if (isEmpty) {
                val nullCount = items.count { it == null }
                val nullValues =
                    if (nullCount == 1) {
                        "$nullCount null value"
                    } else if (nullCount > 1) {
                        "$nullCount null values"
                    } else {
                        ""
                    }
                val otherValues = items.filterNotNull().joinToString(",\n")

                var text = " It contains "
                if (nullValues.isNotEmpty()) text += nullValues
                if (nullValues.isNotEmpty() && otherValues.isNotEmpty()) text += " and "
                if (otherValues.isNotEmpty()) text += "values:\n$otherValues"

                "$text."
            } else {
                ""
            }
        val customMessage = if (additionalMessage != null) "\n${additionalMessage}\n" else " "

        val message = "Assert '$testMethodName' failed.${customMessage}Declaration list is$negation empty.$values"
        throw KoAssertionFailedException(message)
    }
}

private fun getNullResult(
    item: Any?,
    additionalMessage: String?,
    isNull: Boolean,
    testMethodName: String,
) {
    val itemIsNull = item == null

    if (isNull != itemIsNull) {
        val negation = if (isNull) " not" else ""
        val value = if (isNull) ": " + item.toString() else ""
        val customMessage = if (additionalMessage != null) "\n${additionalMessage}\n" else " "

        val message = "Assert `$testMethodName` failed.${customMessage}Declaration has$negation null value$value."
        throw KoAssertionFailedException(message)
    }
}
