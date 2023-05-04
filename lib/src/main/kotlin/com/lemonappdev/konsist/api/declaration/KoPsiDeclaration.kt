package com.lemonappdev.konsist.api.declaration

/**
 * Base interface for all declarations.
 */
interface KoPsiDeclaration {
    /**
     * File path of the declaration.
     */
    val filePath: String
    val projectFilePath: String

    /**
     * Location of the declaration containing the file path, line and column.
     */
    val location: String

    /**
     * Text of the declaration
     */
    val text: String

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    val locationWithText: String
    fun resideInFilePath(text: String): Boolean
    fun resideInProjectFilePath(text: String): Boolean
    fun print()

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    override fun toString(): String
}
