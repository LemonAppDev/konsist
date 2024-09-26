package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import kotlin.reflect.KClass

internal interface KoSourceDeclarationProviderCore :
    KoSourceDeclarationProvider,
    KoBaseProviderCore {
    override val sourceDeclaration: KoBaseDeclaration

    override fun hasSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean = predicate(sourceDeclaration)

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean =
        when (sourceDeclaration) {
            is KoFunctionDeclaration -> (sourceDeclaration as? KoFunctionDeclaration)?.hasReturnTypeOf(kClass) == true
            is KoPropertyDeclaration -> (sourceDeclaration as? KoPropertyDeclaration)?.hasTypeOf(kClass) == true
            is KoTypeAliasDeclaration -> (sourceDeclaration as? KoTypeAliasDeclaration)?.hasTypeOf(kClass) == true
            else -> kClass.qualifiedName == (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName
        }
}
