package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.core.provider.KoKDocDescriptionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocAuthorTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocConstructorTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocExceptionTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocParamTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocPropertyGetterTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocPropertySetterTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocPropertyTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocReceiverTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocReturnTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocSampleTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocSeeTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocSinceTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocSuppressTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocThrowsTagProviderCore
import com.lemonappdev.konsist.core.provider.tag.KoKDocVersionTagProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

internal class KoKDocDeclarationCore(
    private val kDocElement: KDocElement,
) : KoKDocDeclaration,
    KoKDocDescriptionProviderCore,
    KoKDocTagProviderCore,
    KoTextProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoKDocAuthorTagProviderCore,
    KoKDocReceiverTagProviderCore,
    KoKDocReturnTagProviderCore,
    KoKDocParamTagProviderCore,
    KoKDocSeeTagProviderCore,
    KoKDocSampleTagProviderCore,
    KoKDocPropertyTagProviderCore,
    KoKDocPropertyGetterTagProviderCore,
    KoKDocVersionTagProviderCore,
    KoKDocThrowsTagProviderCore,
    KoKDocSuppressTagProviderCore,
    KoKDocExceptionTagProviderCore,
    KoKDocPropertySetterTagProviderCore,
    KoKDocConstructorTagProviderCore,
    KoKDocSinceTagProviderCore {
    override val psiElement: PsiElement = kDocElement

    override val text: String by lazy {
        val splitKDoc =
            kDocElement
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
            splitKDoc
                .also {
                    it.removeFirst()
                    it.removeLast()
                }.joinToString("\n") {
                    it
                        .trim()
                        .removePrefix("*")
                        .trim()
                }
        }
    }

    override fun toString(): String = locationWithText
}
