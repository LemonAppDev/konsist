package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.const.toKtToken
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

open class KoDeclaration(private val ktTypeParameterListOwner: KtTypeParameterListOwner) :
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

    val isPublic by lazy { ktTypeParameterListOwner.hasModifier(KtTokens.PUBLIC_KEYWORD) }

    val isPublicOrDefault by lazy {
        ktTypeParameterListOwner.run {
            if (hasModifier(KtTokens.PUBLIC_KEYWORD)) {
                return@run true
            }

            val hasOtherVisibilityModifier =
                hasModifier(KtTokens.PRIVATE_KEYWORD) ||
                    hasModifier(KtTokens.PROTECTED_KEYWORD) ||
                    hasModifier(
                        KtTokens.INTERNAL_KEYWORD,
                    )

            hasOtherVisibilityModifier.not()
        }
    }

    val isPrivate by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(KtTokens.PRIVATE_KEYWORD)
            ?: false
    }

    val isProtected by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(KtTokens.PROTECTED_KEYWORD)
            ?: false
    }

    val isInternal by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(KtTokens.INTERNAL_KEYWORD)
            ?: false
    }

    val isTopLevel = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    val annotations = ktTypeParameterListOwner
        .annotationEntries
        .map { KoAnnotation.getInstance(it) }

    fun hasAnnotation(kClass: KClass<*>): Boolean {
        val qualifiedName = kClass.qualifiedName ?: return false

        return annotations
            .map { getFullyQualifiedClassName(it.type) }
            .contains(qualifiedName)
    }

    fun hasModifiers(vararg koModifiers: KoModifier) = koModifiers.all {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(it.toKtToken())
            ?: false
    }

    private fun getFullyQualifiedClassName(className: String) =
        (ktTypeParameterListOwner.containingFile as KtFile)
            .importDirectives
            .firstOrNull { it.importedName?.identifier == className }
            ?.importedFqName
            ?.toString()
            ?: className

    fun resideInAPackages(vararg packages: String) = packages.toList().any { resideInAPackages(it) }

    private fun resideInAPackages(packages: String): Boolean {
        val declarationPackages = this.packageName
            .split(".")
            .filter { it.isNotBlank() }

        val packagesList = packages
            .split("..")
            .filter { it.isNotBlank() }

        val declarationPackagesList = declarationPackages
            .toMutableList()
            .filter { packagesList.contains(it) }

        return declarationPackagesList == packagesList
    }
}
