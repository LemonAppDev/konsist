package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag
import com.lemonappdev.konsist.api.KoTag.AUTHOR
import com.lemonappdev.konsist.api.KoTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoTag.EXCEPTION
import com.lemonappdev.konsist.api.KoTag.PARAM
import com.lemonappdev.konsist.api.KoTag.PROPERTY
import com.lemonappdev.konsist.api.KoTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoTag.RECEIVER
import com.lemonappdev.konsist.api.KoTag.RETURN
import com.lemonappdev.konsist.api.KoTag.SAMPLE
import com.lemonappdev.konsist.api.KoTag.SEE
import com.lemonappdev.konsist.api.KoTag.SINCE
import com.lemonappdev.konsist.api.KoTag.SUPPRESS
import com.lemonappdev.konsist.api.KoTag.THROWS
import com.lemonappdev.konsist.api.KoTag.VERSION
import com.lemonappdev.konsist.api.declaration.KoDocDeclaration
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

internal class KoDocDeclarationImpl(private val kDocElement: KDocElement) : KoPsiDeclarationImpl(kDocElement), KoDocDeclaration {
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

    override val blockTags by lazy {
        val regex = "@(\\w+)".toRegex()

        val tagsAsStringList = text.substringAfter("@")
            .split("\n@")
            .map { ("@$it").trimEnd() }

        val tagsWithName = tagsAsStringList
            .flatMap { regex.findAll(it) }
            .map {
                if (KoTag.values().none { koTag -> koTag.type == it.value }) {
                    throw KoInternalException("Unknown doc tag: ${it.value}")
                }

                KoTag.values().first { tag -> tag.type == it.value }
            }
            .zip(tagsAsStringList)

        val tagsGroupingByValued = tagsWithName.groupBy { it.first.isValued }

        tagsGroupingByValued.flatMap {
            when (it.key) {
                true -> it.value.map { value -> parseToValuedBlockTag(value.first, value.second) }
                false -> it.value.map { value -> parseToBlockTag(value.first, value.second) }
            }
        }
    }

    override val paramBlockTags by lazy {
        blockTags.filter { it.name == PARAM }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val returnBlockTag by lazy {
        blockTags.firstOrNull { it.name == RETURN }
    }

    override val constructorBlockTag by lazy {
        blockTags.firstOrNull { it.name == CONSTRUCTOR }
    }

    override val receiverBlockTag by lazy {
        blockTags.firstOrNull { it.name == RECEIVER }
    }

    override val propertyBlockTags by lazy {
        blockTags.filter { it.name == PROPERTY }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val throwsBlockTags by lazy {
        blockTags.filter { it.name == THROWS }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val exceptionBlockTags by lazy {
        blockTags.filter { it.name == EXCEPTION }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val sampleBlockTags by lazy {
        blockTags.filter { it.name == SAMPLE }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val seeBlockTags by lazy {
        blockTags.filter { it.name == SEE }
            .map { it as KoValuedDocTagDeclarationImpl }
    }

    override val authorBlockTags by lazy {
        blockTags.filter { it.name == AUTHOR }
    }

    override val sinceBlockTag by lazy {
        blockTags.firstOrNull { it.name == SINCE }
    }

    override val suppressBlockTag by lazy {
        blockTags.firstOrNull { it.name == SUPPRESS }
    }

    override val versionBlockTag by lazy {
        blockTags.firstOrNull { it.name == VERSION }
    }

    override val propertySetterBlockTag by lazy {
        blockTags.firstOrNull { it.name == PROPERTY_SETTER }
    }

    override val propertyGetterBlockTag by lazy {
        blockTags.firstOrNull { it.name == PROPERTY_GETTER }
    }

    override fun hasTags(vararg tags: KoTag) = tags.all {
        blockTags
            .map { tag -> tag.name }
            .contains(it)
    }

    override fun parseToValuedBlockTag(koTag: KoTag, sentence: String): KoValuedDocTagDeclarationImpl {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(2, parsed.size)
            .joinToString(" ")

        return KoValuedDocTagDeclarationImpl(koTag, parsed[1], description)
    }

    override fun parseToBlockTag(koTag: KoTag, sentence: String): KoDocTagDeclarationImpl {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(1, parsed.size)
            .joinToString(" ")

        return KoDocTagDeclarationImpl(koTag, description)
    }
}
