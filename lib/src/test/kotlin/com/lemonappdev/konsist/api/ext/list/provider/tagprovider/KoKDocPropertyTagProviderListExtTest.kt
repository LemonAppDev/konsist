package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocPropertyTagProviderListExtTest {
    @Test
    fun `propertyTags returns property tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocPropertyTagProvider =
            mockk {
                every { propertyTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocPropertyTagProvider =
            mockk {
                every { propertyTags } returns listOf(tag3)
            }
        val declaration3: KoKDocPropertyTagProvider =
            mockk {
                every { propertyTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.propertyTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withPropertyTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertyTagProvider =
            mockk {
                every { hasPropertyTags } returns true
            }
        val declaration2: KoKDocPropertyTagProvider =
            mockk {
                every { hasPropertyTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPropertyTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertyTagProvider =
            mockk {
                every { hasPropertyTags } returns true
            }
        val declaration2: KoKDocPropertyTagProvider =
            mockk {
                every { hasPropertyTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
