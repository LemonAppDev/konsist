package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocSinceTagProviderListExtTest {
    @Test
    fun `withSinceTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSinceTagProvider =
            mockk {
                every { hasSinceTag } returns true
            }
        val declaration2: KoKDocSinceTagProvider =
            mockk {
                every { hasSinceTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSinceTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSinceTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocSinceTagProvider =
            mockk {
                every { hasSinceTag } returns true
            }
        val declaration2: KoKDocSinceTagProvider =
            mockk {
                every { hasSinceTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSinceTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
