package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassSequenceExtTest {
    @Test
    fun `withEnumModifier() returns class with enum modifier`() {
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
    fun `withoutEnumModifier() returns class without enum modifier`() {
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
    fun `withSealedModifier() returns class with enum modifier`() {
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
    fun `withoutSealedModifier() returns class without enum modifier`() {
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
    fun `withInnerModifier() returns class with enum modifier`() {
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
    fun `withoutInnerModifier() returns class without enum modifier`() {
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
    fun `withValueModifier() returns class with enum modifier`() {
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
    fun `withoutValueModifier() returns class without enum modifier`() {
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
    fun `withAnnotationModifier() returns class with enum modifier`() {
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
    fun `withoutAnnotationModifier() returns class without enum modifier`() {
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
    fun `withDataModifier() returns class with enum modifier`() {
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
    fun `withoutDataModifier() returns class without enum modifier`() {
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
}