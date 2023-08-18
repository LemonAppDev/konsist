package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNullableTypeProviderListExtTest {
    @Test
    fun `withNullableType() returns type with Nullable basic type`() {
        // given
        val type1: KoNullableTypeProvider = mockk {
            every { isNullable } returns true
        }
        val type2: KoNullableTypeProvider = mockk {
            every { isNullable } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withNullableType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutNullableType() returns type without Nullable basic type`() {
        // given
        val type1: KoNullableTypeProvider = mockk {
            every { isNullable } returns true
        }
        val type2: KoNullableTypeProvider = mockk {
            every { isNullable } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutNullableType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
