package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocAuthorTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocAuthorTagProviderListExtTest {
    @Test
    fun `authorTags returns author tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocAuthorTagProvider =
            mockk {
                every { authorTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocAuthorTagProvider =
            mockk {
                every { authorTags } returns listOf(tag3)
            }
        val declaration3: KoKDocAuthorTagProvider =
            mockk {
                every { authorTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.authorTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withAuthorTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocAuthorTagProvider =
            mockk {
                every { hasAuthorTags } returns true
            }
        val declaration2: KoKDocAuthorTagProvider =
            mockk {
                every { hasAuthorTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAuthorTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAuthorTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocAuthorTagProvider =
            mockk {
                every { hasAuthorTags } returns true
            }
        val declaration2: KoKDocAuthorTagProvider =
            mockk {
                every { hasAuthorTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAuthorTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
