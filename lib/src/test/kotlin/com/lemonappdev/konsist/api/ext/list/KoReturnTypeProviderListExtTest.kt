package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReturnTypeProviderListExtTest {
    @Test
    fun `returnTypes returns return types from all declarations`() {
        // given
        val returnType1: KoTypeDeclaration = mockk()
        val returnType2: KoTypeDeclaration = mockk()
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType } returns returnType1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType } returns returnType2
        }
        val declaration3: KoReturnTypeProvider = mockk {
            every { returnType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.returnTypes

        // then
        sut shouldBeEqualTo listOf(returnType1, returnType2)
    }

    @Test
    fun `withReturnType() returns declaration with any return type`() {
        // given
        val declaration1: KoReturnTypeProvider = mockk {
            every { hasReturnType } returns true
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { hasReturnType } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnType() returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }
        val declaration3: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReturnType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnType() returns declaration without any return type`() {
        // given
        val declaration1: KoReturnTypeProvider = mockk {
            every { hasReturnType } returns true
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { hasReturnType } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReturnType(name) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }
        val declaration3: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReturnType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withReturnTypeOf(KClass) returns declarations with given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }
        val declaration3: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns declaration without given return type`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName1
        }
        val declaration2: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName2
        }
        val declaration3: KoReturnTypeProvider = mockk {
            every { returnType?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
