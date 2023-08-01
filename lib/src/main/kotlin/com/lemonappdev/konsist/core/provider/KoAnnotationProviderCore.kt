package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import org.jetbrains.kotlin.psi.KtAnnotated
import kotlin.reflect.KClass

internal interface KoAnnotationProviderCore :
    KoAnnotationProvider,
    KoParentProviderCore,
    KoBaseProviderCore {
    val ktAnnotated: KtAnnotated?
    val koFiles: List<KoFileDeclaration>?

    override val annotations: List<KoAnnotationDeclaration>
        get() = if (ktAnnotated != null) {
            ktAnnotated
                ?.annotationEntries
                ?.map { KoAnnotationDeclarationImpl.getInstance(it, this) }
                ?: emptyList()
        } else {
            koFiles?.flatMap { it.annotations } ?: emptyList()
        }

    override fun hasAnnotations(vararg names: String): Boolean = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all {
            annotations.any { annotation -> annotation.representsType(it) }
        }
    }

    override fun hasAnnotationsOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
        checkIfAnnotated(name) && names.all { checkIfAnnotated(it) }

    private fun checkIfAnnotated(kClass: KClass<*>): Boolean =
        annotations.any { annotation ->
            if (kClass.qualifiedName?.startsWith("kotlin.") == true) {
                annotation.name == kClass.simpleName
            } else {
                annotation.fullyQualifiedName == kClass.qualifiedName
            }
        }
}
