package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.seeTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withSeeTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutSeeTags
import com.lemonappdev.konsist.api.provider.tag.KoKDocSeeTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocSeeTagProviderListExtTest {
    @Test
    fun `seeTags returns see tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocSeeTagProvider =
            mockk {
                every { seeTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocSeeTagProvider =
            mockk {
                every { seeTags } returns listOf(tag3)
            }
        val declaration3: KoKDocSeeTagProvider =
            mockk {
                every { seeTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.seeTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withSeeTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSeeTagProvider =
            mockk {
                every { hasSeeTags } returns true
            }
        val declaration2: KoKDocSeeTagProvider =
            mockk {
                every { hasSeeTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSeeTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSeeTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSeeTagProvider =
            mockk {
                every { hasSeeTags } returns true
            }
        val declaration2: KoKDocSeeTagProvider =
            mockk {
                every { hasSeeTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSeeTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
