package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

object DataCore {
    val declarations: List<KoBaseDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .declarationsOf()
    }

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

    val typeAliases: List<KoTypeAliasDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .typeAliases
    }

    /**
     * Classes, interfaces, objects and type aliases of the project by fully qualified name.
     */
    val typesByFullyQualifiedName: Map<String, KoBaseDeclaration> by lazy {
        val scope = Konsist.scopeFromProject()

        associateByFullyQualifiedName(
            scope.classes(includeNested = true, includeLocal = false) +
                scope.interfaces(includeNested = true) +
                scope.objects(includeNested = true) +
                scope.typeAliases,
        )
    }

    /**
     * Associates declarations by fully qualified name. When more declarations have the same fully qualified name
     * (e.g. in different source sets), the first one is used.
     */
    internal fun associateByFullyQualifiedName(declarations: List<KoBaseDeclaration>): Map<String, KoBaseDeclaration> =
        buildMap {
            declarations.forEach { declaration ->
                (declaration as? KoFullyQualifiedNameProvider)
                    ?.fullyQualifiedName
                    ?.let { putIfAbsent(it, declaration) }
            }
        }
}
