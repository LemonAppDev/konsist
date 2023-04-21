package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFunction
import com.lemonappdev.konsist.testdata.SampleType
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionSequenceExtTest {
    @Test
    fun `withOperatorModifier() returns function1 with operator modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOperatorModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOperatorModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOperatorModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOperatorModifier() returns function2 without operator modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOperatorModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOperatorModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOperatorModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withInlineModifier() returns function1 with inline modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasInlineModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasInlineModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutInlineModifier() returns function2 without inline modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasInlineModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasInlineModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withTailrecModifier() returns function1 with tailrec modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasTailrecModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasTailrecModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withTailrecModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutTailrecModifier() returns function2 without tailrec modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasTailrecModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasTailrecModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutTailrecModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withInfixModifier() returns function1 with infix modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasInfixModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasInfixModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withInfixModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutInfixModifier() returns function2 without infix modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasInfixModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasInfixModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutInfixModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExternalModifier() returns function1 with external modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExternalModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExternalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExternalModifier() returns function2 without external modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExternalModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExternalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withSuspendModifier() returns function1 with suspend modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasSuspendModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasSuspendModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withSuspendModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutSuspendModifier() returns function2 without suspend modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasSuspendModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasSuspendModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutSuspendModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withOpenModifier() returns function1 with open modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOpenModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOpenModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOpenModifier() returns function2 without open modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOpenModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOpenModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withOverrideModifier() returns function1 with override modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOverrideModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOverrideModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOverrideModifier() returns function2 without override modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasOverrideModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasOverrideModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withFinalModifier() returns function1 with final modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasFinalModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasFinalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutFinalModifier() returns function2 without final modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasFinalModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasFinalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withAbstractModifier() returns function1 with abstract modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasAbstractModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasAbstractModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutAbstractModifier() returns function2 without abstract modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasAbstractModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasAbstractModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withActualModifier() returns function1 with actual modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasActualModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasActualModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutActualModifier() returns function2 without actual modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasActualModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasActualModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExpectModifier() returns function1 with expect modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExpectModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExpectModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExpectModifier() returns function2 without expect modifier`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExpectModifier() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExpectModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExtension() returns function1 which has extension`() {
        // given
        val function1: KoFunction = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunction = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExtension() returns function2 which has not extension`() {
        // given
        val function1: KoFunction = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunction = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExplicitReturnType() returns function1 which has explicit return type`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExplicitReturnType() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExplicitReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withExplicitReturnType() returns functions which has one of given explicit return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2)
    }

    @Test
    fun `withoutExplicitReturnType() returns function2 which has not explicit return type`() {
        // given
        val function1: KoFunction = mockk {
            every { hasExplicitReturnType() } returns true
        }
        val function2: KoFunction = mockk {
            every { hasExplicitReturnType() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExplicitReturnType()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withoutExplicitReturnType(name) returns function3 which has not any given explicit return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val function1: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val function3: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName3
        }
        val functions = sequenceOf(function1, function2, function3)

        // when
        val sut = functions.withoutExplicitReturnType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(function3)
    }

    @Test
    fun `withExplicitReturnTypeOf() returns function1 which has given explicit return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExplicitReturnTypeOf() returns function2 which has not given explicit return type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val function1: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName1
        }
        val function2: KoFunction = mockk {
            every { explicitReturnType?.name } returns typeName2
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExplicitReturnTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }
}
