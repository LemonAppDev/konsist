package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.parents

/**
 * Base declaration
 */
internal open class KoBaseDeclarationImpl(private val ktElement: KtElement) : KoPsiDeclarationImpl(ktElement), KoBaseDeclaration {
    /**
     * KoFile containing the declaration
     */
    override val containingFile by lazy { KoFileDeclarationImpl.getInstance(ktElement.containingKtFile, this) }

    override val parentDeclaration by lazy {
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

    override fun hasParentDeclaration(name: String?) = when (name) {
        null -> parentDeclaration != null
        else -> parentDeclaration?.name == name
    }
}
