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
    fun `withSourceType(type) returns type1 which has SampleClass source type`() {
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
        val sut = types.withSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutSourceType(type) returns type2 which has not SampleClass source type`() {
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
        val sut = types.withoutSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withImportAliasName() returns type1 which has SampleImportAlias`() {
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
        val sut = types.withImportAliasName<SampleImportAlias>()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutImportAliasName() returns type2, typ3 and type4 which have not SampleImportAlias`() {
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
        val sut = types.withoutImportAliasName<SampleImportAlias>()

        // then
        sut.toList() shouldBeEqualTo listOf(type2, type3, type4)
    }

    @Test
    fun `withImportAliasName(type) returns type1 which has SampleImportAlias`() {
        // given
        val importAliasName1 = "SampleImportAlias"
        val importAliasName2 = "OtherImportAlias"
        val type1: KoType = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoType = mockk {
            every { importAliasName } returns importAliasName2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withImportAliasName(importAliasName1)

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutImportAliasName(type) returns type2, typ3 and type4 which have not SampleImportAlias`() {
        // given
        val importAliasName1 = "SampleImportAlias"
        val importAliasName2 = "OtherImportAlias"
        val type1: KoType = mockk {
            every { importAliasName } returns importAliasName1
        }
        val type2: KoType = mockk {
            every { importAliasName } returns importAliasName2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutImportAliasName(importAliasName1)

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withFullyQualifiedName() returns type1 which has given name`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val type1: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withFullyQualifiedName(fullyQualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutFullyQualifiedName() returns type2 which has not given name`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val type1: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoType = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutFullyQualifiedName(fullyQualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
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
