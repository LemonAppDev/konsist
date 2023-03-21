package com.lemon.konsist.core.assertion.check

import com.lemon.konsist.core.declaration.KoBaseDeclaration
import com.lemon.konsist.exception.KonsistAssertionFailed
import com.lemon.konsist.exception.KonsistPreconditionFailed

fun <E : KoBaseDeclaration?> Collection<E>.check(function: (E) -> Boolean) {
    val localNotNull = this.filterNotNull()

    if (localNotNull.isEmpty()) {
        throw KonsistPreconditionFailed("Check failed. No declarations found")
    }

    val allChecksPassed = localNotNull.all { function(it) }

    if (!allChecksPassed) {
        val failedDeclarations = localNotNull.filter { !function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailed(message)
    }
}

fun <E : KoBaseDeclaration?> Collection<E>.checkNot(function: (E) -> Boolean) {
    val localNotNull = this.filterNotNull()

    if (localNotNull.isEmpty()) {
        throw KonsistPreconditionFailed("Check failed. No declarations found")
    }

    val noneChecksPassed = localNotNull.none { function(it) }

    if (!noneChecksPassed) {
        val failedDeclarations = localNotNull.filter { function(it) }

        val message = failedDeclarations.joinToString("\n") { it.textWithLocation }
        throw KonsistAssertionFailed(message)
    }
}
