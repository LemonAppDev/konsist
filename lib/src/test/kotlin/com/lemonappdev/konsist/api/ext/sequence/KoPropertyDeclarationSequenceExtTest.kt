package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationSequenceExtTest {
    @Test
    fun `withVarModifier() returns property with var modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutVarModifier() returns property without var modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withValModifier() returns property with val modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutValModifier() returns property without val modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withLateinitModifier() returns property with lateinit modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasLateinitModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasLateinitModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withLateinitModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutLateinitModifier() returns property without lateinit modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasLateinitModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasLateinitModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutLateinitModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withOverrideModifier() returns property with override modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutOverrideModifier() returns property without override modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasOverrideModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withAbstractModifier() returns property with abstract modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutAbstractModifier() returns property without abstract modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withOpenModifier() returns property with open modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutOpenModifier() returns property without open modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withFinalModifier() returns property with final modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutFinalModifier() returns property without final modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withActualModifier() returns property with actual modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutActualModifier() returns property without actual modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withExpectModifier() returns property with expect modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExpectModifier() returns property without expect modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withConstModifier() returns property with const modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasConstModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasConstModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withConstModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutConstModifier() returns property without const modifier`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasConstModifier() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasConstModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutConstModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withExtension() returns property which is extension`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExtension() returns property which is not extension`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withDelegate() returns property with any delegate`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withDelegate(name) returns properties with one of given delegate names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutDelegate() returns property without any delegate`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withoutDelegate(name) returns property without any of delegate with given name`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }

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
