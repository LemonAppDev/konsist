package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertySetterTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocPropertySetterTagProviderListExtTest {
    @Test
    fun `withPropertySetterTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertySetterTagProvider =
            mockk {
                every { hasPropertySetterTag } returns true
            }
        val declaration2: KoKDocPropertySetterTagProvider =
            mockk {
                every { hasPropertySetterTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertySetterTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPropertySetterTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocPropertySetterTagProvider =
            mockk {
                every { hasPropertySetterTag } returns true
            }
        val declaration2: KoKDocPropertySetterTagProvider =
            mockk {
                every { hasPropertySetterTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertySetterTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
