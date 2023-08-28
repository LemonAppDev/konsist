package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about return statements.
 */
interface KoReturnStatementsProvider : KoBaseProvider {
    /**
     * The number of return statements present in the declaration.
     */
    val numReturnStatements: Int
}
