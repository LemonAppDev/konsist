package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAlias
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasSequenceExtTest {
    @Test
    fun `withType(name) returns typeAlias1 which has given type`() {
        // given
        val type1 = "SampleType"
        val type2 = "OtherType"
        val typeAlias1: KoTypeAlias = mockk {
            every { type } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type } returns type2
        }
        val properties = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = properties.withType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1)
    }

    @Test
    fun `withoutType(name) returns typeAlias2 which has not given type`() {
        // given
        val type1 = "SampleType"
        val type2 = "OtherType"
        val typeAlias1: KoTypeAlias = mockk {
            every { type } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type } returns type2
        }
        val properties = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = properties.withoutType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias2)
    }

    @Test
    fun `withTypeOf() returns typeAlias1 which has given type`() {
        // given
        val type1 = "() -> Unit"
        val type2 = "OtherType"
        val typeAlias1: KoTypeAlias = mockk {
            every { type } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type } returns type2
        }
        val properties = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = properties.withTypeOf<AliasName>()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1)
    }

    @Test
    fun `withoutTypeOf() returns typeAlias2 which has not given type`() {
        // given
        val type1 = "() -> Unit"
        val type2 = "OtherType"
        val typeAlias1: KoTypeAlias = mockk {
            every { type } returns type1
        }
        val typeAlias2: KoTypeAlias = mockk {
            every { type } returns type2
        }
        val properties = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = properties.withoutTypeOf<AliasName>()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias2)
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

typealias AliasName = () -> Unit
