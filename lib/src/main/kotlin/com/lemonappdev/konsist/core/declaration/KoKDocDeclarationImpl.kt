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
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import java.util.Locale

internal class KoKDocDeclarationImpl(private val kDocElement: KDocElement) : KoPsiDeclarationImpl(kDocElement), KoKDocDeclaration {
    override val text: String by lazy {
        (kDocElement.text.split("\n") as MutableList)
            .also {
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

    override val description by lazy {
        text
            .substringBefore("@")
            .trimEnd()
    }

    override val tags by lazy {
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

    override val paramTags by lazy {
        tags.filter { it.name == PARAM }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val returnTag by lazy {
        tags.firstOrNull { it.name == RETURN }
    }

    override val constructorTag by lazy {
        tags.firstOrNull { it.name == CONSTRUCTOR }
    }

    override val receiverTag by lazy {
        tags.firstOrNull { it.name == RECEIVER }
    }

    override val propertyTags by lazy {
        tags.filter { it.name == PROPERTY }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val throwsTags by lazy {
        tags.filter { it.name == THROWS }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val exceptionTags by lazy {
        tags.filter { it.name == EXCEPTION }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val sampleTags by lazy {
        tags.filter { it.name == SAMPLE }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val seeTags by lazy {
        tags.filter { it.name == SEE }
            .map { it as KoValuedKDocTagDeclarationImpl }
    }

    override val authorTags by lazy {
        tags.filter { it.name == AUTHOR }
    }

    override val sinceTag by lazy {
        tags.firstOrNull { it.name == SINCE }
    }

    override val suppressTag by lazy {
        tags.firstOrNull { it.name == SUPPRESS }
    }

    override val versionTag by lazy {
        tags.firstOrNull { it.name == VERSION }
    }

    override val propertySetterTag by lazy {
        tags.firstOrNull { it.name == PROPERTY_SETTER }
    }

    override val propertyGetterTag by lazy {
        tags.firstOrNull { it.name == PROPERTY_GETTER }
    }

    override fun hasTags(vararg tags: KoKDocTag) = tags.all {
        this.tags
            .map { tag -> tag.name }
            .contains(it)
    }

    private fun parseToValuedTag(koKDocTag: KoKDocTag, sentence: String): KoValuedKDocTagDeclarationImpl {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(2, parsed.size)
            .joinToString(" ")

        return KoValuedKDocTagDeclarationImpl(koKDocTag, parsed[1], description)
    }

    private fun parseToTag(koKDocTag: KoKDocTag, sentence: String): KoKDocTagDeclarationImpl {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(1, parsed.size)
            .joinToString(" ")

        return KoKDocTagDeclarationImpl(koKDocTag, description)
    }
}
