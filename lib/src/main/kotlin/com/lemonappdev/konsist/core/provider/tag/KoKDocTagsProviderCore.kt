package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider
import com.lemonappdev.konsist.core.declaration.KoKDocTagDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoValuedKDocTagDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import java.util.*

internal interface KoKDocTagsProviderCore : KoKDocTagsProvider, KoTextProviderCore, KoBaseProviderCore {
    override val tags: List<KoKDocTagDeclaration>
        get() {
            val regex = "@(\\w+)".toRegex()

            val tagsAsStringList = text
                .substringAfter("@", "")
                .split("${EndOfLine.UNIX.value}@")
                .map { ("@${it.replaceFirstChar { char -> char.lowercase(Locale.getDefault()) }}").trimEnd() }

            val tagsWithName = tagsAsStringList
                .filterNot { it == "@" }
                .flatMap { regex.findAll(it) }
                .mapNotNull { KoKDocTag.entries.firstOrNull { tag -> tag.type == it.value } }
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

    @Deprecated(
        """
        Will be removed in v1.0.0. 
        If you passed one argument - replace with `hasTag`, otherwise with `hasAllTags`.
       """,
    )
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

    override fun hasTags(): Boolean = tags.isNotEmpty()

    override fun hasTag(tag: KoKDocTag, vararg tags: KoKDocTag): Boolean {
        val givenKoKDocTags = tags.toList() + tag

        return givenKoKDocTags.any { this.tags.any { tag -> tag.name == it } }
    }

    override fun hasAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): Boolean =
        this.tags.map { it.name }.containsAll(tags.toList() + tag)

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
