package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.KoKDocTag.VERSION
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.provider.KoKDocDescriptionProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagsProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import java.util.Locale

internal class KoKDocDeclarationImpl(private val kDocElement: KDocElement) :
    KoKDocDeclaration,
    KoKDocProviderCore,
    KoTextProviderCore,
    KoKDocDescriptionProviderCore,
    KoKDocTagsProviderCore {
    override val psiElement: PsiElement
        get() = kDocElement

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
