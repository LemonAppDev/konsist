package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyGetterTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocPropertyGetterTagProviderListExtTest {
    @Test
    fun `withPropertyGetterTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertyGetterTagProvider =
            mockk {
                every { hasPropertyGetterTag } returns true
            }
        val declaration2: KoKDocPropertyGetterTagProvider =
            mockk {
                every { hasPropertyGetterTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyGetterTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPropertyGetterTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertyGetterTagProvider =
            mockk {
                every { hasPropertyGetterTag } returns true
            }
        val declaration2: KoKDocPropertyGetterTagProvider =
            mockk {
                every { hasPropertyGetterTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyGetterTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
