package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPrivate
import org.jetbrains.kotlin.psi.psiUtil.isProtected
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember

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
            ?.split(" ")
            ?.map { KoModifier.valueOf(it.uppercase()) }
            ?: emptyList()
    }

    fun hasPublicModifier() = modifiers.contains(KoModifier.PUBLIC)

    fun isPublicOrDefault() = ktTypeParameterListOwner.isPublic

    fun hasPrivateModifier() = ktTypeParameterListOwner.isPrivate()

    fun hasProtectedModifier() = ktTypeParameterListOwner.isProtected()

    fun hasInternalModifier() = modifiers.contains(KoModifier.INTERNAL)

    fun isTopLevel() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    fun hasAnnotation(name: String) = annotations
        .any { it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name }

    inline fun <reified T> hasAnnotation(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
    }

    fun hasModifiers(vararg koModifiers: KoModifier) = modifiers.containsAll(koModifiers.toList())

    fun resideInPackage(packageName: String) = PackageHelper.resideInPackage(packageName, this.packageName)

    fun resideOutsidePackage(packageName: String) = !resideInPackage(packageName)
}
