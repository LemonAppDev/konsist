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
}