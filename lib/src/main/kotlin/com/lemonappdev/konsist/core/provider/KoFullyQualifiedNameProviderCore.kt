package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

internal interface KoFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore {
    val textUsedToFqn: String
        get() = name

    override val fullyQualifiedName: String
        get() {
            var fqn =
                containingFile
                    .imports
                    .map { it.name }
                    .firstOrNull { it.isFullyQualifiedName() }

            if (fqn == null) {
                fqn =
                    containingFile
                        .declarations()
                        .filterNot {
                            if (this is KoAnnotationDeclaration) {
                                it is KoAnnotationDeclaration
                            } else {
                                false
                            }
                        }.mapNotNull { (it as? KoFullyQualifiedNameProvider)?.fullyQualifiedName }
                        .firstOrNull { it.isFullyQualifiedName() }
            }

            return fqn ?: textUsedToFqn
        }

    fun String.isFullyQualifiedName(): Boolean = split(".").last() == textUsedToFqn
}
