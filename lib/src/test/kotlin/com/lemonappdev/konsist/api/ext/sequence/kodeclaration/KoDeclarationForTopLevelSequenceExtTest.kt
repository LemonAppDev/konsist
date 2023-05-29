package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.ext.sequence.withTopLevel
import com.lemonappdev.konsist.api.ext.sequence.withoutTopLevel
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForTopLevelSequenceExtTest {
    @Test
    fun `withTopLevel() returns declaration which is top level declaration`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration which is not top level declaration`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
