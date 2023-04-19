package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoProperty
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertySequenceExtTest {
    @Test
    fun `withVarModifier() returns property1 with var modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { isVar } returns true
        }
        val property2: KoProperty = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutVarModifier() returns property2 without var modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { isVar } returns true
        }
        val property2: KoProperty = mockk {
            every { isVar } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withValModifier() returns property1 with val modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { isVal } returns true
        }
        val property2: KoProperty = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutValModifier() returns property2 without val modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { isVal } returns true
        }
        val property2: KoProperty = mockk {
            every { isVal } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withLateinitModifier() returns property1 with lateinit modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasLateinitModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasLateinitModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withLateinitModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutLateinitModifier() returns property2 without lateinit modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasLateinitModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasLateinitModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutLateinitModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withOverrideModifier() returns property1 with override modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasOverrideModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasOverrideModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutOverrideModifier() returns property2 without override modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasOverrideModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasOverrideModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutOverrideModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withAbstractModifier() returns property1 with abstract modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasAbstractModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasAbstractModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutAbstractModifier() returns property2 without abstract modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasAbstractModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasAbstractModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withOpenModifier() returns property1 with open modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasOpenModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasOpenModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutOpenModifier() returns property2 without open modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasOpenModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasOpenModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withFinalModifier() returns property1 with final modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasFinalModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasFinalModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutFinalModifier() returns property2 without final modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasFinalModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasFinalModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withActualModifier() returns property1 with actual modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasActualModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasActualModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutActualModifier() returns property2 without actual modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasActualModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasActualModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withExpectModifier() returns property1 with expect modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasExpectModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasExpectModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExpectModifier() returns property2 without expect modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasExpectModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasExpectModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withConstModifier() returns property1 with const modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasConstModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasConstModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withConstModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutConstModifier() returns property2 without const modifier`() {
        // given
        val property1: KoProperty = mockk {
            every { hasConstModifier() } returns true
        }
        val property2: KoProperty = mockk {
            every { hasConstModifier() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutConstModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withExtension() returns property1 which is extension`() {
        // given
        val property1: KoProperty = mockk {
            every { isExtension() } returns true
        }
        val property2: KoProperty = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExtension() returns property2 which is not extension`() {
        // given
        val property1: KoProperty = mockk {
            every { isExtension() } returns true
        }
        val property2: KoProperty = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }
}