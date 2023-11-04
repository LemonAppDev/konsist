package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeProviderListExtTest {
    @Test
    fun `withKotlinType() returns type with Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinType() returns type without Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withKotlinBasicType() returns type with Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinBasicType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinBasicType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinBasicType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinBasicType() returns type without Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinBasicType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinBasicType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinBasicType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withKotlinCollectionType() returns type with Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinCollectionType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinCollectionType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinCollectionType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinCollectionType() returns type without Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinCollectionType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinCollectionType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinCollectionType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
