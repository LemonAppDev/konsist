package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentDeclarationImpl :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoDelegateProviderCore,
    KoNameProviderCore,
    KoLocationProviderCore {
    val ktSuperTypeListEntry: KtSuperTypeListEntry
    override val psiElement: PsiElement
        get() = ktSuperTypeListEntry

    override val ktElement: KtElement
        get() = ktSuperTypeListEntry

    override val name: String
        get() = ktSuperTypeListEntry
            .text
            .removeSuffix("()")
            .replace("\n", " ")
            .substringBefore(" by")

    override val delegateName: String?
        get() = if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) {
            (ktSuperTypeListEntry as KtDelegatedSuperTypeEntry)
                .delegateExpression
                ?.text
        } else {
            null
        }
}
