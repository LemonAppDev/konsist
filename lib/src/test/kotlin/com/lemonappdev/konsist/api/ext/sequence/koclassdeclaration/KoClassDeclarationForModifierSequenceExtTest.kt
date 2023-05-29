package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationModifier
import com.lemonappdev.konsist.api.ext.sequence.withDataModifier
import com.lemonappdev.konsist.api.ext.sequence.withEnumModifier
import com.lemonappdev.konsist.api.ext.sequence.withExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withInnerModifier
import com.lemonappdev.konsist.api.ext.sequence.withOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withSealedModifier
import com.lemonappdev.konsist.api.ext.sequence.withValueModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutAbstractModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutActualModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutDataModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutEnumModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutExpectModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutFinalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInnerModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutOpenModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutSealedModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutValueModifier
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForModifierSequenceExtTest {
    @Test
    fun `withEnumModifier() returns class with enum modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasEnumModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasEnumModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutEnumModifier() returns class without enum modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasEnumModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasEnumModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSealedModifier() returns class with sealed modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasSealedModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasSealedModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSealedModifier() returns class without sealed modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasSealedModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasSealedModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withInnerModifier() returns class with inner modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasInnerModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasInnerModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutInnerModifier() returns class without inner modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasInnerModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasInnerModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withValueModifier() returns class with value modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasValueModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasValueModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutValueModifier() returns class without value modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasValueModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasValueModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnnotationModifier() returns class with annotation modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasAnnotationModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasAnnotationModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnnotationModifier() returns class without annotation modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasAnnotationModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasAnnotationModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withDataModifier() returns class with data modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasDataModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasDataModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutDataModifier() returns class without data modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasDataModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasDataModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withActualModifier() returns class with actual modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutActualModifier() returns class without actual modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withExpectModifier() returns class with expect modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutExpectModifier() returns class without expect modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAbstractModifier() returns class with abstract modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAbstractModifier() returns class without abstract modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasAbstractModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withOpenModifier() returns class with open modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutOpenModifier() returns class without open modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasOpenModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasOpenModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withFinalModifier() returns class with final modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutFinalModifier() returns class without final modifier`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasFinalModifier() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasFinalModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }
}
