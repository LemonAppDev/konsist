package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoTag
import com.lemonappdev.konsist.core.const.KoTag.AUTHOR
import com.lemonappdev.konsist.core.const.KoTag.CONSTRUCTOR
import com.lemonappdev.konsist.core.const.KoTag.EXCEPTION
import com.lemonappdev.konsist.core.const.KoTag.PARAM
import com.lemonappdev.konsist.core.const.KoTag.PROPERTY
import com.lemonappdev.konsist.core.const.KoTag.PROPERTYGETTER
import com.lemonappdev.konsist.core.const.KoTag.PROPERTYSETTER
import com.lemonappdev.konsist.core.const.KoTag.RECEIVER
import com.lemonappdev.konsist.core.const.KoTag.RETURN
import com.lemonappdev.konsist.core.const.KoTag.SAMPLE
import com.lemonappdev.konsist.core.const.KoTag.SEE
import com.lemonappdev.konsist.core.const.KoTag.SINCE
import com.lemonappdev.konsist.core.const.KoTag.SUPPRESS
import com.lemonappdev.konsist.core.const.KoTag.THROWS
import com.lemonappdev.konsist.core.const.KoTag.VERSION
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

class KoDoc(private val kDocElement: KDocElement) : KoPsiDeclaration(kDocElement) {
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

    val description by lazy {
        text
            .substringBefore("@")
            .trimEnd()
    }

    val blockTags by lazy {
        val regex = "@(\\w+)".toRegex()

        val tagsAsStringList = text.substringAfter("@")
            .split("\n@")
            .map { ("@$it").trimEnd() }

        val tagsWithName = tagsAsStringList
            .flatMap { regex.findAll(it) }
            .map {
                if (KoTag.values().none { koTag -> koTag.type == it.value }) {
                    throw KoInternalException("Unknown doc tag $it")
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

    val paramBlockTags by lazy {
        blockTags.filter { it.name == PARAM }
            .map { it as KoValuedDocTag }
    }

    val returnBlockTag by lazy {
        blockTags.firstOrNull { it.name == RETURN }
    }

    val constructorBlockTag by lazy {
        blockTags.firstOrNull { it.name == CONSTRUCTOR }
    }

    val receiverBlockTag by lazy {
        blockTags.firstOrNull { it.name == RECEIVER }
    }

    val propertyBlockTags by lazy {
        blockTags.filter { it.name == PROPERTY }
            .map { it as KoValuedDocTag }
    }

    val throwsBlockTags by lazy {
        blockTags.filter { it.name == THROWS }
            .map { it as KoValuedDocTag }
    }

    val exceptionBlockTags by lazy {
        blockTags.filter { it.name == EXCEPTION }
            .map { it as KoValuedDocTag }
    }

    val sampleBlockTags by lazy {
        blockTags.filter { it.name == SAMPLE }
            .map { it as KoValuedDocTag }
    }

    val seeBlockTags by lazy {
        blockTags.filter { it.name == SEE }
            .map { it as KoValuedDocTag }
    }

    val authorBlockTags by lazy {
        blockTags.filter { it.name == AUTHOR }
    }

    val sinceBlockTag by lazy {
        blockTags.firstOrNull { it.name == SINCE }
    }

    val suppressBlockTag by lazy {
        blockTags.firstOrNull { it.name == SUPPRESS }
    }

    val versionBlockTag by lazy {
        blockTags.firstOrNull { it.name == VERSION }
    }

    val propertySetterBlockTag by lazy {
        blockTags.firstOrNull { it.name == PROPERTYSETTER }
    }

    val propertyGetterBlockTag by lazy {
        blockTags.firstOrNull { it.name == PROPERTYGETTER }
    }

    fun hasTags(vararg tags: KoTag) = tags.all {
        blockTags
            .map { tag -> tag.name }
            .contains(it)
    }

    private fun parseToValuedBlockTag(koTag: KoTag, sentence: String): KoValuedDocTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(2, parsed.size)
            .joinToString(" ")

        return KoValuedDocTag(koTag, parsed[1], description)
    }

    private fun parseToBlockTag(koTag: KoTag, sentence: String): KoDocTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(1, parsed.size)
            .joinToString(" ")

        return KoDocTag(koTag, description)
    }
}
