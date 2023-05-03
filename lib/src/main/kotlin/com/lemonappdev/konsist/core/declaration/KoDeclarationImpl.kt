package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

@Suppress("detekt.TooManyFunctions")
internal abstract class KoDeclarationImpl(private val ktTypeParameterListOwner: KtTypeParameterListOwner) :
    KoNamedDeclarationImpl(ktTypeParameterListOwner) {

    open val fullyQualifiedName by lazy {
        if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
    }

    val packageName by lazy {
        fullyQualifiedName
            .split(".")
            .toMutableList()
            .apply { removeLast() }
            .joinToString(separator = ".")
    }

    val annotations = ktTypeParameterListOwner
        .annotationEntries
        .map { KoAnnotationDeclarationImpl.getInstance(it) }

    val modifiers by lazy {
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

    val koDoc by lazy {
        val kDocElement = ktTypeParameterListOwner
            .children
            .filterIsInstance<KDocElement>()
            .firstOrNull()

        kDocElement?.let { KoDocDeclarationImpl(kDocElement) }
    }

    fun hasPublicModifier() = hasModifiers(KoModifier.PUBLIC)

    fun isPublicOrDefault() = ktTypeParameterListOwner.isPublic

    fun hasPrivateModifier() = hasModifiers(KoModifier.PRIVATE)

    fun hasProtectedModifier() = hasModifiers(KoModifier.PROTECTED)

    fun hasInternalModifier() = hasModifiers(KoModifier.INTERNAL)

    fun isTopLevel() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    fun hasAnnotations(vararg names: String) = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all { hasAnnotationNameOrAnnotationFullyQualifyName(it) }
    }

    private fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String) = annotations.any {
        it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name
    }

    fun hasAnnotationsOf(vararg names: KClass<*>) = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    inline fun <reified T> hasAnnotationOf(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
    }

    fun hasModifiers(vararg koModifiers: KoModifier) = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }

    fun hasKoDoc() = koDoc != null

    fun resideInPackage(packageName: String) = PackageHelper.resideInPackage(packageName, this.packageName)

    fun resideOutsidePackage(packageName: String) = !resideInPackage(packageName)
}
