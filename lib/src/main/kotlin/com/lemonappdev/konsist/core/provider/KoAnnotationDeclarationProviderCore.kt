package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import kotlin.reflect.KClass

internal interface KoAnnotationDeclarationProviderCore :
    KoAnnotationDeclarationProvider,
    KoParentProviderCore {
    val ktAnnotated: KtAnnotated

    override val annotations: List<KoAnnotationDeclaration>
        get() =
            ktAnnotated
                .annotationEntries
                .map { KoAnnotationDeclarationImpl.getInstance(it, this) }

    override fun hasAnnotations(vararg names: String): Boolean = when {
        names.isEmpty() -> annotations.isNotEmpty()
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
