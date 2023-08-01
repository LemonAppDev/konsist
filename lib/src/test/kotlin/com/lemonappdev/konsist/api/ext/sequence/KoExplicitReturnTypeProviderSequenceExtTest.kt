package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitReturnTypeProviderSequenceExtTest {
    @Test
    fun `withExplicitReturnType() returns declaration with any return type`() {
        // given
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { hasExplicitReturnType } returns true
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { hasExplicitReturnType } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExplicitReturnType() returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declaration3: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExplicitReturnType() returns declaration without any return type`() {
        // given
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { hasExplicitReturnType } returns true
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { hasExplicitReturnType } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExplicitReturnType(name) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declaration3: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withExplicitReturnTypeOf() returns declaration with given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExplicitReturnTypeOf() returns declaration without given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExplicitReturnTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declaration3: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExplicitReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExplicitReturnTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val declaration2: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val declaration3: KoExplicitReturnTypeProvider = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExplicitReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
