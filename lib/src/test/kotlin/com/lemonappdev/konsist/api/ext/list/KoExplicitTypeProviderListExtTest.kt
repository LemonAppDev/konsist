package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitTypeProviderListExtTest {
    @Test
    fun `withExplicitType() returns declaration with any type`() {
        // given
        val declaration1: KoExplicitTypeProvider = mockk {
            every { hasExplicitType() } returns true
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { hasExplicitType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExplicitType(name) returns declarations with one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns true
            every { hasExplicitType(typeName2) } returns false
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns true
        }
        val declaration3: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExplicitType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExplicitType() returns declaration without any type`() {
        // given
        val declaration1: KoExplicitTypeProvider = mockk {
            every { hasExplicitType() } returns true
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { hasExplicitType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExplicitType(name) returns declaration without any of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns true
            every { hasExplicitType(typeName2) } returns false
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns true
        }
        val declaration3: KoExplicitTypeProvider = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExplicitType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withExplicitTypeOf() with KClass returns declaration with given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName1
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExplicitTypeOf() with KClass returns declaration without given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName1
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExplicitTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName1
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName2
        }
        val declaration3: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExplicitTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExplicitTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName1
        }
        val declaration2: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName2
        }
        val declaration3: KoExplicitTypeProvider = mockk {
            every { explicitType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExplicitTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
