package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsWildcardProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsWildcardProviderListExtTest {
    @Test
    fun `withWildcard() returns import with wildcard`() {
        // given
        val import1: KoIsWildcardProvider =
            mockk {
                every { isWildcard } returns true
            }
        val import2: KoIsWildcardProvider =
            mockk {
                every { isWildcard } returns false
            }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withWildcard()

        // then
        sut shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withoutWildcard() returns import without wildcard`() {
        // given
        val import1: KoIsWildcardProvider =
            mockk {
                every { isWildcard } returns true
            }
        val import2: KoIsWildcardProvider =
            mockk {
                every { isWildcard } returns false
            }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withoutWildcard()

        // then
        sut shouldBeEqualTo listOf(import2)
    }
}
