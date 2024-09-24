package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withVersionTag
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutVersionTag
import com.lemonappdev.konsist.api.provider.tag.KoKDocVersionTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocVersionTagProviderListExtTest {
    @Test
    fun `withVersionTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocVersionTagProvider =
            mockk {
                every { hasVersionTag } returns true
            }
        val declaration2: KoKDocVersionTagProvider =
            mockk {
                every { hasVersionTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVersionTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVersionTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocVersionTagProvider =
            mockk {
                every { hasVersionTag } returns true
            }
        val declaration2: KoKDocVersionTagProvider =
            mockk {
                every { hasVersionTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVersionTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
