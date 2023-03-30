package com.lemon.konsist.core.assertion.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.exception.KonsistAssertionFailedException
import com.lemon.konsist.exception.KonsistPreconditionFailedException

fun <E : KoBaseDeclaration?> Collection<E>.check(function: (E) -> Boolean) {
    val localNotNull = this.filterNotNull()

    if (localNotNull.isEmpty()) {
        throw KonsistPreconditionFailedException("Check failed. Collection contains no elements.")
    }

    val allChecksPassed = localNotNull.all { function(it) }

    if (!allChecksPassed) {
        val failedDeclarations = localNotNull.filter { !function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailedException(message)
    }
}

fun <E : KoBaseDeclaration?> Collection<E>.checkNot(function: (E) -> Boolean) {
    val localNotNull = this.filterNotNull()

    if (localNotNull.isEmpty()) {
        throw KonsistPreconditionFailedException("Check failed. Collection contains no elements.")
    }

    val noneChecksPassed = localNotNull.none { function(it) }

    if (!noneChecksPassed) {
        val failedDeclarations = localNotNull.filter { function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailedException(message)
    }
}
