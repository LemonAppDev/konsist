package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.ext.sequence.withAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withExtension
import com.lemonappdev.konsist.api.ext.sequence.withExternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withInfixModifier
import com.lemonappdev.konsist.api.ext.sequence.withInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withOperatorModifier
import com.lemonappdev.konsist.api.ext.sequence.withOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withReturnType
import com.lemonappdev.konsist.api.ext.sequence.withReturnTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withSuspendModifier
import com.lemonappdev.konsist.api.ext.sequence.withTailrecModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExtension
import com.lemonappdev.konsist.api.ext.sequence.withoutExternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInfixModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOperatorModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutReturnType
import com.lemonappdev.konsist.api.ext.sequence.withoutReturnTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutSuspendModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutTailrecModifier
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
    fun `withOperatorModifier() returns function1 with operator modifier`() {
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
    fun `withoutOperatorModifier() returns function2 without operator modifier`() {
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
    fun `withInlineModifier() returns function1 with inline modifier`() {
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
    fun `withoutInlineModifier() returns function2 without inline modifier`() {
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
    fun `withTailrecModifier() returns function1 with tailrec modifier`() {
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
    fun `withoutTailrecModifier() returns function2 without tailrec modifier`() {
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
    fun `withInfixModifier() returns function1 with infix modifier`() {
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
    fun `withoutInfixModifier() returns function2 without infix modifier`() {
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
    fun `withExternalModifier() returns function1 with external modifier`() {
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
    fun `withoutExternalModifier() returns function2 without external modifier`() {
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
    fun `withSuspendModifier() returns function1 with suspend modifier`() {
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
    fun `withoutSuspendModifier() returns function2 without suspend modifier`() {
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
    fun `withOpenModifier() returns function1 with open modifier`() {
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
    fun `withoutOpenModifier() returns function2 without open modifier`() {
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
    fun `withOverrideModifier() returns function1 with override modifier`() {
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
    fun `withoutOverrideModifier() returns function2 without override modifier`() {
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
    fun `withFinalModifier() returns function1 with final modifier`() {
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
    fun `withoutFinalModifier() returns function2 without final modifier`() {
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
    fun `withAbstractModifier() returns function1 with abstract modifier`() {
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
    fun `withoutAbstractModifier() returns function2 without abstract modifier`() {
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
    fun `withActualModifier() returns function1 with actual modifier`() {
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
    fun `withoutActualModifier() returns function2 without actual modifier`() {
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
    fun `withExpectModifier() returns function1 with expect modifier`() {
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
    fun `withoutExpectModifier() returns function2 without expect modifier`() {
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
    fun `withExtension() returns function1 which has extension`() {
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
    fun `withoutExtension() returns function2 which has not extension`() {
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
    fun `withReturnType() returns function1 which has return type`() {
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
    fun `withReturnType() returns functions which has one of given return types`() {
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
    fun `withoutReturnType() returns function2 which has not return type`() {
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
    fun `withoutReturnType(name) returns function3 which has not any given return types`() {
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
    fun `withReturnTypeOf() returns function1 which has given return type`() {
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
    fun `withoutReturnTypeOf() returns function2 which has not given return type`() {
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
    fun `withReturnTypeOf(KClass) returns functions which has one of given return types`() {
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
    fun `withoutReturnTypeOf(KClass) returns function which has not any of given return types`() {
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
