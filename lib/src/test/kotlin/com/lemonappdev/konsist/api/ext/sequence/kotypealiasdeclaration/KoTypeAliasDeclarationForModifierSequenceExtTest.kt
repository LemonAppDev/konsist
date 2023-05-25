package com.lemonappdev.konsist.api.ext.sequence.kotypealiasdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutActualModifier
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForModifierSequenceExtTest {
    @Test
    fun `withActualModifier() returns typeAlias with actual modifier`() {
        // given
        val typeAlias1: KoTypeAliasDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val typeAlias2: KoTypeAliasDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = typeAliases.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1)
    }

    @Test
    fun `withoutActualModifier() returns typeAlias without actual modifier`() {
        // given
        val typeAlias1: KoTypeAliasDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val typeAlias2: KoTypeAliasDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2)

        // when
        val sut = typeAliases.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias2)
    }
}
