package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationSequenceExtTest {
    @Test
    fun `withOperatorModifier() returns function with operator modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOperatorModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOperatorModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOperatorModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOperatorModifier() returns function without operator modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOperatorModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOperatorModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOperatorModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withInlineModifier() returns function with inline modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasInlineModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasInlineModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutInlineModifier() returns function without inline modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasInlineModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasInlineModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withTailrecModifier() returns function with tailrec modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasTailrecModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasTailrecModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withTailrecModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutTailrecModifier() returns function without tailrec modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasTailrecModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasTailrecModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutTailrecModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withInfixModifier() returns function with infix modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasInfixModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasInfixModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withInfixModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutInfixModifier() returns function without infix modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasInfixModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasInfixModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutInfixModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExternalModifier() returns function with external modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExternalModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExternalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExternalModifier() returns function without external modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExternalModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExternalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withSuspendModifier() returns function with suspend modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasSuspendModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasSuspendModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withSuspendModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutSuspendModifier() returns function without suspend modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasSuspendModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasSuspendModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutSuspendModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withOpenModifier() returns function with open modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOpenModifier() returns function without open modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withOverrideModifier() returns function with override modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutOverrideModifier() returns function without override modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withFinalModifier() returns function with final modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutFinalModifier() returns function without final modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withAbstractModifier() returns function with abstract modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutAbstractModifier() returns function without abstract modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withActualModifier() returns function with actual modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutActualModifier() returns function without actual modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExpectModifier() returns function with expect modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExpectModifier() returns function without expect modifier`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

    @Test
    fun `withExtension() returns function with extension`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExtension() returns function without extension`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }

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
