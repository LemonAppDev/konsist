package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withReceiverTag
import com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider.withoutReceiverTag
import com.lemonappdev.konsist.api.provider.tag.KoKDocReceiverTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocReceiverTagProviderListExtTest {
    @Test
    fun `withReceiverTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocReceiverTagProvider =
            mockk {
                every { hasReceiverTag } returns true
            }
        val declaration2: KoKDocReceiverTagProvider =
            mockk {
                every { hasReceiverTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReceiverTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocReceiverTagProvider =
            mockk {
                every { hasReceiverTag } returns true
            }
        val declaration2: KoKDocReceiverTagProvider =
            mockk {
                every { hasReceiverTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
