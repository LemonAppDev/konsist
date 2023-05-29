package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withReturnType
import com.lemonappdev.konsist.api.ext.sequence.withReturnTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutReturnType
import com.lemonappdev.konsist.api.ext.sequence.withoutReturnTypeOf
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForReturnTypeSequenceExtTest {
    @Test
    fun `withReturnType() returns function with any return type`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReturnType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withReturnType() returns functions with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReturnType() returns function without any return type`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReturnType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withoutReturnType(name) returns function without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }

    @Test
    fun `withReturnTypeOf() returns function with given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutReturnTypeOf() returns function without given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withReturnTypeOf(KClass) returns functions with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns function without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { returnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }
}
