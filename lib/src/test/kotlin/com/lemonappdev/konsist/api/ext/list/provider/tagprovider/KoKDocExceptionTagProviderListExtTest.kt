package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocExceptionTagProviderListExtTest {
    @Test
    fun `exceptionTags returns exception tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocExceptionTagProvider =
            mockk {
                every { exceptionTags } returns listOf(tag1, tag2)
            }
        val declaration2: KoKDocExceptionTagProvider =
            mockk {
                every { exceptionTags } returns listOf(tag3)
            }
        val declaration3: KoKDocExceptionTagProvider =
            mockk {
                every { exceptionTags } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.exceptionTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }

    @Test
    fun `withExceptionTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocExceptionTagProvider =
            mockk {
                every { hasExceptionTags } returns true
            }
        val declaration2: KoKDocExceptionTagProvider =
            mockk {
                every { hasExceptionTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExceptionTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExceptionTags() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocExceptionTagProvider =
            mockk {
                every { hasExceptionTags } returns true
            }
        val declaration2: KoKDocExceptionTagProvider =
            mockk {
                every { hasExceptionTags } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExceptionTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
