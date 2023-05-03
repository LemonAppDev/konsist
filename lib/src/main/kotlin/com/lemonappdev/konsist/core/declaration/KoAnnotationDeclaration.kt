package com.lemonappdev.konsist.core.declaration

interface KoAnnotationDeclaration : KoNamedDeclaration {
    val fullyQualifiedName: String

    fun representsType(name: String): Boolean
}
