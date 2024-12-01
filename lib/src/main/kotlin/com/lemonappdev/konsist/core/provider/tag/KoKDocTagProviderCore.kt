package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider
import com.lemonappdev.konsist.core.declaration.KoKDocTagDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoValuedKDocTagDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.kdoc.psi.impl.KDocLink
import org.jetbrains.kotlin.kdoc.psi.impl.KDocTag

internal interface KoKDocTagProviderCore :
    KoKDocTagProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    val kDocTags: List<KDocTag>

    override val tags: List<KoKDocTagDeclaration>
        get() = kDocTags.map { tag ->
            val name = KoKDocTag
                .values()
                .firstOrNull { value -> value.type.removePrefix("@") == tag.name }

            if (name == null) {
                return@map null
            }

            val value = tag
                .children
                .filterIsInstance<KDocLink>()
                .firstOrNull()
                ?.text

            val description = tag.getContent()

            if (value != null) {
                KoValuedKDocTagDeclarationCore(name, value, description)
            } else {
                KoKDocTagDeclarationCore(name, description)
            }
        }.filterNotNull()

    override val numTags: Int
        get() = tags.size

    override fun hasTags(): Boolean = tags.isNotEmpty()

    override fun hasTag(
        tag: KoKDocTag,
        vararg tags: KoKDocTag,
    ): Boolean = hasTag(listOf(tag, *tags))

    override fun hasTag(tags: Collection<KoKDocTag>): Boolean =
        when {
            tags.isEmpty() -> hasTags()
            else -> tags.any { this.tags.any { tag -> tag.name == it } }
        }

    override fun hasAllTags(
        tag: KoKDocTag,
        vararg tags: KoKDocTag,
    ): Boolean = hasAllTags(listOf(tag, *tags))

    override fun hasAllTags(tags: Collection<KoKDocTag>): Boolean =
        when {
            tags.isEmpty() -> hasTags()
            else -> this.tags.map { it.name }.containsAll(tags)
        }
}
