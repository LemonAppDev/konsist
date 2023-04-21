package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAlias
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasSequenceExtTest {
    @Test
    fun `withType(name) returns typeAliases which has one of given types`() {
        // given
        val type1 = "SampleType1"
        val type2 = "SampleType2"
        val type3 = "SampleType3"
        val typeAlias1: KoTypeAlias = mockk {
            every { type.sourceType } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type.sourceType } returns type2
        }
        val typeAlias3: KoTypeAlias = mockk {
            every { type.sourceType } returns type3
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2, typeAlias3)

        // when
        val sut = typeAliases.withSourceType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1, typeAlias2)
    }

    @Test
    fun `withoutType(name) returns typeAlias3 which has not any given type`() {
        // given
        val type1 = "SampleType1"
        val type2 = "SampleType2"
        val type3 = "SampleType3"
        val typeAlias1: KoTypeAlias = mockk {
            every { type.sourceType } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type.sourceType } returns type2
        }
        val typeAlias3: KoTypeAlias = mockk {
            every { type.sourceType } returns type3
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2, typeAlias3)

        // when
        val sut = typeAliases.withoutSourceType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias3)
    }

    @Test
    fun `withActualModifier() returns typeAlias1 with actual modifier`() {
        // given
        val typeAlias1: KoTypeAlias = mockk {
            every { hasActualModifier() } returns true
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { hasActualModifier() } returns false
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = typeAliases.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1)
    }

    @Test
    fun `withoutActualModifier() returns typeAlias2 without actual modifier`() {
        // given
        val typeAlias1: KoTypeAlias = mockk {
            every { hasActualModifier() } returns true
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { hasActualModifier() } returns false
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = typeAliases.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias2)
    }
}
