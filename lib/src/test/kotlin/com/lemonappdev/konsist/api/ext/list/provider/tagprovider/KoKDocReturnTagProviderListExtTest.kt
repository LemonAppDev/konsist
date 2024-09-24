package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withReturnTag
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutReturnTag
import com.lemonappdev.konsist.api.provider.tag.KoKDocReturnTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocReturnTagProviderListExtTest {
    @Test
    fun `withReturnTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocReturnTagProvider =
            mockk {
                every { hasReturnTag } returns true
            }
        val declaration2: KoKDocReturnTagProvider =
            mockk {
                every { hasReturnTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReturnTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocReturnTagProvider =
            mockk {
                every { hasReturnTag } returns true
            }
        val declaration2: KoKDocReturnTagProvider =
            mockk {
                every { hasReturnTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
