package com.lemon.konsist.core.assertion.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.exception.KonsistAssertionFailed
import com.lemon.konsist.exception.KonsistPreconditionFailed
import java.util.random.RandomGeneratorFactory.all

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
