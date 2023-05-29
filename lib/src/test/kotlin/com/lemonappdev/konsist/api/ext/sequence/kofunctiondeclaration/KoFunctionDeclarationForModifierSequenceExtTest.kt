package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withExternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withInfixModifier
import com.lemonappdev.konsist.api.ext.sequence.withInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withOperatorModifier
import com.lemonappdev.konsist.api.ext.sequence.withOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withSuspendModifier
import com.lemonappdev.konsist.api.ext.sequence.withTailrecModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInfixModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOperatorModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutSuspendModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutTailrecModifier
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForModifierSequenceExtTest {
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
}
