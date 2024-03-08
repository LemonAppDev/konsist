package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocParamTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocParamTagProviderListExtTest {
    @Test
    fun `paramTags returns param tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocParamTagProvider =
            mockk {
                every { paramTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocParamTagProvider =
            mockk {
                every { paramTags } returns listOf(tag3)
            }
        val declaration3: KoKDocParamTagProvider =
            mockk {
                every { paramTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.paramTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withParamTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocParamTagProvider =
            mockk {
                every { hasParamTags } returns true
            }
        val declaration2: KoKDocParamTagProvider =
            mockk {
                every { hasParamTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParamTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParamTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocParamTagProvider =
            mockk {
                every { hasParamTags } returns true
            }
        val declaration2: KoKDocParamTagProvider =
            mockk {
                every { hasParamTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParamTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
