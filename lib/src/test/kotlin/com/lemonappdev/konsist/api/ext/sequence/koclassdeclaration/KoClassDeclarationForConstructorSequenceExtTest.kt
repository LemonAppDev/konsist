package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withPrimaryConstructor
import com.lemonappdev.konsist.api.ext.sequence.withSecondaryConstructors
import com.lemonappdev.konsist.api.ext.sequence.withoutPrimaryConstructor
import com.lemonappdev.konsist.api.ext.sequence.withoutSecondaryConstructors
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForConstructorSequenceExtTest {
    @Test
    fun `withPrimaryConstructor() returns class with primary constructor`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasPrimaryConstructor() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasPrimaryConstructor() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutPrimaryConstructor() returns class without primary constructor`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasPrimaryConstructor() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasPrimaryConstructor() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSecondaryConstructors() returns class with secondary constructor`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns class without secondary constructor`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }
}
