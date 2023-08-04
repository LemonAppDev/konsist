package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocTagsProvider
import com.lemonappdev.konsist.core.declaration.KoKDocTagDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoValuedKDocTagDeclarationCore
import java.util.*

internal interface KoKDocTagsProviderCore : KoKDocTagsProvider, KoTextProviderCore, KoBaseProviderCore {
    override val tags: List<KoKDocTagDeclaration>
        get() {
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

            return tagsGroupingByValued.flatMap {
                when (it.key) {
                    true -> it.value.map { value -> parseToValuedTag(value.first, value.second) }
                    false -> it.value.map { value -> parseToTag(value.first, value.second) }
                }
            }
        }

    override val numTags: Int
        get() = tags.size

    override val paramTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.PARAM }
            .map { it as KoValuedKDocTagDeclaration }

    override val returnTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.RETURN }

    override val constructorTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.CONSTRUCTOR }

    override val receiverTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.RECEIVER }

    override val propertyTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.PROPERTY }
            .map { it as KoValuedKDocTagDeclaration }

    override val throwsTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.THROWS }
            .map { it as KoValuedKDocTagDeclaration }

    override val exceptionTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.EXCEPTION }
            .map { it as KoValuedKDocTagDeclaration }

    override val sampleTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.SAMPLE }
            .map { it as KoValuedKDocTagDeclaration }

    override val seeTags: List<KoValuedKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.SEE }
            .map { it as KoValuedKDocTagDeclaration }

    override val authorTags: List<KoKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.AUTHOR }

    override val sinceTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.SINCE }

    override val suppressTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.SUPPRESS }

    override val versionTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.VERSION }

    override val propertySetterTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.PROPERTY_SETTER }

    override val propertyGetterTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.PROPERTY_GETTER }

    override fun hasTags(vararg tags: KoKDocTag): Boolean = when {
        tags.isEmpty() -> {
            this.tags.isNotEmpty()
        }

        else -> {
            tags.all {
                this.tags
                    .map { tag -> tag.name }
                    .contains(it)
            }
        }
    }

    private fun parseToValuedTag(koKDocTag: KoKDocTag, sentence: String): KoValuedKDocTagDeclaration {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(2, parsed.size)
            .joinToString(" ")

        return KoValuedKDocTagDeclarationCore(koKDocTag, parsed[1], description)
    }

    private fun parseToTag(koKDocTag: KoKDocTag, sentence: String): KoKDocTagDeclaration {
        val parsed = sentence.split(" ")
        val description = parsed
            .subList(1, parsed.size)
            .joinToString(" ")

        return KoKDocTagDeclarationCore(koKDocTag, description)
    }
}
