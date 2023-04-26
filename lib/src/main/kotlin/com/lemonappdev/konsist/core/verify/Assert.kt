package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunction
import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

fun <E : KoBaseDeclaration> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoBaseDeclaration> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseDeclaration> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastDeclaration: KoBaseDeclaration? = null

    try {
        val localList = this.toList()

        if (localList.isEmpty()) {
            val checkMethodName = Thread.currentThread().stackTrace[2].methodName
            throw KoPreconditionFailedException(
                "Declaration list is empty. Please make sure that list of declarations contain items " +
                    "before calling '$checkMethodName' method.",
            )
        }

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(localList)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it)
        }

        val allChecksPassed = (result[positiveCheck]?.size ?: 0) == notSuppressedDeclarations.size

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

private fun <E : KoBaseDeclaration> getCheckFailedMessage(failedDeclarations: List<E>): String {
    val testMethodName = Thread.currentThread().stackTrace[4].methodName
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = if (it is KoNamedDeclaration) it.name else ""
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.location} ($name $declarationType)"
    }

    return "Assert '$testMethodName' has failed. Invalid declarations:\n$failedDeclarationsMessage"
}

private fun <E : KoBaseDeclaration> checkIfAnnotatedWithSuppress(localList: List<E>): List<E> {
    val testName = Thread.currentThread().stackTrace[4].methodName

    val declarationsWithoutAnnotations = localList
        .filterNot { (it as KoDeclaration).hasAnnotations("Suppress(\"konsist.$testName\")") }
        .filterNot { (it as KoDeclaration).hasAnnotations("Suppress(\"$testName\")") }

    val notTopLevelDeclarations = declarationsWithoutAnnotations.filter { !(it as KoDeclaration).isTopLevel() }

    val withoutAnnotationFromTopLevelDeclaration = notTopLevelDeclarations.map {
        val allDeclarations = it
            .containingFile
            .declarations(includeNested = true, includeLocal = true)

        val functions = allDeclarations
            .filterIsInstance<KoFunction>()
            .filter { function -> function.containsLocalDeclarations((it as KoFunction).name) }
            .toMutableList()

        val others = allDeclarations
            .toMutableList()
            .also { declarations ->
                declarations.removeIf { declaration -> declaration is KoFunction }
            }
            .filter { declaration ->
                (declaration as KoComplexDeclaration).containsDeclarations((it as KoDeclaration).name, includeNested = true) ||
                    declaration.containsFunction((it as KoDeclaration).name, includeNested = true, includeLocal = true)
            }

        if (
            (functions + others).none { declaration ->
                declaration.hasAnnotations("Suppress(\"konsist.$testName\")") ||
                    declaration.hasAnnotations("Suppress(\"$testName\")")
            }
        ) {
            it
        } else {
            null
        }
    }.filterNotNull()

    return ((declarationsWithoutAnnotations - notTopLevelDeclarations.toSet()) + withoutAnnotationFromTopLevelDeclaration)
        .filterNot { it.containingFile.hasAnnotations("file:Suppress(\"konsist.$testName\")") }
        .filterNot { it.containingFile.hasAnnotations("file:Suppress(\"$testName\")") }
}
