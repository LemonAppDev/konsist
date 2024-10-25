package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import kotlin.reflect.KClass

internal interface KoAnnotationProviderCore :
    KoAnnotationProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktAnnotationEntries: List<KtAnnotationEntry>?

    override val annotations: List<KoAnnotationDeclaration>
        get() =
            ktAnnotationEntries
                ?.map { KoAnnotationDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
                ?: emptyList()

    override val numAnnotations: Int
        get() = annotations.size

    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int = annotations.count { predicate(it) }

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
    ): Boolean = hasAnnotationWithName(listOf(name, *names))

    override fun hasAnnotationWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.any {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override fun hasAnnotationsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasAnnotationsWithAllNames(listOf(name, *names))

    override fun hasAnnotationsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.all {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.any(predicate)

    override fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.all(predicate)

    override fun hasAnnotationOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAnnotationOf(listOf(name, *names))

    override fun hasAnnotationOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.any { checkIfAnnotated(it) }
        }

    override fun hasAllAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllAnnotationsOf(listOf(name, *names))

    override fun hasAllAnnotationsOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.all { checkIfAnnotated(it) }
        }
}
