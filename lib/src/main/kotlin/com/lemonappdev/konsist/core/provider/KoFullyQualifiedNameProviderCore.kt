package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

internal interface KoFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore {
    val stringUsedAsFullyQualifiedName: String
        get() = name

    override val fullyQualifiedName: String?
        get() {
            var fullyQualifiedName =
                containingFile
                    .imports
                    .map { it.name }
                    .firstOrNull { it.isFullyQualifiedName() }

            if (fullyQualifiedName == null) {
                fullyQualifiedName =
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

            return fullyQualifiedName ?: stringUsedAsFullyQualifiedName
        }

    fun String.isFullyQualifiedName(): Boolean = split(".").last() == stringUsedAsFullyQualifiedName
}
