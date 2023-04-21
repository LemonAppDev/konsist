package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoType
import com.lemonappdev.konsist.testdata.SampleClass
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import com.lemonappdev.konsist.testdata.SampleType as SampleImportAlias

class KoTypeSequenceExtTest {
    @Test
    fun `withSourceType() returns type1 which has SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withSourceType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutSourceType() returns type2 which has not SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutSourceType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withSourceType(type) returns types which has one of given source types`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoType = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutSourceType(type) returns type3 which has not any given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val type1: KoType = mockk {
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { sourceType } returns sourceType2
        }
        val type3: KoType = mockk {
            every { sourceType } returns sourceType3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }

    @Test
    fun `withImportAliasOf() returns type1 which has SampleImportAlias`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val type1: KoType = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoType = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoType = mockk {
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
    fun `withoutImportAliasOf() returns type2, typ3 and type4 which have not SampleImportAlias`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val type1: KoType = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val type2: KoType = mockk {
            every { isImportAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val type3: KoType = mockk {
            every { isImportAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoType = mockk {
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
    fun `withImportAlias(type) returns types which have one of given import aliases`() {
        // given
        val importAliasName1 = "SampleImportAlias1"
        val importAliasName2 = "SampleImportAlias2"
        val importAliasName3 = "SampleImportAlias3"
        val type1: KoType = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoType = mockk {
            every { importAliasName } returns importAliasName2
        }
        val type3: KoType = mockk {
            every { importAliasName } returns importAliasName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withImportAlias(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutImportAlias(type) returns type3 which has not any given import alias`() {
        // given
        val importAliasName1 = "SampleImportAlias1"
        val importAliasName2 = "SampleImportAlias2"
        val importAliasName3 = "SampleImportAlias3"
        val type1: KoType = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoType = mockk {
            every { importAliasName } returns importAliasName2
        }
        val type3: KoType = mockk {
            every { importAliasName } returns importAliasName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutImportAlias(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }

    @Test
    fun `withFullyQualifiedName() returns types which has one of given names`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val type1: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val type3: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutFullyQualifiedName() returns type3 which has not any given name`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val type1: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val type3: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }

    @Test
    fun `withImportAlias() returns type1 which has given name`() {
        // given
        val type1: KoType = mockk {
            every { isImportAlias() } returns true
        }
        val type2: KoType = mockk {
            every { isImportAlias() } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withImportAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutImportAlias() returns type2 which has not given name`() {
        // given
        val type1: KoType = mockk {
            every { isImportAlias() } returns true
        }
        val type2: KoType = mockk {
            every { isImportAlias() } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutImportAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }
}
