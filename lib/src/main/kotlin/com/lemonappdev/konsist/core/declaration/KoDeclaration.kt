package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

abstract class KoDeclaration(private val ktTypeParameterListOwner: KtTypeParameterListOwner) :
    KoNamedDeclaration(ktTypeParameterListOwner) {

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
        .map { KoAnnotation.getInstance(it) }

    val modifiers by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split(" ", "\n")
            ?.toMutableList()
            ?.also {
                it.removeIf { string -> string.contains('@') }
            }
            ?.map { KoModifier.valueOf(it.uppercase()) }
            ?: emptyList()
    }

    val koDoc by lazy {
        val kDocElement = ktTypeParameterListOwner
            .children
            .filterIsInstance<KDocElement>()
            .firstOrNull()

        kDocElement?.let { KoDoc(kDocElement) }
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

    fun resideInPackage(packageName: String) = PackageHelper.resideInPackage(packageName, this.packageName)

    fun resideOutsidePackage(packageName: String) = !resideInPackage(packageName)
}
