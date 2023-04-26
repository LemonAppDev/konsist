package com.lemonappdev.konsist.core.declaration

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
        val tag = this.tags.filter { it.startsWith("@param") }

        tag.map { parseToValuedBlockTag(it) }
    }

    val returnBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@return") }

        tag?.let { parseToBlockTag(it) }
    }

    val constructorBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@constructor") }

        tag?.let { parseToBlockTag(it) }
    }

    val receiverBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@receiver") }

        tag?.let { parseToBlockTag(it) }
    }

    val propertyBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith("@property") }

        tag.map { parseToValuedBlockTag(it) }
    }

    val throwsBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith("@throws") || it.startsWith("@exception") }

        tag.map { parseToValuedBlockTag(it) }
    }

    val sampleBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith("@sample") }

        tag.map { parseToValuedBlockTag(it) }
    }

    val seeBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith("@see") }

        tag.map { parseToValuedBlockTag(it) }
    }

    val authorBlockTags by lazy {
        val tag = this.tags.filter { it.startsWith("@author") }

        tag.map { parseToBlockTag(it) }
    }

    val sinceBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@since") }

        tag?.let { parseToBlockTag(it) }
    }

    val suppressBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@suppress") }

        tag?.let { parseToBlockTag(it) }
    }

    val versionBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@version") }

        tag?.let { parseToBlockTag(it) }
    }

    val propertySetterBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@propertySetter") }

        tag?.let { parseToBlockTag(it) }
    }

    val propertyGetterBlockTag by lazy {
        val tag = this.tags.firstOrNull { it.startsWith("@propertyGetter") }

        tag?.let { parseToBlockTag(it) }
    }

    val blockTags by lazy {
        (
                paramBlockTags + returnBlockTag + constructorBlockTag + receiverBlockTag +
                        propertyBlockTags + throwsBlockTags + sampleBlockTags +
                        seeBlockTags + authorBlockTags + sinceBlockTag + suppressBlockTag +
                        versionBlockTag + propertySetterBlockTag + propertyGetterBlockTag
                ).filterNotNull()
    }

    fun hasTags(vararg tags: String) = tags.all {
        blockTags
            .map { tag -> tag.name }
            .contains(it) ?: false
    }

    private fun parseToValuedBlockTag(sentence: String): KoValuedBlockTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .toMutableList()
            .also {
                it.removeFirst()
                it.removeFirst()
            }
            .joinToString(" ")

        return KoValuedBlockTag(parsed[0], parsed[1], description)
    }

    private fun parseToBlockTag(sentence: String): KoBlockTag {
        val parsed = sentence.split(" ")
        val description = parsed
            .toMutableList()
            .also { it.removeFirst() }
            .joinToString(" ")

        return KoBlockTag(parsed[0], description)
    }
}
