package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withReceiver
import com.lemonappdev.konsist.api.ext.sequence.withReceiverOf
import com.lemonappdev.konsist.api.ext.sequence.withoutReceiver
import com.lemonappdev.konsist.api.ext.sequence.withoutReceiverOf
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
    fun `withReceiver() returns function with any receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReceiver()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withReceiver() returns functions with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns true
            every { hasReceiver(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReceiver(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReceiver() returns function without any receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReceiver()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withoutReceiver(name) returns function without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns true
            every { hasReceiver(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReceiver(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }

    @Test
    fun `withReceiverOf() returns function with given receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { receiver?.name } returns "SampleType"
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { receiver?.name } returns "OtherType"
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withReceiverOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutReceiverOf() returns function without given receiver`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { receiver?.name } returns "SampleType"
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { receiver?.name } returns "OtherType"
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutReceiverOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withReceiverOf(KClass) returns functions with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns true
            every { hasReceiver(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withReceiverOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutReceiverOf(KClass) returns function without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns true
            every { hasReceiver(typeName2) } returns false
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns true
        }
        val function3: KoFunctionDeclarationImpl = mockk {
            every { hasReceiver(typeName1) } returns false
            every { hasReceiver(typeName2) } returns false
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutReceiverOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }
}
