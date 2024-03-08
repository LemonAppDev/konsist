package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtAnnotated
import kotlin.reflect.KClass

internal interface KoAnnotationProviderCore :
    KoAnnotationProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktAnnotated: KtAnnotated

    override val annotations: List<KoAnnotationDeclaration>
        get() =
            ktAnnotated
                .annotationEntries
                .map { KoAnnotationDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numAnnotations: Int
        get() = annotations.size

    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int = annotations.count { predicate(it) }

    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasAnnotationWithName`, otherwise with `hasAnnotationsWithAllNames`.
            """,
    )
    override fun hasAnnotations(vararg names: String): Boolean =
        when {
            names.isEmpty() -> annotations.isNotEmpty()
            else ->
                names.all {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasAnnotationOf`, otherwise with `hasAllAnnotationsOf`.
            """,
    )
    override fun hasAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = checkIfAnnotated(name) && names.all { checkIfAnnotated(it) }

    private fun checkIfAnnotated(kClass: KClass<*>): Boolean =
        annotations.any { annotation ->
            if (kClass.qualifiedName?.startsWith("kotlin.") == true) {
                annotation.name == kClass.simpleName
            } else {
                annotation.fullyQualifiedName == kClass.qualifiedName
            }
        }

    override fun hasAnnotations(): Boolean = annotations.isNotEmpty()

    override fun hasAnnotationWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            annotations.any { annotation -> annotation.representsType(it) }
        }
    }

    override fun hasAnnotationsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            annotations.any { annotation -> annotation.representsType(it) }
        }
    }

    override fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.any(predicate)

    override fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.all(predicate)

    override fun hasAnnotationOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = checkIfAnnotated(name) || names.any { checkIfAnnotated(it) }

    override fun hasAllAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = checkIfAnnotated(name) && names.all { checkIfAnnotated(it) }
}
