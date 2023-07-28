package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceAndAliasTypeProviderSequenceExtTest {
    @Test
    fun `withSourceTypeOf() returns declaration with SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceTypeOf() returns declaration without SampleClass source type`() {
        // given
        val sourceType1 = "SampleClass"
        val sourceType2 = "OtherClass"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSourceTypeOf(KClass) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declaration3: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceTypeOf(KClass) returns declaration without any of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declaration3: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSourceType(type) returns declaration with given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceType(type) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declaration3: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceType(type) returns declaration without given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceType(sourceType1)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceType(type) returns declaration without any of given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType1
        }
        val declaration2: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType2
        }
        val declaration3: KoSourceAndAliasTypeProvider = mockk {
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceType(sourceType1, sourceType2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAliasType() returns declaration with any alias type`() {
        // given
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAliasType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAliasType() returns declaration without any alias type`() {
        // given
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAliasType()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAliasType(type) returns declarations with one of given alias types`() {
        // given
        val importAliasName1 = "SampleAliasType1"
        val importAliasName2 = "SampleAliasType2"
        val importAliasName3 = "SampleAliasType3"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAliasType(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAliasType(type) returns declaration without any of given alias type`() {
        // given
        val importAliasName1 = "SampleAliasType1"
        val importAliasName2 = "SampleAliasType2"
        val importAliasName3 = "SampleAliasType3"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { aliasType } returns importAliasName3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAliasType(importAliasName1, importAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAliasTypeOf() returns declaration with SampleAliasType`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3, type4)

        // when
        val sut = declarations.withAliasTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAliasTypeOf() returns declarations without SampleAliasType`() {
        // given
        val sourceType1 = "SampleType"
        val sourceType2 = "Sample"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType2
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3, type4)

        // when
        val sut = declarations.withoutAliasTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2, declaration3, type4)
    }

    @Test
    fun `withAliasTypeOf(KClass) returns declarations with one of given alias types`() {
        // given
        val sourceType1 = "SampleType1"
        val sourceType2 = "SampleType2"
        val sourceType3 = "SampleType3"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3, type4)

        // when
        val sut = declarations.withAliasTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAliasTypeOf(KClass) returns declarations without any of given alias types`() {
        // given
        val sourceType1 = "SampleType1"
        val sourceType2 = "SampleType2"
        val sourceType3 = "SampleType3"
        val declaration1: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType1
        }
        val declaration2: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns true
            every { sourceType } returns sourceType2
        }
        val declaration3: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType1
        }
        val type4: KoTypeDeclarationImpl = mockk {
            every { isAlias() } returns false
            every { sourceType } returns sourceType3
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3, type4)

        // when
        val sut = declarations.withoutAliasTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3, type4)
    }
}
