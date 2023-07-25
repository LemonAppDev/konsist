package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.core.provider.KoKDocDescriptionProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagsProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

internal class KoKDocDeclarationImpl(private val kDocElement: KDocElement) :
    KoKDocDeclaration,
    KoTextProviderCore,
    KoKDocDescriptionProviderCore,
    KoKDocTagsProviderCore {
    override val psiElement: PsiElement by lazy { kDocElement }

    override val text: String by lazy {
        val splitKDoc = kDocElement.text.split("\n") as MutableList

        if (splitKDoc.size == 1 && splitKDoc.first().startsWith("/**") && splitKDoc.first().endsWith("*/")) {
            splitKDoc
                .first()
                .removePrefix("/**")
                .removeSuffix("*/")
                .trim()
        } else {
            splitKDoc.also {
                it.removeFirst()
                it.removeLast()
            }
                .joinToString("\n") {
                    it
                        .trim()
                        .removePrefix("*")
                        .trim()
                }
        }
    }
}
