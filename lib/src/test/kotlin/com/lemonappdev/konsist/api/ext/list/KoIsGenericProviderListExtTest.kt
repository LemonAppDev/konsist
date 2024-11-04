package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsGenericProviderListExtTest {
    @Test
    fun `withGeneric() returns type with generic type`() {
        // given
        val type1: KoIsGenericProvider =
            mockk {
                every { isGeneric } returns true
            }
        val type2: KoIsGenericProvider =
            mockk {
                every { isGeneric } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withGeneric()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutGeneric() returns type without generic type`() {
        // given
        val type1: KoIsGenericProvider =
            mockk {
                every { isGeneric } returns true
            }
        val type2: KoIsGenericProvider =
            mockk {
                every { isGeneric } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutGeneric()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
