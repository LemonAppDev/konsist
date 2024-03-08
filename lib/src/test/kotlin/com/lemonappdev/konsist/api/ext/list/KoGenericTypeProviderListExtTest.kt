package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeProviderListExtTest {
    @Test
    fun `withGenericType() returns type with generic type`() {
        // given
        val type1: KoGenericTypeProvider =
            mockk {
                every { isGenericType } returns true
            }
        val type2: KoGenericTypeProvider =
            mockk {
                every { isGenericType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withGenericType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutGenericType() returns type without generic type`() {
        // given
        val type1: KoGenericTypeProvider =
            mockk {
                every { isGenericType } returns true
            }
        val type2: KoGenericTypeProvider =
            mockk {
                every { isGenericType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutGenericType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
