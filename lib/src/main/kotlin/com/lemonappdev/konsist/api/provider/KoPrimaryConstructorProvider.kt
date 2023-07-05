package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration

interface KoPrimaryConstructorProvider {
    val primaryConstructor: KoPrimaryConstructorDeclaration?
}
