package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.ext.list.provider.withNullableType
import com.lemonappdev.konsist.api.ext.list.provider.withoutNullableType
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsNullableProviderListExtTest"))
class KoNullableProviderListExtTest {
    @Test
    fun `withNullableType() returns type with Nullable basic type`() {
        // given
        val type1: KoNullableProvider =
            mockk {
                every { isNullable } returns true
            }
        val type2: KoNullableProvider =
            mockk {
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
        val type1: KoNullableProvider =
            mockk {
                every { isNullable } returns true
            }
        val type2: KoNullableProvider =
            mockk {
                every { isNullable } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutNullableType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
