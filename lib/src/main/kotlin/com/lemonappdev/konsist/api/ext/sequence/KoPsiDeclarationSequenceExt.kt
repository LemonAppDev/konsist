package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration

/**
 * Sequence containing declarations that have KDoc.
 *
 * @return A sequence containing declarations with KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withKDoc(): Sequence<T> = filter { it.hasKDoc() }

/**
 * Sequence containing declarations that don't have the KDoc.
 *
 * @return A sequence containing declarations without KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutKDoc(): Sequence<T> = filterNot { it.hasKDoc() }

/**
 * Sequence containing declarations that have valid KDoc.
 *
 * @param verifyDescription `false` to not verify a description in the KDoc. By default, `true`.
 * @param verifyParamTag `true` to verify param tags in the KDoc. By default, `false`.
 * @param verifyReturnTag `true` to verify a return tag in the KDoc. By default, `false`.
 * @param verifyConstructorTag `true` to verify a constructor tag in the KDoc. By default, `false`.
 * @param verifyReceiverTag `true` to verify a receiver tag in the KDoc. By default, `false`.
 * @param verifyPropertyTag `true` to verify a property tag in the KDoc. By default, `false`.
 * @param verifyThrowsTag `true` to verify throws tags in the KDoc. By default, `false`.
 * @param verifyExceptionTag `true` to verify exception tags in the KDoc. By default, `false`.
 * @param verifySampleTag `true` to verify sample tags in the KDoc. By default, `false`.
 * @param verifySeeTag `true` to verify see tags in the KDoc. By default, `false`.
 * @param verifyAuthorTag `true` to verify author tags in the KDoc. By default, `false`.
 * @param verifySinceTag `true` to verify since tags in the KDoc. By default, `false`.
 * @param verifySuppressTag `true` to verify suppress tags in the KDoc. By default, `false`.
 * @param verifyVersionTag `true` to verify version tags in the KDoc. By default, `false`.
 * @param verifyPropertySetterTag `true` to verify property setter tags in the KDoc. By default, `false`.
 * @param verifyPropertyGetterTag `true` to verify property getter tags in the KDoc. By default, `false`.
 * @return A sequence containing declarations with a valid KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withValidKDoc(
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
): Sequence<T> = filter {
    it.hasValidKDoc(
        verifyDescription,
        verifyParamTag,
        verifyReturnTag,
        verifyConstructorTag,
        verifyReceiverTag,
        verifyPropertyTag,
        verifyThrowsTag,
        verifyExceptionTag,
        verifySampleTag,
        verifySeeTag,
        verifyAuthorTag,
        verifySinceTag,
        verifySuppressTag,
        verifyVersionTag,
        verifyPropertySetterTag,
        verifyPropertyGetterTag,
    )
}

/**
 * Sequence containing declarations that don't have the valid KDoc.
 *
 * @param verifyDescription `false` to not verify a description in the KDoc. By default, `true`.
 * @param verifyParamTag `true` to verify param tags in the KDoc. By default, `false`.
 * @param verifyReturnTag `true` to verify a return tag in the KDoc. By default, `false`.
 * @param verifyConstructorTag `true` to verify a constructor tag in the KDoc. By default, `false`.
 * @param verifyReceiverTag `true` to verify a receiver tag in the KDoc. By default, `false`.
 * @param verifyPropertyTag `true` to verify a property tag in the KDoc. By default, `false`.
 * @param verifyThrowsTag `true` to verify throws tags in the KDoc. By default, `false`.
 * @param verifyExceptionTag `true` to verify exception tags in the KDoc. By default, `false`.
 * @param verifySampleTag `true` to verify sample tags in the KDoc. By default, `false`.
 * @param verifySeeTag `true` to verify see tags in the KDoc. By default, `false`.
 * @param verifyAuthorTag `true` to verify author tags in the KDoc. By default, `false`.
 * @param verifySinceTag `true` to verify since tags in the KDoc. By default, `false`.
 * @param verifySuppressTag `true` to verify suppress tags in the KDoc. By default, `false`.
 * @param verifyVersionTag `true` to verify version tags in the KDoc. By default, `false`.
 * @param verifyPropertySetterTag `true` to verify property setter tags in the KDoc. By default, `false`.
 * @param verifyPropertyGetterTag `true` to verify property getter tags in the KDoc. By default, `false`.
 * @return A sequence containing declarations without a valid KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutValidKDoc(
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
): Sequence<T> = filterNot {
    it.hasValidKDoc(
        verifyDescription,
        verifyParamTag,
        verifyReturnTag,
        verifyConstructorTag,
        verifyReceiverTag,
        verifyPropertyTag,
        verifyThrowsTag,
        verifyExceptionTag,
        verifySampleTag,
        verifySeeTag,
        verifyAuthorTag,
        verifySinceTag,
        verifySuppressTag,
        verifyVersionTag,
        verifyPropertySetterTag,
        verifyPropertyGetterTag,
    )
}

/**
 * Sequence containing declarations that have KDoc tags.
 *
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with the specified KDoc tags
 */
fun <T : KoPsiDeclaration> Sequence<T>.withKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that have at least one of the specified KDoc tags.
 *
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with at least one of the specified KDoc tags.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withSomeKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter {
    tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations that don't have the KDoc tags.
 *
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A sequence containing declarations without the specified KDoc tags.
 *
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutKDocWithTags(vararg tags: KoKDocTag): Sequence<T> =
    filterNot { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that have file path.
 *
 * @param paths The paths to include.
 * @param absolutePath Determines whether the file paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that reside in any of the specified file paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withFilePath(vararg paths: String, absolutePath: Boolean = false): Sequence<T> = filter {
    paths.any { path -> it.resideInFilePath(path, absolutePath) }
}

/**
 * Sequence containing declarations that don't have file path.
 *
 * @param paths The paths to exclude.
 * @param absolutePath Determines whether the file paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that don't reside in any of the specified file paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutFilePath(vararg paths: String, absolutePath: Boolean = false): Sequence<T> = filter {
    paths.none { path -> it.resideInFilePath(path, absolutePath) }
}

/**
 * Sequence containing declarations that have absolute file path.
 *
 * @param paths The absolute paths to include.
 * @return A sequence containing declarations that reside in any of the specified absolute paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withAbsoluteFilePath(vararg paths: String): Sequence<T> = withFilePath(*paths, absolutePath = true)

/**
 * Sequence containing declarations that don't have absolute file path.
 *
 * @param paths The absolute paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified absolute paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutAbsoluteFilePath(vararg paths: String): Sequence<T> =
    withoutFilePath(*paths, absolutePath = true)

/**
 * Sequence containing declarations that have project file path.
 *
 * @param paths The project paths to include.
 * @return A sequence containing declarations that reside in any of the specified project paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withProjectFilePath(vararg paths: String): Sequence<T> = withFilePath(*paths, absolutePath = false)

/**
 * Sequence containing declarations that don't have project file path.
 *
 * @param paths The project paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified project paths.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutProjectFilePath(vararg paths: String): Sequence<T> =
    withoutFilePath(*paths, absolutePath = false)
