package com.lemonappdev.konsist.api.ext.sequence.kotypedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withImportAlias
import com.lemonappdev.konsist.api.ext.sequence.withImportAliasOf
import com.lemonappdev.konsist.api.ext.sequence.withoutImportAlias
import com.lemonappdev.konsist.api.ext.sequence.withoutImportAliasOf
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import com.lemonappdev.konsist.testdata.SampleType as SampleImportAlias
import com.lemonappdev.konsist.testdata.SampleType1 as SampleImportAlias1
import com.lemonappdev.konsist.testdata.SampleType2 as SampleImportAlias2

class KoTypeDeclarationForImportAliasSequenceExtTest {
    @Test
    fun `withImportAlias() returns type with any import alias`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withImportAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutImportAlias() returns type without any import alias`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutImportAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withImportAlias(type) returns types with one of given import aliases`() {
        // given
        val importAliasName1 = "SampleImportAlias1"
        val importAliasName2 = "SampleImportAlias2"
        val importAliasName3 = "SampleImportAlias3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withImportAlias(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutImportAlias(type) returns type without any of given import alias`() {
        // given
        val importAliasName1 = "SampleImportAlias1"
        val importAliasName2 = "SampleImportAlias2"
        val importAliasName3 = "SampleImportAlias3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { importAliasName } returns importAliasName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutImportAlias(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }

    @Test
    fun `withImportAliasOf() returns type with SampleImportAlias`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2, type3, type4)

        // when
        val sut = types.withImportAliasOf<SampleImportAlias>()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutImportAliasOf() returns types without SampleImportAlias`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2, type3, type4)

        // when
        val sut = types.withoutImportAliasOf<SampleImportAlias>()

        // then
        sut.toList() shouldBeEqualTo listOf(type2, type3, type4)
    }

    @Test
    fun `withImportAliasOf(KClass) returns types with one of given import aliases`() {
        // given
        val sourceType1 = "SampleType1"
        val sourceType2 = "SampleType2"
        val sourceType3 = "SampleType3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3, type4)

        // when
        val sut = types.withImportAliasOf(SampleImportAlias1::class, SampleImportAlias2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutImportAliasOf(KClass) returns types without any of given import aliases`() {
        // given
        val sourceType1 = "SampleType1"
        val sourceType2 = "SampleType2"
        val sourceType3 = "SampleType3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3, type4)

        // when
        val sut = types.withoutImportAliasOf(SampleImportAlias1::class, SampleImportAlias2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(type3, type4)
    }
}
