package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.sampleTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withSampleTags
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutSampleTags
import com.lemonappdev.konsist.api.provider.tag.KoKDocSampleTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocSampleTagProviderListExtTest {
    @Test
    fun `sampleTags returns sample tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocSampleTagProvider =
            mockk {
                every { sampleTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocSampleTagProvider =
            mockk {
                every { sampleTags } returns listOf(tag3)
            }
        val declaration3: KoKDocSampleTagProvider =
            mockk {
                every { sampleTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sampleTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withSampleTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSampleTagProvider =
            mockk {
                every { hasSampleTags } returns true
            }
        val declaration2: KoKDocSampleTagProvider =
            mockk {
                every { hasSampleTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSampleTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSampleTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSampleTagProvider =
            mockk {
                every { hasSampleTags } returns true
            }
        val declaration2: KoKDocSampleTagProvider =
            mockk {
                every { hasSampleTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSampleTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
