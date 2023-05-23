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
     * Project file path of the declaration.
     */
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

    /**
     * Documentation pf the declaration.
     */
    val kDoc: KoKDocDeclaration?

    /**
     * Whether the declaration has kDoc.
     *
     * @return `true` if the declaration has kDoc, `false` otherwise.
     */
    fun hasKDoc(): Boolean

    /**
     * Whether the declaration has kDoc with the given requirements.
     *
     * @param verifyDescription -false to not verify a description in the KDoc. By default, true.
     * @param verifyParamTag - true to verify param tags in the KDoc. By default, false.
     * @param verifyReturnTag - true to verify a return tag in the KDoc. By default, false.
     * @param verifyConstructorTag - true to verify a constructor tag in the KDoc. By default, false.
     * @param verifyReceiverTag - true to verify a receiver tag in the KDoc. By default, false.
     * @param verifyPropertyTag - true to verify a property tag in the KDoc. By default, false.
     * @param verifyThrowsTag - true to verify throws tags in the KDoc. By default, false.
     * @param verifyExceptionTag - true to verify exception tags in the KDoc. By default, false.
     * @param verifySampleTag - true to verify sample tags in the KDoc. By default, false.
     * @param verifySeeTag - true to verify see tags in the KDoc. By default, false.
     * @param verifyAuthorTag - true to verify author tags in the KDoc. By default, false.
     * @param verifySinceTag - true to verify since tags in the KDoc. By default, false.
     * @param verifySuppressTag - true to verify suppress tags in the KDoc. By default, false.
     * @param verifyVersionTag - true to verify version tags in the KDoc. By default, false.
     * @param verifyPropertySetterTag - true to verify property setter tags in the KDoc. By default, false.
     * @param verifyPropertyGetterTag - true to verify property getter tags in the KDoc. By default, false.
     * @return `true` if the declaration has valid kDoc with the given requirements, `false` otherwise.
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
     *
     * @param path the file path to check.
     * @return `true` if the declaration resides in the specified file path, `false` otherwise.
     */
    fun resideInFilePath(path: String): Boolean

    /**
     * Whatever declaration reside in project file path.
     *
     * @param path the project file path to check.
     * @return `true` if the declaration resides in the specified project file path, `false` otherwise.
     */
    fun resideInProjectFilePath(path: String): Boolean

    /**
     * Print declaration.
     */
    fun print(): Unit

    /**
     * Text of the declaration with the location (file path, line and column).
     *
     * @return the text of the declaration with the location.
     */
    override fun toString(): String
}
