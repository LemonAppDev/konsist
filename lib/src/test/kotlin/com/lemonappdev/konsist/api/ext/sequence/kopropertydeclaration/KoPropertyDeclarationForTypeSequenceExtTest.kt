package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withType
import com.lemonappdev.konsist.api.ext.sequence.withTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutType
import com.lemonappdev.konsist.api.ext.sequence.withoutTypeOf
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
    fun `withType() returns property with any type`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasType() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasType() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withType()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withType(name) returns properties with one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns true
            every { hasType(typeName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutType() returns property without any type`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasType() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasType() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutType()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withoutType(name) returns property without any of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns true
            every { hasType(typeName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }

    @Test
    fun `withType() with KClass returns property with given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName2
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutType() with KClass returns property without given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName2
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withTypeOf(KClass) returns properties with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName2
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName3
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns property without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName1
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName2
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { type?.name } returns typeName3
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }
}
