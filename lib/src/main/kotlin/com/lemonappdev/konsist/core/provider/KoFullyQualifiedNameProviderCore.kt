package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.list.withWildcard
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.isTypeAvailable

internal interface KoFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore {
    val stringUsedAsFullyQualifiedName: String
        get() = name

    override val fullyQualifiedName: String?
        get() {
            val imports = containingFile.imports

            // Check if any explicit import already matches the fully qualified name
            imports
                .map { it.name }
                .firstOrNull { it.isFullyQualifiedName() }
                ?.let { return it }

            // Check other declarations in the same file (excluding annotation declarations when current is annotation)
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
                ?.let { return it }

            // Check wildcard imports one by one
            imports
                .withWildcard()
                .forEach { wildcardImport ->
                    val candidateFqn = "${wildcardImport.name}.$stringUsedAsFullyQualifiedName"

                    if (isTypeAvailable(candidateFqn)) {
                        return candidateFqn
                    }
                }

            // Check kotlin types one by one
            TypeUtil
                .kotlinTypes
                .filter { it.endsWith(".$stringUsedAsFullyQualifiedName") }
                .forEach { kotlinType ->
                    if (isTypeAvailable(kotlinType)) {
                        return kotlinType
                    }
                }

            // Fallback: return the simple name if nothing else matches
            return stringUsedAsFullyQualifiedName
        }

    fun String.isFullyQualifiedName(): Boolean = split(".").last() == stringUsedAsFullyQualifiedName
}
