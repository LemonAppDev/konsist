package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.testdata.SampleClass
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassSequenceExtTest {
    @Test
    fun `withEnumModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasEnumModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasEnumModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutEnumModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasEnumModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasEnumModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSealedModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasSealedModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasSealedModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSealedModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasSealedModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasSealedModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withInnerModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasInnerModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasInnerModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutInnerModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasInnerModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasInnerModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withValueModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasValueModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasValueModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutValueModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasValueModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasValueModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnnotationModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasAnnotationModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasAnnotationModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnnotationModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasAnnotationModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasAnnotationModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withDataModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasDataModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasDataModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutDataModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasDataModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasDataModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAbstractModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasAbstractModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasAbstractModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAbstractModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasAbstractModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasAbstractModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withOpenModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasOpenModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasOpenModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutOpenModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasOpenModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasOpenModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withFinalModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasFinalModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasFinalModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutFinalModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasFinalModifier() } returns true
        val class2: KoClass = mockk()
        every { class2.hasFinalModifier() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withExplicitPrimaryConstructor() returns class1 with primary constructor`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasExplicitPrimaryConstructor() } returns true
        val class2: KoClass = mockk()
        every { class2.hasExplicitPrimaryConstructor() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withExplicitPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutExplicitPrimaryConstructor() returns class2 without primary constructor`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasExplicitPrimaryConstructor() } returns true
        val class2: KoClass = mockk()
        every { class2.hasExplicitPrimaryConstructor() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutExplicitPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSecondaryConstructors() returns class1 with secondary constructor`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasSecondaryConstructors() } returns true
        val class2: KoClass = mockk()
        every { class2.hasSecondaryConstructors() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns class2 without secondary constructor`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasSecondaryConstructors() } returns true
        val class2: KoClass = mockk()
        every { class2.hasSecondaryConstructors() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnyParent() returns class1 with parent`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasParent() } returns true
        val class2: KoClass = mockk()
        every { class2.hasParent() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnyParent()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnyParent() returns class2 without parent`() {
        // given
        val class1: KoClass = mockk()
        every { class1.hasParent() } returns true
        val class2: KoClass = mockk()
        every { class2.hasParent() } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnyParent()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParent(name) returns class1 with given parent`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk()
        every { class1.hasParent(name) } returns true
        val class2: KoClass = mockk()
        every { class2.hasParent(name) } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParent(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParent(name) returns class2 without given parent`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk()
        every { class1.hasParent(name) } returns true
        val class2: KoClass = mockk()
        every { class2.hasParent(name) } returns false
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParent(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParent() with KClass syntax returns SampleClass`() {
        // given
        val class1: KoClass = mockk()
        val name1 = "SampleClass"
        every { class1.name } returns name1
        val class2: KoClass = mockk()
        val name2 = "OtherClass"
        every { class2.name } returns name2
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParent<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParent() with KClass syntax returns OtherClass`() {
        // given
        val class1: KoClass = mockk()
        val name1 = "SampleClass"
        every { class1.name } returns name1
        val class2: KoClass = mockk()
        val name2 = "OtherClass"
        every { class2.name } returns name2
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParent<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

}