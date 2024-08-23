package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsNullableProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsNullableProviderListExtTest {
    @Test
    fun `withNullable() returns type with Nullable basic type`() {
        // given
        val type1: KoIsNullableProvider =
            mockk {
                every { isNullable } returns true
            }
        val type2: KoIsNullableProvider =
            mockk {
                every { isNullable } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withNullable()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutNullable() returns type without Nullable basic type`() {
        // given
        val type1: KoIsNullableProvider =
            mockk {
                every { isNullable } returns true
            }
        val type2: KoIsNullableProvider =
            mockk {
                every { isNullable } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutNullable()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
