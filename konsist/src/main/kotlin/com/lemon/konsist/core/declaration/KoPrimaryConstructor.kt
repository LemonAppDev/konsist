package com.lemon.konsist.core.declaration

import com.lemon.konsist.ext.isInternal
import com.lemon.konsist.ext.isPrivate
import com.lemon.konsist.ext.isProtected
import com.lemon.konsist.ext.isPublic
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtPrimaryConstructor
import kotlin.reflect.KClass

class KoPrimaryConstructor(
    private val ktPrimaryConstructor: KtPrimaryConstructor,
    private val ktParameters: List<KtParameter>,
    private val ktModifierList: KtModifierList?,
) : KoBaseDeclaration(ktPrimaryConstructor) {
    val name by lazy { ktPrimaryConstructor.name }

    val isPublic by lazy { ktModifierList.isPublic() }

    val isPrivate by lazy { ktModifierList.isPrivate() }

    val isProtected by lazy { ktModifierList.isProtected() }

    val isInternal by lazy { ktModifierList.isInternal() }

    val parameters by lazy { ktParameters.map { KoParameter(it) } }

    fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name

    val annotations = ktPrimaryConstructor
        .annotationEntries
        .map { KoAnnotation(it) }

    fun hasAnnotation(kClass: KClass<*>): Boolean {
        val qualifiedName = kClass.qualifiedName ?: return false

        return annotations
            .map { getFullyQualifiedClassName(it.type) }
            .contains(qualifiedName)
    }

    private fun getFullyQualifiedClassName(className: String) =
        (ktPrimaryConstructor.containingFile as KtFile)
            .importDirectives
            .firstOrNull { it.importedName?.identifier == className }
            ?.importedFqName
            ?.toString()
            ?: className
}
