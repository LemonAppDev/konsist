package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocConstructorTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocConstructorTagProviderListExtTest {
    @Test
    fun `withConstructorTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocConstructorTagProvider =
            mockk {
                every { hasConstructorTag } returns true
            }
        val declaration2: KoKDocConstructorTagProvider =
            mockk {
                every { hasConstructorTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstructorTag()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstructorTag() returns declaration with any tag`() {
        // given
        val declaration1: KoKDocConstructorTagProvider =
            mockk {
                every { hasConstructorTag } returns true
            }
        val declaration2: KoKDocConstructorTagProvider =
            mockk {
                every { hasConstructorTag } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstructorTag()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
