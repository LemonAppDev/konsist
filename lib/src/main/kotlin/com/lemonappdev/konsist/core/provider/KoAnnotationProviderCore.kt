package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationCore
import org.jetbrains.kotlin.psi.KtAnnotated
import kotlin.reflect.KClass

internal interface KoAnnotationProviderCore :
    KoAnnotationProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktAnnotated: KtAnnotated

    override val annotations: List<KoAnnotationDeclaration>
        get() = ktAnnotated
            .annotationEntries
            .map { KoAnnotationDeclarationCore.getInstance(it, this) }

    override val numAnnotations: Int
        get() = annotations.size

    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int =
        annotations.count { predicate(it) }

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
