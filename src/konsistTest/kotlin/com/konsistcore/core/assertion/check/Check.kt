package com.konsistcore.core.assertion.check

import com.konsistcore.core.declaration.KoBaseDeclaration
import com.konsistcore.exception.KonsistAssertionFailed
import com.konsistcore.exception.KonsistPreconditionFailed

fun <E : KoBaseDeclaration> Collection<E>.check(function: (E) -> Boolean) {
    if (isEmpty()) {
        throw KonsistPreconditionFailed("No declarations found to check")
    }

    val allChecksPassed = all { function(it) }

    if (!allChecksPassed) {
        val failedDeclarations = filter { !function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailed(message)
    }
}

fun <E : KoBaseDeclaration> Collection<E>.checkNot(function: (E) -> Boolean) {
    if (isEmpty()) {
        throw KonsistPreconditionFailed("No declarations found to check")
    }

    val noneChecksPassed = none { function(it) }

    if (!noneChecksPassed) {
        val failedDeclarations = filter { function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailed(message)
    }
}
