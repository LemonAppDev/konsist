package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withExplicitType
import com.lemonappdev.konsist.api.ext.sequence.withExplicitTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutExplicitType
import com.lemonappdev.konsist.api.ext.sequence.withoutExplicitTypeOf
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForTypeSequenceExtTest {
    @Test
    fun `withExplicitType() returns property with any type`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExplicitType()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withExplicitType(name) returns properties with one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns true
            every { hasExplicitType(typeName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withExplicitType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutExplicitType() returns property without any type`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExplicitType()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withoutExplicitType(name) returns property without any of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns true
            every { hasExplicitType(typeName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasExplicitType(typeName1) } returns false
            every { hasExplicitType(typeName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutExplicitType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }

    @Test
    fun `withExplicitTypeOf() with KClass returns property with given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName2
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExplicitTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExplicitTypeOf() with KClass returns property without given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName2
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExplicitTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withExplicitTypeOf(KClass) returns properties with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName2
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName3
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withExplicitTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutExplicitTypeOf(KClass) returns property without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName2
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { explicitType?.name } returns typeName3
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutExplicitTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }
}
