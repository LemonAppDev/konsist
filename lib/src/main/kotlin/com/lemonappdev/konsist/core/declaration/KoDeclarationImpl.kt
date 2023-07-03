package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

internal abstract class KoDeclarationImpl(
    private val ktTypeParameterListOwner: KtTypeParameterListOwner,
    val parentDeclaration: KoBaseDeclaration?,
) : KoNamedDeclarationImpl(ktTypeParameterListOwner), KoDeclaration {

    override val fullyQualifiedName: String by lazy {
        if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
    }

    override val packagee: String by lazy {
        fullyQualifiedName
            .split(".")
            .toMutableList()
            .also { it.removeLast() }
            .joinToString(separator = ".")
    }

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        ktTypeParameterListOwner
            .annotationEntries
            .map { KoAnnotationDeclarationImpl.getInstance(it, this) }
    }

    override val modifiers: List<KoModifier> by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split("\n")
            ?.map { it.substringBefore("//") }
            ?.flatMap { it.split(" ") }
            ?.takeLastWhile {
                // We filter this way because this list contains modifiers and annotations,
                // and we need to exclude all annotations especially with blank spaces
                // e.g. @SampleAnnotation(parameter = "sample parameter")
                // and with angle brackets
                // e.g. @SampleAnnotation<String, Int>
                !it.contains('<') &&
                    !it.contains('>') &&
                    !it.contains(')') &&
                    !it.contains('@') &&
                    it.isNotBlank()
            }
            ?.map {
                KoModifier
                    .values()
                    .firstOrNull { modifier -> modifier.type == it }
                    ?: throw KoInternalException("Modifier not found: $it")
            }
            ?.toList()
            ?: emptyList()
    }

    override fun hasPublicModifier(): Boolean = hasModifiers(KoModifier.PUBLIC)

    override fun isPublicOrDefault(): Boolean = ktTypeParameterListOwner.isPublic

    override fun hasPrivateModifier(): Boolean = hasModifiers(KoModifier.PRIVATE)

    override fun hasProtectedModifier(): Boolean = hasModifiers(KoModifier.PROTECTED)

    override fun hasInternalModifier(): Boolean = hasModifiers(KoModifier.INTERNAL)

    override fun isTopLevel(): Boolean = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

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

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }

    override fun resideInPackage(packagee: String): Boolean = LocationUtil.resideInLocation(packagee, this.packagee)

    override fun resideOutsidePackage(packagee: String): Boolean = !resideInPackage(packagee)
}
