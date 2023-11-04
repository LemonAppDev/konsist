package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAliasProviderListExtTest {
    private interface SampleTestDeclaration : KoAliasProvider, KoNameProvider

    @Test
    fun `withAlias() returns declaration with any alias`() {
        // given
        val declarationName = "name"
        val alias1 = "AliasName"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns alias1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAlias()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAlias(name) returns declarations with one of given alias names`() {
        // given
        val declarationName = "name"
        val aliasName1 = "AliasName1"
        val aliasName2 = "AliasName2"
        val aliasName3 = "AliasName3"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName2
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName3
            }
        val declaration4: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2, declaration3, declaration4)

        // when
        val sut = declarations.withAlias(aliasName1, aliasName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAlias() returns declaration without any alias`() {
        // given
        val declarationName = "name"
        val alias1 = "AliasName"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns alias1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAlias()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAlias(name) returns declarations without alias with any of given names`() {
        // given
        val declarationName = "name"
        val aliasName1 = "AliasName1"
        val aliasName2 = "AliasName2"
        val aliasName3 = "AliasName3"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName2
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns aliasName3
            }
        val declaration4: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2, declaration3, declaration4)

        // when
        val sut = declarations.withoutAlias(aliasName1, aliasName2)

        // then
        sut shouldBeEqualTo listOf(declaration3, declaration4)
    }
}
