package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.Modifier
import com.lemon.konsist.core.const.toKtToken
import com.lemon.konsist.ext.isInternal
import com.lemon.konsist.ext.isPrivate
import com.lemon.konsist.ext.isProtected
import com.lemon.konsist.ext.isPublic
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

open class KoDeclaration(private val ktTypeParameterListOwner: KtTypeParameterListOwner) : KoNamedDeclaration(ktTypeParameterListOwner) {

    val fullyQualifiedName by lazy { ktTypeParameterListOwner.fqName.toString() }

    val packageName by lazy {
        ktTypeParameterListOwner
            .fqName
            .toString()
            .split(".")
            .toMutableList()
            .apply { removeLast() }
            .joinToString(separator = ".")
    }

    val isPublic by lazy { ktTypeParameterListOwner.modifierList.isPublic() }

    val isPrivate by lazy { ktTypeParameterListOwner.modifierList.isPrivate() }

    val isProtected by lazy { ktTypeParameterListOwner.modifierList.isProtected() }

    val isInternal by lazy { ktTypeParameterListOwner.modifierList.isInternal() }

    val isTopLevel = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    val annotations = ktTypeParameterListOwner
        .annotationEntries
        .map { KoAnnotation(it) }

    fun hasAnnotation(kClass: KClass<*>): Boolean {
        val qualifiedName = kClass.qualifiedName ?: return false

        return annotations
            .map { getFullyQualifiedClassName(it.type) }
            .contains(qualifiedName)
    }

    fun hasModifiers(vararg modifiers: Modifier) = modifiers.all {
        ktTypeParameterListOwner
            .modifierList
            ?.hasModifier(it.toKtToken())
            ?: false
    }

    fun getFullyQualifiedClassName(annotationTypes: List<String>) = annotationTypes.map { getFullyQualifiedClassName(it) }

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
