package com.lemonappdev.konsist.core.provider

import com.intellij.icons.AllIcons.Nodes.Alias
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider

internal interface KoAliasProviderCore :
    KoAliasProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    override fun hasAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> alias != null
            else -> alias?.let { predicate(it) } ?: false
        }
    }
