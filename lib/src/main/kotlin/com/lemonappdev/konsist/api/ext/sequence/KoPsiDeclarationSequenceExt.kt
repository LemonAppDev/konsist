package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration

/**
 * Sequence containing declarations that have KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withKDoc(): Sequence<T> = filter { it.hasKDoc() }

/**
 * Sequence containing declarations that don't have the KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutKDoc(): Sequence<T> = filterNot { it.hasKDoc() }

/**
 * Sequence containing declarations that have complete KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withCompleteKDoc(
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
    it.hasCompleteKDoc(
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
 * Sequence containing declarations that don't have the complete KDoc.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutCompleteKDoc(
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
    it.hasCompleteKDoc(
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
 */
fun <T : KoPsiDeclaration> Sequence<T>.withKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withSomeKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter {
    tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutKDocWithTags(vararg tags: KoKDocTag): Sequence<T> =
    filterNot { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that have file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withFilePath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInFilePath(path) }
}

/**
 * Sequence containing declarations that don't have file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutFilePath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInFilePath(path) }
}

/**
 * Sequence containing declarations that have project file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withProjectFilePath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInProjectFilePath(path) }
}

/**
 * Sequence containing declarations that don't have project file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutProjectFilePath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInProjectFilePath(path) }
}
