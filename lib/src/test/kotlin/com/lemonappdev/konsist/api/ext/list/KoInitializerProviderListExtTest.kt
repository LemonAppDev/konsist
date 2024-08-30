package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoInitializerProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitializerProviderListExtTest {
    @Test
    fun `withInitialized() returns declaration that has been initialized`() {
        // given
        val declaration1: KoInitializerProvider =
            mockk {
                every { isInitialized } returns true
            }
        val declaration2: KoInitializerProvider =
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
        val declaration1: KoInitializerProvider =
            mockk {
                every { isInitialized } returns true
            }
        val declaration2: KoInitializerProvider =
            mockk {
                every { isInitialized } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInitialized()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImplementation() returns declaration with implementation`() {
        // given
        val declaration1: KoInitializerProvider =
            mockk {
                every { hasImplementation } returns true
            }
        val declaration2: KoInitializerProvider =
            mockk {
                every { hasImplementation } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImplementation()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImplementation() returns declaration without implementation`() {
        // given
        val declaration1: KoInitializerProvider =
            mockk {
                every { hasImplementation } returns true
            }
        val declaration2: KoInitializerProvider =
            mockk {
                every { hasImplementation } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImplementation()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
