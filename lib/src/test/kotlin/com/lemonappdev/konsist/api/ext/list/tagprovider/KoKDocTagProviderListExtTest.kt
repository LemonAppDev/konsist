package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
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
    fun `withTag() returns declaration with any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val declaration1: KoKDocTagProvider =
            mockk {
                every { hasTag(tag1, tag2) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(tag1, tag2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTag(tag1, tag2)

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
                every { hasTag(tag1, tag2) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasTag(tag1, tag2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTag(tag1, tag2)

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
                every { hasAllTags(tag1, tag2) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(tag1, tag2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTags(tag1, tag2)

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
                every { hasAllTags(tag1, tag2) } returns true
            }
        val declaration2: KoKDocTagProvider =
            mockk {
                every { hasAllTags(tag1, tag2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
