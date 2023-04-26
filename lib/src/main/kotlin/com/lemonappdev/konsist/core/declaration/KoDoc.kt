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
import com.lemonappdev.konsist.core.const.KoTag.VERSION
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement
import org.jetbrains.kotlin.kdoc.psi.impl.KDocSection

class KoDoc(private val kDocElement: KDocElement) {
    val text: String by lazy { kDocElement.text }

    val description by lazy {
        kDocElement
            .children
            .filterIsInstance<KDocSection>()
            .first()
            .text
            .substringBefore("@")
            .split("\n")
            .map {
                it
                    .substringAfter("*")
                    .removePrefix(" ")
            }
            .toMutableList()
            .also { it.removeIf { element -> element.isBlank() } }
            .joinToString("\n")
    }

    private val tags by lazy {
        val firsts = kDocElement
            .children
            .filterIsInstance<KDocSection>()
            .first()
            .text
            .replaceBefore('@', "")
            .split("\n")
            .map {
                it
                    .substringAfter("*")
                    .removePrefix(" ")
            }
            .toMutableList()
            .also { it.removeIf { element -> element.isBlank() } }

        val others = kDocElement
            .children
            .filterIsInstance<KDocSection>()
            .toMutableList()
            .also { it.removeFirst() }
            .map { it.text }
            .flatMap {
                it.split("\n")
                    .map { line ->
                        line
                            .substringAfter("*")
                            .removePrefix(" ")
                    }
                    .toMutableList()
                    .also { line -> line.removeIf { element -> element.isBlank() } }
            }

        firsts + others
    }

    val paramBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(PARAM.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val returnBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(RETURN.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val constructorBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(CONSTRUCTOR.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val receiverBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(RECEIVER.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val propertyBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(PROPERTY.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val throwsBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(KoTag.THROWS.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val exceptionBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(EXCEPTION.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val sampleBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(SAMPLE.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val seeBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(SEE.type) }

        tag.map { parseToValuedBlockTag(it) }
    }

    val authorBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith(AUTHOR.type) }

        tag.map { parseToBlockTag(it) }
    }

    val sinceBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(SINCE.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val suppressBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(SUPPRESS.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val versionBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(VERSION.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val propertySetterBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(PROPERTYSETTER.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val propertyGetterBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith(PROPERTYGETTER.type) }

        tag?.let { parseToBlockTag(it) }
    }

    val blockTags by lazy {
        (
                paramBlockTags + returnBlockTag + constructorBlockTag + receiverBlockTag +
                        propertyBlockTags + throwsBlockTags + exceptionBlockTags + sampleBlockTags +
                        seeBlockTags + authorBlockTags + sinceBlockTag + suppressBlockTag +
                        versionBlockTag + propertySetterBlockTag + propertyGetterBlockTag
                ).filterNotNull()
    }

    fun hasTags(vararg tags: KoTag) = tags.all {
        blockTags
            .map { tag -> tag.name }
            .contains(it)
    }

    private fun parseToValuedBlockTag(sentence: String): KoValuedDocTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .toMutableList()
            .also {
                it.removeFirst()
                it.removeFirst()
            }
            .joinToString(" ")

        val name = KoTag.values().first { tag ->
            tag.type == parsed[0]
        }

        return KoValuedDocTag(name, parsed[1], description)
    }

    private fun parseToBlockTag(sentence: String): KoDocTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .toMutableList()
            .also { it.removeFirst() }
            .joinToString(" ")

        val name = KoTag.values().first { tag ->
            tag.type == parsed[0]
        }

        return KoDocTag(name, description)
    }
}
