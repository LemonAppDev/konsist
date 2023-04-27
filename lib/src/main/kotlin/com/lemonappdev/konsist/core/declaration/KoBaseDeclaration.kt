package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.parents

/**
 * Base declaration
 */
open class KoBaseDeclaration(private val ktElement: KtElement) : KoPsiDeclaration(ktElement) {
    /**
     * KoFile containing the declaration
     */
    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }

    val parentDeclaration by lazy {
        val parents = ktElement
            .parents
            .toList()

        val parent = when {
            parents.size > 1 -> parents[1]
            else -> null
        }

        val name = parent
            ?.namedUnwrappedElement
            ?.name

        name?.let {
            containingFile
                .declarations(includeLocal = true, includeNested = true)
                .firstOrNull { declaration -> declaration.name == it }
        }
    }

    fun hasParentDeclaration(name: String? = null) = when (name) {
        null -> parentDeclaration != null
        else -> parentDeclaration?.name == name
    }
}
