package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.const.toKtToken
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
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

    fun hasPublicModifier() = ktTypeParameterListOwner.hasModifier(KtTokens.PUBLIC_KEYWORD)

    fun isPublicOrDefault() = ktTypeParameterListOwner.run {
        if (hasModifier(KtTokens.PUBLIC_KEYWORD)) {
            return@run true
        }

        val hasOtherVisibilityModifier =
            hasModifier(KtTokens.PRIVATE_KEYWORD) ||
                hasModifier(KtTokens.PROTECTED_KEYWORD) ||
                hasModifier(KtTokens.INTERNAL_KEYWORD)

        hasOtherVisibilityModifier.not()
    }

    fun hasPrivateModifier() = ktTypeParameterListOwner
        .modifierList
        ?.hasModifier(KtTokens.PRIVATE_KEYWORD)
        ?: false

    fun hasProtectedModifier() = ktTypeParameterListOwner
        .modifierList
        ?.hasModifier(KtTokens.PROTECTED_KEYWORD)
        ?: false

    fun hasInternalModifier() = ktTypeParameterListOwner
        .modifierList
        ?.hasModifier(KtTokens.INTERNAL_KEYWORD)
        ?: false

    fun isTopLevel() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    fun hasAnnotation(name: String) = annotations
        .any { it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name }

    inline fun <reified T> hasAnnotation(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
    }

    fun hasModifiers(vararg koModifiers: KoModifier) = koModifiers.all {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(it.toKtToken())
            ?: false
    }

    fun resideInPackage(packageName: String) = PackageHelper.resideInPackage(packageName, this.packageName)

    fun resideOutsidePackage(packageName: String) = !resideInPackage(packageName)
}
