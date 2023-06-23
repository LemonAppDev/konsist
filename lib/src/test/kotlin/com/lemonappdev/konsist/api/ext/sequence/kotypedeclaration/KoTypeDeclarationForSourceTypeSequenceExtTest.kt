package com.lemonappdev.konsist.api.ext.sequence.kotypedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withSourceType
import com.lemonappdev.konsist.api.ext.sequence.withSourceTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutSourceType
import com.lemonappdev.konsist.api.ext.sequence.withoutSourceTypeOf
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForSourceTypeSequenceExtTest {
    @Test
    fun `withSourceTypeOf() returns type with SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withSourceTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutSourceTypeOf() returns type without SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutSourceTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withSourceTypeOf(KClass) returns types with one of given source types`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutSourceTypeOf(KClass) returns type without any of given source types`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }

    @Test
    fun `withSourceType(type) returns type with given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withSourceType(type) returns types with one of given source types`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutSourceType(type) returns type without given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withoutSourceType(type) returns type without any of given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }
}
