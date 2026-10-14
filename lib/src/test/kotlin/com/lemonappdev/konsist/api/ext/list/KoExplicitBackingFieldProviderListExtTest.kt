package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoExplicitBackingFieldDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitBackingFieldProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldProviderListExtTest {
    @Test
    fun `explicitBackingFields returns explicit backing fields from all declarations`() {
        // given
        val explicitBackingField1: KoExplicitBackingFieldDeclaration = mockk()
        val explicitBackingField2: KoExplicitBackingFieldDeclaration = mockk()
        val declaration1: KoExplicitBackingFieldProvider =
            mockk {
                every { explicitBackingField } returns explicitBackingField1
            }
        val declaration2: KoExplicitBackingFieldProvider =
            mockk {
                every { explicitBackingField } returns explicitBackingField2
            }
        val declaration3: KoExplicitBackingFieldProvider =
            mockk {
                every { explicitBackingField } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.explicitBackingFields

        // then
        sut shouldBeEqualTo listOf(explicitBackingField1, explicitBackingField2)
    }

    @Test
    fun `withExplicitBackingField() returns declaration with explicit backing field`() {
        // given
        val declaration1: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField() } returns true
            }
        val declaration2: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitBackingField()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExplicitBackingField() returns declaration without explicit backing field`() {
        // given
        val declaration1: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField() } returns true
            }
        val declaration2: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitBackingField()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExplicitBackingField{} returns declaration with explicit backing field which satisfy predicate`() {
        // given
        val predicate: (KoExplicitBackingFieldDeclaration) -> Boolean = { it.hasValue("value") }
        val declaration1: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField(predicate) } returns true
            }
        val declaration2: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExplicitBackingField(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExplicitBackingField{} returns declaration without explicit backing field which satisfy predicate`() {
        // given
        val predicate: (KoExplicitBackingFieldDeclaration) -> Boolean = { it.hasValue("value") }
        val declaration1: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField(predicate) } returns true
            }
        val declaration2: KoExplicitBackingFieldProvider =
            mockk {
                every { hasExplicitBackingField(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExplicitBackingField(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
