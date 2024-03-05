package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

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

    val typeAliases: List<KoTypeAliasDeclaration> by lazy {
        Konsist
            .scopeFromProject()
            .typeAliases
    }
}

fun getClass(name: String, fqn: String?, containingFile: KoFileDeclaration): KoClassDeclaration? = DataCore
    .classes
    .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
    ?: containingFile
        .classes()
        .firstOrNull { decl -> decl.name == name }

fun getInterface(name: String, fqn: String?, containingFile: KoFileDeclaration): KoInterfaceDeclaration? = DataCore
    .interfaces
    .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
    ?: containingFile
        .interfaces()
        .firstOrNull { decl -> decl.name == name }

fun getObject(name: String, fqn: String?, containingFile: KoFileDeclaration): KoObjectDeclaration? = DataCore
    .objects
    .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
    ?: containingFile
        .objects()
        .firstOrNull { decl -> decl.name == name }

fun getTypeAlias(name: String, fqn: String?, containingFile: KoFileDeclaration): KoTypeAliasDeclaration? = DataCore
    .typeAliases
    .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
    ?: containingFile
        .typeAliases
        .firstOrNull { decl -> decl.name == name }
