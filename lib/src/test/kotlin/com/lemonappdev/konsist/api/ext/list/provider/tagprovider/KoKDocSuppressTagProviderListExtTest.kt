package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocSuppressTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocSuppressTagProviderListExtTest {
    @Test
    fun `withSuppressTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSuppressTagProvider =
            mockk {
                every { hasSuppressTag } returns true
            }
        val declaration2: KoKDocSuppressTagProvider =
            mockk {
                every { hasSuppressTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSuppressTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSuppressTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSuppressTagProvider =
            mockk {
                every { hasSuppressTag } returns true
            }
        val declaration2: KoKDocSuppressTagProvider =
            mockk {
                every { hasSuppressTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSuppressTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
