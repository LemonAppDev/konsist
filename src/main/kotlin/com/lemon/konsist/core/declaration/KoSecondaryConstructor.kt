package com.lemon.konsist.core.declaration

import com.lemon.konsist.ext.isInternal
import com.lemon.konsist.ext.isPrivate
import com.lemon.konsist.ext.isProtected
import com.lemon.konsist.ext.isPublic
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import kotlin.reflect.KClass

class KoSecondaryConstructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
) : KoBaseDeclaration(ktSecondaryConstructor) {
    val isPublic by lazy { ktSecondaryConstructor.modifierList.isPublic() }

    val isPrivate by lazy { ktSecondaryConstructor.modifierList.isPrivate() }

    val isProtected by lazy { ktSecondaryConstructor.modifierList.isProtected() }

    val isInternal by lazy { ktSecondaryConstructor.modifierList.isInternal() }

    val name by lazy { ktSecondaryConstructor.name }

    val annotations = ktSecondaryConstructor
        .annotationEntries
        .map { KoAnnotation(it) }

    fun hasAnnotation(kClass: KClass<*>): Boolean {
        val qualifiedName = kClass.qualifiedName ?: return false

        return annotations
            .map { getFullyQualifiedClassName(it.type) }
            .contains(qualifiedName)
    }

    private fun getFullyQualifiedClassName(className: String) =
        (ktSecondaryConstructor.containingFile as KtFile)
            .importDirectives
            .firstOrNull { it.importedName?.identifier == className }
            ?.importedFqName
            ?.toString()
            ?: className
}
