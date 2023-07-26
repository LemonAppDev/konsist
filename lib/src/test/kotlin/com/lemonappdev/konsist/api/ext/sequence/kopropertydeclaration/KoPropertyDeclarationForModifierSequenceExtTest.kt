package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withValModifier
import com.lemonappdev.konsist.api.ext.sequence.withVarModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutValModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutVarModifier
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForModifierSequenceExtTest {
    @Test
    fun `withVarModifier() returns property with var modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutVarModifier() returns property without var modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withValModifier() returns property with val modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutValModifier() returns property without val modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }
}
