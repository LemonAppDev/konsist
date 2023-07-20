package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import org.jetbrains.kotlin.psi.KtAnnotated
import kotlin.reflect.KClass

internal interface KoAnnotationProviderCore :
    KoAnnotationProvider,
    KoParentDeclarationProviderCore,
    KoBaseProviderCore {
    val ktAnnotated: KtAnnotated?
    val koFiles: Sequence<KoFile>?

    override val annotations: Sequence<KoAnnotationDeclaration>
        get() = if (ktAnnotated != null) {
            ktAnnotated
                ?.annotationEntries
                ?.map { KoAnnotationDeclarationImpl.getInstance(it, this) }
                ?.asSequence() ?: emptySequence()
        } else {
            koFiles?.flatMap { it.annotations } ?: emptySequence()
        }

    override fun hasAnnotations(vararg names: String): Boolean = when {
        names.isEmpty() -> annotations.toList().isNotEmpty()
        else -> names.all {
            annotations.any { annotation -> annotation.representsType(it) }
        }
    }

    override fun hasAnnotationsOf(vararg names: KClass<*>): Boolean = names.all {
        annotations.any { annotation ->
            if (it.qualifiedName?.startsWith("kotlin.") == true) {
                annotation.name == it.simpleName
            } else {
                annotation.fullyQualifiedName == it.qualifiedName
            }
        }
    }
}
