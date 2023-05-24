package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.parent.KoParent
import com.lemonappdev.konsist.core.util.LocationHelper
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

internal abstract class KoDeclarationImpl(
    private val ktTypeParameterListOwner: KtTypeParameterListOwner,
    val parent: KoParent,
) : KoNamedDeclarationImpl(ktTypeParameterListOwner), KoDeclaration {

    override val fullyQualifiedName by lazy {
        if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
    }

    override val packagee by lazy {
        fullyQualifiedName
            .split(".")
            .toMutableList()
            .also { it.removeLast() }
            .joinToString(separator = ".")
    }

    override val annotations = ktTypeParameterListOwner
        .annotationEntries
        .map { KoAnnotationDeclarationImpl.getInstance(it, this) }

    override val modifiers by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split(" ", "\n")
            ?.takeLastWhile {
                // We filter this way because this list contains modifiers and annotations,
                // and we need to exclude all annotations especially with blank spaces
                // e.g. @SampleAnnotation(parameter = "sample parameter")
                !it.contains(')') && !it.contains('@') && it.isNotBlank()
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

    override fun hasPublicModifier() = hasModifiers(KoModifier.PUBLIC)

    override fun isPublicOrDefault() = ktTypeParameterListOwner.isPublic

    override fun hasPrivateModifier() = hasModifiers(KoModifier.PRIVATE)

    override fun hasProtectedModifier() = hasModifiers(KoModifier.PROTECTED)

    override fun hasInternalModifier() = hasModifiers(KoModifier.INTERNAL)

    override fun isTopLevel() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    override fun hasAnnotations(vararg names: String) = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all { hasAnnotationNameOrAnnotationFullyQualifyName(it) }
    }

    private fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String) = annotations.any {
        it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name
    }

    override fun hasAnnotationsOf(vararg names: KClass<*>) = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    override fun hasModifiers(vararg koModifiers: KoModifier) = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }

    override fun resideInPackage(packagee: String) = LocationHelper.resideInLocation(packagee, this.packagee)

    override fun resideOutsidePackage(packagee: String) = !resideInPackage(packagee)
}
