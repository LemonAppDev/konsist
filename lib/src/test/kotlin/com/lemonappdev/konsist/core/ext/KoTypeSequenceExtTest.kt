package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoType
import com.lemonappdev.konsist.testdata.SampleClass
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeSequenceExtTest {
    @Test
    fun `withSourceType() returns type1 which has SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withSourceType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutSourceType() returns type2 which has not SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutSourceType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withSourceType(type) returns type1 which has SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutSourceType(type) returns type2 which has not SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }
}
