package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionProviderListExtTest {
    @Test
    fun `withStarProjection() returns import with wildcard`() {
        // given
        val import1: KoStarProjectionProvider =
            mockk {
                every { isStarProjection } returns true
            }
        val import2: KoStarProjectionProvider =
            mockk {
                every { isStarProjection } returns false
            }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withStarProjection()

        // then
        sut shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withoutStarProjection() returns import without wildcard`() {
        // given
        val import1: KoStarProjectionProvider =
            mockk {
                every { isStarProjection } returns true
            }
        val import2: KoStarProjectionProvider =
            mockk {
                every { isStarProjection } returns false
            }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withoutStarProjection()

        // then
        sut shouldBeEqualTo listOf(import2)
    }
}
