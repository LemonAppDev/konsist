package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageMatchingPathProviderListExtTest {
    @Test
    fun `withMatchingPath() returns declaration which has matching declaration path`() {
        // given
        val declaration1: KoPackageMatchingPathProvider =
            mockk {
                every { hasMatchingPath } returns true
            }
        val declaration2: KoPackageMatchingPathProvider =
            mockk {
                every { hasMatchingPath } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withMatchingPath()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutMatchingPath() returns declaration which has not matching declaration path`() {
        // given
        val declaration1: KoPackageMatchingPathProvider =
            mockk {
                every { hasMatchingPath } returns true
            }
        val declaration2: KoPackageMatchingPathProvider =
            mockk {
                every { hasMatchingPath } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutMatchingPath()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
