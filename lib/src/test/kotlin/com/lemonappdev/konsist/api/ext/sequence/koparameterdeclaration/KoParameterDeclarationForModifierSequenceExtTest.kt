package com.lemonappdev.konsist.api.ext.sequence.koparameterdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withCrossInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withNoInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withVarargModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutCrossInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutNoInlineModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutVarargModifier
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForModifierSequenceExtTest {
    @Test
    fun `withVarargModifier() returns parameter with vararg modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withVarargModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutVarargModifier() returns parameter without vararg modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutVarargModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withNoInlineModifier() returns parameter with noInline modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withNoInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutNoInlineModifier() returns parameter without noInline modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutNoInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withCrossInlineModifier() returns parameter with crossInline modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withCrossInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutCrossInlineModifier() returns parameter without crossInline modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutCrossInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }
}
