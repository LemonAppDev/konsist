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
            .map { it.getFullyQualifiedClassName(it.type, ktTypeParameterListOwner.containingFile as KtFile) }
            .contains(qualifiedName)
    }

    fun hasModifiers(vararg koModifiers: KoModifier) = koModifiers.all {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(it.toKtToken())
            ?: false
    }

    fun resideInPackages(vararg packages: String) = packages.toList().any { resideInPackages(it) }

    @Suppress("detekt.CyclomaticComplexMethod")
    private fun resideInPackages(packages: String): Boolean {
        val declarationPackages = packageName
            .split(".")
            .filter { it.isNotBlank() }
            .toMutableList()

        val packagesList = packages
            .split("..")
            .filter { it.isNotBlank() }

        val packagesListWithOneDot = packages
            .split(".")
            .filter { it.isNotBlank() }

        @Suppress("detekt.ComplexCondition")
        return if (
            packagesList.size == 1 &&
            packages.startsWith("..") &&
            packages.endsWith("..")
        ) {
            declarationPackages.contains(packagesList.first())
        } else if (
            packagesList.size == 1 &&
            packages.startsWith("..") &&
            !packages.endsWith("..")
        ) {
            packageName.endsWith(packagesList.first())
        } else if (
            packagesList.size == 1 &&
            !packages.startsWith("..") &&
            packages.endsWith("..")
        ) {
            packageName.startsWith(packagesList.first())
        } else if (
            (packageName.startsWith(packagesList.first()) && packageName.endsWith(packagesList.last())) ||
            (!packageName.startsWith(packagesList.first()) && packages.startsWith("..")) ||
            (!packageName.endsWith(packagesList.last()) && packages.endsWith(".."))
        ) {
            var counter = 0
            packagesListWithOneDot.forEach {
                if (declarationPackages.contains(it)) {
                    val index = declarationPackages.indexOf(it)
                    declarationPackages.removeAll { element -> declarationPackages.indexOf(element) <= index }
                } else {
                    counter++
                }
            }
            counter == 0
        } else {
            false
        }
    }
}
