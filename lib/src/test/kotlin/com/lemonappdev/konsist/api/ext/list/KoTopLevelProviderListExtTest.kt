package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTopLevelProviderListExtTest {
    @Test
    fun `withTopLevel() returns declaration which is top level declaration`() {
        // given
        val declaration1: KoTopLevelProvider = mockk {
            every { isTopLevel } returns true
        }
        val declaration2: KoTopLevelProvider = mockk {
            every { isTopLevel } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration which is not top level declaration`() {
        // given
        val declaration1: KoTopLevelProvider = mockk {
            every { isTopLevel } returns true
        }
        val declaration2: KoTopLevelProvider = mockk {
            every { isTopLevel } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTopLevel()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
