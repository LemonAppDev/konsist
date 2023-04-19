package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoImport
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportSequenceExtTest {
    @Test
    fun `withAlias() returns import1 which has alias`() {
        // given
        val importName = "name"
        val alias1 = "AliasName"
        val import1: KoImport = mockk {
            every { name } returns importName
            every { alias } returns alias1
        }
        val import2: KoImport = mockk {
            every { name } returns importName
            every { alias } returns importName
        }
        val imports = sequenceOf(import1, import2)

        // when
        val sut = imports.withAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withAlias(name) returns import1 which has alias with given name`() {
        // given
        val importName = "name"
        val aliasName1 = "AliasName"
        val aliasName2 = "OtherAliasName"
        val import1: KoImport = mockk {
            every { name } returns importName
            every { alias } returns aliasName1
        }
        val import2: KoImport = mockk {
            every { name } returns importName
            every { alias } returns aliasName2
        }
        val import3: KoImport = mockk {
            every { name } returns importName
            every { alias } returns importName
        }
        val imports = sequenceOf(import1, import2, import3)

        // when
        val sut = imports.withAlias(aliasName1)

        // then
        sut.toList() shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withoutAlias() returns import2 which has not alias`() {
        // given
        val importName = "name"
        val alias1 = "AliasName"
        val import1: KoImport = mockk {
            every { name } returns importName
            every { alias } returns alias1
        }
        val import2: KoImport = mockk {
            every { name } returns importName
            every { alias } returns importName
        }
        val imports = sequenceOf(import1, import2)

        // when
        val sut = imports.withoutAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(import2)
    }

    @Test
    fun `withoutAlias(name) returns import2 and import3 which has not alias with given name`() {
        // given
        val importName = "name"
        val aliasName1 = "AliasName"
        val aliasName2 = "OtherAliasName"
        val import1: KoImport = mockk {
            every { name } returns importName
            every { alias } returns aliasName1
        }
        val import2: KoImport = mockk {
            every { name } returns importName
            every { alias } returns aliasName2
        }
        val import3: KoImport = mockk {
            every { name } returns importName
            every { alias } returns importName
        }
        val imports = sequenceOf(import1, import2, import3)

        // when
        val sut = imports.withoutAlias(aliasName1)

        // then
        sut.toList() shouldBeEqualTo listOf(import2, import3)
    }
}
