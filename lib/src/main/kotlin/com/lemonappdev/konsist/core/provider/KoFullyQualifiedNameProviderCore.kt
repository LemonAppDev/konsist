package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider

internal interface KoFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore {
    val textUsedToFqn: String
        get() = name

    override val fullyQualifiedName: String
        get() {
            val isInImports = containingFile
                .imports
                .map { it.name }
                .firstOrNull { it.contains(textUsedToFqn) }

            val isInFile = containingFile
                .declarations()
                .filterNot {
                    if (this is KoAnnotationDeclaration) {
                        it is KoAnnotationDeclaration
                    } else {
                        false
                    }
                }
                .mapNotNull { (it as? KoFullyQualifiedNameProvider)?.fullyQualifiedName }
                .firstOrNull { it.contains(textUsedToFqn) }

            val additionalTextIfIsNullable = if (this is KoNullableTypeProvider && isNullable) "?" else ""

            return when {
                isInImports != null -> isInImports
                isInFile != null -> isInFile + additionalTextIfIsNullable
                else -> name
            }
        }
}
