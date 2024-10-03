package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsInitializedProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsInitializedProviderListExtTest {
    @Test
    fun `withInitialized() returns declaration that has been initialized`() {
        // given
        val declaration1: KoIsInitializedProvider =
            mockk {
                every { isInitialized } returns true
            }
        val declaration2: KoIsInitializedProvider =
            mockk {
                every { isInitialized } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInitialized()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInitialized() returns declaration that has not been initialized`() {
        // given
        val declaration1: KoIsInitializedProvider =
            mockk {
                every { isInitialized } returns true
            }
        val declaration2: KoIsInitializedProvider =
            mockk {
                every { isInitialized } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInitialized()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
