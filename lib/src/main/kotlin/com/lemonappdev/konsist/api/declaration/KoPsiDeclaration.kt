package com.lemonappdev.konsist.api.declaration

/**
 * Base interface for all declarations.
 */
interface KoPsiDeclaration {
    /**
     * File path of the declaration.
     */
    val filePath: String

    /**
     * Roo project file path of the declaration.
     */
    val rootProjectFilePath: String

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

    /**
     * Documentation pf the declaration.
     */
    val kDoc: KoKDocDeclaration?

    /**
     * Whether the declaration has kDoc.
     */
    fun hasKDoc(): Boolean

    /**
     * Whether the declaration has kDoc with the given requirements.
     */
    fun hasValidKDoc(
        verifyDescription: Boolean = true,
        verifyParamTag: Boolean = false,
        verifyReturnTag: Boolean = false,
        verifyConstructorTag: Boolean = false,
        verifyReceiverTag: Boolean = false,
        verifyPropertyTag: Boolean = false,
        verifyThrowsTag: Boolean = false,
        verifyExceptionTag: Boolean = false,
        verifySampleTag: Boolean = false,
        verifySeeTag: Boolean = false,
        verifyAuthorTag: Boolean = false,
        verifySinceTag: Boolean = false,
        verifySuppressTag: Boolean = false,
        verifyVersionTag: Boolean = false,
        verifyPropertySetterTag: Boolean = false,
        verifyPropertyGetterTag: Boolean = false,
    ): Boolean

    /**
     * Whatever declaration reside in file path.
     */
    fun resideInFilePath(path: String): Boolean

    /**
     * Whatever declaration reside in project file path.
     */
    fun resideInProjectFilePath(path: String): Boolean

    /**
     * Print declaration.
     */
    fun print(): Unit

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    override fun toString(): String
}
