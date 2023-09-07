package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.core.provider.KoKDocDescriptionProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagsProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

internal class KoKDocDeclarationCore(private val kDocElement: KDocElement) :
    KoKDocDeclaration,
    KoKDocDescriptionProviderCore,
    KoKDocTagsProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { kDocElement }

    override val text: String by lazy {
        val splitKDoc = kDocElement
            .text
            .split(EndOfLine.UNIX.value)
            .toMutableList()

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

    override fun toString(): String = text
}
