package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalDeclarationProviderListExtTest {
    @Test
    fun `localDeclarations returns local declarations from all declarations`() {
        // given
        val localDeclaration1: KoBaseDeclaration = mockk()
        val localDeclaration2: KoBaseDeclaration = mockk()
        val localDeclaration3: KoBaseDeclaration = mockk()
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(localDeclaration1, localDeclaration2)
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(localDeclaration3)
            }
        val declaration3: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localDeclarations

        // then
        sut shouldBeEqualTo listOf(localDeclaration1, localDeclaration2, localDeclaration3)
    }

    @Test
    fun `withLocalDeclarations() returns declaration with any declaration`() {
        // given
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclarations() } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclarations() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalDeclarations() returns declaration without any declaration`() {
        // given
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclarations() } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclarations() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalDeclaration{} returns declaration with local declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclaration(predicate) } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalDeclaration(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalDeclaration{} returns declaration without local declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclaration(predicate) } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasLocalDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalDeclaration(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllLocalDeclarations{} returns declaration with all local declarations satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasAllLocalDeclarations(predicate) } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasAllLocalDeclarations(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllLocalDeclarations{} returns declaration with all local declarations which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoLocalDeclarationProvider =
            mockk {
                every { hasAllLocalDeclarations(predicate) } returns true
            }
        val declaration2: KoLocalDeclarationProvider =
            mockk {
                every { hasAllLocalDeclarations(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalDeclaration{} returns declaration with declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoBaseDeclaration>) -> Boolean =
            { it.all { koDeclaration -> (koDeclaration as KoNameProvider).hasNameEndingWith(suffix) } }
        val declaration1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val declaration2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val localDeclaration1: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(declaration1)
            }
        val localDeclaration2: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(declaration2)
            }
        val localDeclaration3: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns emptyList()
            }
        val declarations = listOf(localDeclaration1, localDeclaration2, localDeclaration3)

        // when
        val sut = declarations.withLocalDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(localDeclaration1, localDeclaration3)
    }

    @Test
    fun `withoutLocalDeclaration{} returns declaration without declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoBaseDeclaration>) -> Boolean =
            { it.all { koDeclaration -> (koDeclaration as KoNameProvider).hasNameEndingWith(suffix) } }
        val declaration1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val declaration2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val localDeclaration1: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(declaration1)
            }
        val localDeclaration2: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns listOf(declaration2)
            }
        val localDeclaration3: KoLocalDeclarationProvider =
            mockk {
                every { localDeclarations } returns emptyList()
            }
        val declarations = listOf(localDeclaration1, localDeclaration2, localDeclaration3)

        // when
        val sut = declarations.withoutLocalDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(localDeclaration2)
    }
}
