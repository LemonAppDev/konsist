package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocTagsProviderListExtTest {
    @Test
    fun `tags returns tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { tags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { tags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { tags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.tags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `paramTags returns param tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { paramTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { paramTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.paramTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `propertyTags returns property tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { propertyTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { propertyTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { propertyTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.propertyTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `throwsTags returns throws tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { throwsTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { throwsTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { throwsTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.throwsTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `exceptionTags returns exception tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { exceptionTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { exceptionTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { exceptionTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.exceptionTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `sampleTags returns sample tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { sampleTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { sampleTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { sampleTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sampleTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `seeTags returns see tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { seeTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { seeTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { seeTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.seeTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `authorTags returns author tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { authorTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { authorTags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { authorTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.authorTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }
}
