package com.lemonappdev.konsist.core.provider.util

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoVariableDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty

internal object KoLocalDeclarationProviderCoreUtil {
    fun getKoLocalDeclarations(
        psiElements: Array<PsiElement>?,
        containingDeclaration: KoBaseDeclaration,
    ): List<KoBaseDeclaration> {
        val declarations = psiElements
            ?.filterIsInstance<KtDeclaration>()
            .orEmpty()

        return declarations
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClassDeclarationCore.getInstance(it, containingDeclaration)
                } else if (it is KtFunction) {
                    KoFunctionDeclarationCore.getInstance(it, containingDeclaration)
                } else if (it is KtProperty) {
                    KoVariableDeclarationCore.getInstance(it, containingDeclaration)
                } else {
                    null
                }
            }
    }
}
