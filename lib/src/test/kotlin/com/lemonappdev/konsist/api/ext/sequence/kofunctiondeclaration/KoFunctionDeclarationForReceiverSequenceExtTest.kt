package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withReceiverType
import com.lemonappdev.konsist.api.ext.sequence.withReceiverTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutReceiverType
import com.lemonappdev.konsist.api.ext.sequence.withoutReceiverTypeOf
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForReceiverSequenceExtTest {
    @Test
    fun `withReceiverType() returns function with any receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReceiverType()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withReceiverType() returns functions with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReceiverType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReceiverType() returns function without any receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReceiverType()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withoutReceiverType(name) returns function without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReceiverType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }

    @Test
    fun `withReceiverTypeOf() returns function with given receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { receiverType?.name } returns "SampleType"
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { receiverType?.name } returns "OtherType"
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReceiverTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutReceiverTypeOf() returns function without given receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { receiverType?.name } returns "SampleType"
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { receiverType?.name } returns "OtherType"
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReceiverTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withReceiverTypeOf(KClass) returns functions with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReceiverTypeOf(KClass) returns function without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }
}
