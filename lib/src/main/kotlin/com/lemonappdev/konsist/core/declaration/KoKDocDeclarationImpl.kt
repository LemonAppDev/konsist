package com.lemonappdev.konsist.core.declaration

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
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import java.util.Locale

internal class KoKDocDeclarationImpl(private val kDocElement: KDocElement) : KoPsiDeclarationImpl(kDocElement), KoKDocDeclaration {
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

    override val description: String by lazy {
        text
            .substringBefore("@")
            .trimEnd()
    }

    override val tags: List<KoKDocTagDeclaration> by lazy {
        val regex = "@(\\w+)".toRegex()

        val tagsAsStringList = text
            .substringAfter("@", "")
            .split("\n@")
            .map { ("@${it.replaceFirstChar { char -> char.lowercase(Locale.getDefault()) }}").trimEnd() }

        val tagsWithName = tagsAsStringList
            .filterNot { it == "@" }
            .flatMap { regex.findAll(it) }
            .mapNotNull { KoKDocTag.values().firstOrNull { tag -> tag.type == it.value } }
            .zip(tagsAsStringList)

        val tagsGroupingByValued = tagsWithName.groupBy { it.first.isValued }

        tagsGroupingByValued.flatMap {
            when (it.key) {
                true -> it.value.map { value -> parseToValuedTag(value.first, value.second) }
                false -> it.value.map { value -> parseToTag(value.first, value.second) }
            }
        }
    }

    override val paramTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == PARAM }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val returnTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == RETURN }
    }

    override val constructorTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == CONSTRUCTOR }
    }

    override val receiverTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == RECEIVER }
    }

    override val propertyTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == PROPERTY }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val throwsTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == THROWS }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val exceptionTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == EXCEPTION }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val sampleTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == SAMPLE }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val seeTags: List<KoValuedKDocTagDeclaration> by lazy {
        tags.filter { it.name == SEE }
            .map { it as KoValuedKDocTagDeclaration }
    }

    override val authorTags: List<KoKDocTagDeclaration> by lazy {
        tags.filter { it.name == AUTHOR }
    }

    override val sinceTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == SINCE }
    }

    override val suppressTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == SUPPRESS }
    }

    override val versionTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == VERSION }
    }

    override val propertySetterTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == PROPERTY_SETTER }
    }

    override val propertyGetterTag: KoKDocTagDeclaration? by lazy {
        tags.firstOrNull { it.name == PROPERTY_GETTER }
    }

    override fun hasTags(vararg tags: KoKDocTag): Boolean = tags.all {
        this.tags
            .map { tag -> tag.name }
            .contains(it)
    }

    private fun parseToValuedTag(koKDocTag: KoKDocTag, sentence: String): KoValuedKDocTagDeclaration {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(2, parsed.size)
            .joinToString(" ")

        return KoValuedKDocTagDeclarationImpl(koKDocTag, parsed[1], description)
    }

    private fun parseToTag(koKDocTag: KoKDocTag, sentence: String): KoKDocTagDeclaration {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(1, parsed.size)
            .joinToString(" ")

        return KoKDocTagDeclarationImpl(koKDocTag, description)
    }
}
