package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.tags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withAllTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withTag
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutAllTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutTag
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutTags
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocTagProviderListExtTest {
    @Test
    fun `tags returns tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagProvider =
            mockk {
                every { tags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { tags } returns listOf(tag3)
            }
        val declaration3: KoKDocTagProvider =
            mockk {
                every { tags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.tags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTag(empty list) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTag(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTag(empty set) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTag(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTags(empty list) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTags(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTags(empty set) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTags(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTag(empty list) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTag(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTag(empty set) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTag(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTags(empty list) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTags(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTags(empty set) returns declaration with any tag`() {
        // given
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTags() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTags(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTag() returns declaration with any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTag(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTag(List) returns declaration with any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = listOf(tag1, tag2)

        // when
        val sut = declarations.withTag(tags)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTag(Set) returns declaration with any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(setOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(setOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = setOf(tag1, tag2)

        // when
        val sut = declarations.withTag(tags)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTag() returns declaration without all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTag(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTag(List) returns declaration without all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = listOf(tag1, tag2)

        // when
        val sut = declarations.withoutTag(tags)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTag(Set) returns declaration without all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(setOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(setOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = setOf(tag1, tag2)

        // when
        val sut = declarations.withoutTag(tags)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTags() returns declaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTags(List) returns declaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = listOf(tag1, tag2)

        // when
        val sut = declarations.withAllTags(tags)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTags(Set) returns declaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(setOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(setOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = setOf(tag1, tag2)

        // when
        val sut = declarations.withAllTags(tags)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTags() returns declaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTags(List) returns declaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(listOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = listOf(tag1, tag2)

        // when
        val sut = declarations.withoutAllTags(tags)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTags(Set) returns declaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasAllTags(setOf(tag1, tag2)) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(setOf(tag1, tag2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val tags = setOf(tag1, tag2)

        // when
        val sut = declarations.withoutAllTags(tags)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
