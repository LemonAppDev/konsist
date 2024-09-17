package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Deprecated("Will be removed in version 0.18.0")
class KoIsGenericTypeProviderListExtTest {
    @Test
    fun `withGenericType() returns type with generic type`() {
        // given
        val type1: KoIsGenericTypeProvider =
            mockk {
                every { isGenericType } returns true
            }
        val type2: KoIsGenericTypeProvider =
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
        val type1: KoIsGenericTypeProvider =
            mockk {
                every { isGenericType } returns true
            }
        val type2: KoIsGenericTypeProvider =
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
