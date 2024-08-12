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

fun getClass(
    name: String,
    fqn: String?,
    isAlias: Boolean = false,
    containingFile: KoFileDeclaration,
): KoClassDeclaration? {
    val declarationQualifiedName = if (fqn?.endsWith(name) == true || isAlias) fqn else "$fqn.$name"

    return DataCore
        .classes
        .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .classes()
            .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .classes()
            .firstOrNull { decl -> decl.name == name }
}

fun getInterface(
    name: String,
    fqn: String?,
    isAlias: Boolean,
    containingFile: KoFileDeclaration,
): KoInterfaceDeclaration? {
    val declarationQualifiedName = if (fqn?.endsWith(name) == true || isAlias) fqn else "$fqn.$name"

    return DataCore
        .interfaces
        .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .interfaces()
            .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .interfaces()
            .firstOrNull { decl -> decl.name == name }
}

fun getObject(
    name: String,
    fqn: String?,
    isAlias: Boolean,
    containingFile: KoFileDeclaration,
): KoObjectDeclaration? {
    val declarationQualifiedName = if (fqn?.endsWith(name) == true || isAlias) fqn else "$fqn.$name"

    return DataCore
        .objects
        .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .objects()
            .firstOrNull { decl -> decl.fullyQualifiedName == declarationQualifiedName }
        ?: containingFile
            .objects()
            .firstOrNull { decl -> decl.name == name }
}

fun getTypeAlias(
    name: String,
    fqn: String?,
    containingFile: KoFileDeclaration,
): KoTypeAliasDeclaration? =
    DataCore
        .typeAliases
        .firstOrNull { decl -> (decl.packagee?.name + "." + decl.name) == fqn }
        ?: containingFile
            .typeAliases
            .firstOrNull { decl -> decl.name == name }
