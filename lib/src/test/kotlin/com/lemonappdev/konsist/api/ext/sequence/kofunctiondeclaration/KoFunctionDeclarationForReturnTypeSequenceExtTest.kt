package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withExplicitReturnType
import com.lemonappdev.konsist.api.ext.sequence.withExplicitReturnTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutExplicitReturnType
import com.lemonappdev.konsist.api.ext.sequence.withoutExplicitReturnTypeOf
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
    fun `withExplicitReturnType() returns function with any return type`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExplicitReturnType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExplicitReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withExplicitReturnType() returns functions with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutExplicitReturnType() returns function without any return type`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExplicitReturnType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExplicitReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withoutExplicitReturnType(name) returns function without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }

    @Test
    fun `withExplicitReturnTypeOf() returns function with given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExplicitReturnTypeOf() returns function without given return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExplicitReturnTypeOf(KClass) returns functions with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withExplicitReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutExplicitReturnTypeOf(KClass) returns function without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutExplicitReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }
}
