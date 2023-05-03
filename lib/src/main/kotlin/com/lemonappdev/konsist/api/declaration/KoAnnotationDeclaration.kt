package com.lemonappdev.konsist.api.declaration

interface KoAnnotationDeclaration : KoNamedDeclaration {
    val fullyQualifiedName: String

    fun representsType(name: String): Boolean
}
