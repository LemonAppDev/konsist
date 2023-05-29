package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withConstModifier
import com.lemonappdev.konsist.api.ext.sequence.withExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withLateinitModifier
import com.lemonappdev.konsist.api.ext.sequence.withOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withValModifier
import com.lemonappdev.konsist.api.ext.sequence.withVarModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutConstModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutLateinitModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOverrideModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutValModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutVarModifier
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForModifierSequenceExtTest {
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
}
