package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAliasProviderListExtTest {
    private interface SampleTestDeclaration :
        KoAliasProvider,
        KoNameProvider

    @Test
    fun `importAliases returns import aliases from all declarations`() {
        // given
        val alias1: KoImportAliasDeclaration = mockk()
        val alias2: KoImportAliasDeclaration = mockk()
        val declaration1: KoAliasProvider =
            mockk {
                every { alias } returns alias1
            }
        val declaration2: KoAliasProvider =
            mockk {
                every { alias } returns alias2
            }
        val declaration3: KoAliasProvider =
            mockk {
                every { alias } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.importAliases

        // then
        sut shouldBeEqualTo listOf(alias1, alias2)
    }

    @Test
    fun `withAlias() returns declaration with any alias`() {
        // given
        val declaration1: SampleTestDeclaration =
            mockk {
                every { hasAlias() } returns true
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { hasAlias() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAlias()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAlias{} returns declarations with one of given alias names`() {
        // given
        val declarationName = "name"
        val aliasName1 = "AliasName1"
        val aliasName2 = "AliasName2"
        val aliasName3 = "AliasName3"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName2
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName3
            }
        val declaration4: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2, declaration3, declaration4)

        // when
        val sut = declarations.withAlias { it.name == aliasName1 || it.name == aliasName2 }

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAlias() returns declaration without any alias`() {
        // given
        val declaration1: SampleTestDeclaration =
            mockk {
                every { hasAlias() } returns true
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { hasAlias() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAlias()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAlias{}} returns declarations without alias with any of given names`() {
        // given
        val declarationName = "name"
        val aliasName1 = "AliasName1"
        val aliasName2 = "AliasName2"
        val aliasName3 = "AliasName3"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName1
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName2
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns aliasName3
            }
        val declaration4: SampleTestDeclaration =
            mockk {
                every { name } returns declarationName
                every { alias?.name } returns declarationName
            }
        val declarations = listOf(declaration1, declaration2, declaration3, declaration4)

        // when
        val sut = declarations.withoutAlias { it.name == aliasName1 || it.name == aliasName2 }

        // then
        sut shouldBeEqualTo listOf(declaration3, declaration4)
    }
}
