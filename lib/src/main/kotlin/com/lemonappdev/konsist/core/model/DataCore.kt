package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

object DataCore {
    val classes: List<KoClassDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .classes()
    }

    val interfaces: List<KoInterfaceDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .interfaces()
    }

    val objects: List<KoObjectDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .objects()
    }
}
