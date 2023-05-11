package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationSequenceExtTest {
    @Test
    fun `withDataModifier() returns object with data modifier`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { hasDataModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object1)
    }

    @Test
    fun `withoutDataModifier() returns object without data modifier`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { hasDataModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }

    @Test
    fun `withCompanionModifier() returns object with companion modifier`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { hasCompanionModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { hasCompanionModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withCompanionModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object1)
    }

    @Test
    fun `withoutCompanionModifier() returns object without companion modifier`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { hasCompanionModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { hasCompanionModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutCompanionModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }

    @Test
    fun `withNamedCompanionObject() returns object without default name`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { name } returns "name"
            every { hasCompanionModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { name } returns "Companion"
            every { hasCompanionModifier() } returns true

        }
        val object3: KoObjectDeclarationImpl = mockk {
            every { name } returns "Companion"
            every { hasCompanionModifier() } returns false

        }
        val objects = sequenceOf(object1, object2, object3)

        // when
        val sut = objects.withNamedCompanionObject()

        // then
        sut.toList() shouldBeEqualTo listOf(object1)
    }

    @Test
    fun `withoutNamedCompanionObject() returns object with default name`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk {
            every { name } returns "name"
            every { hasCompanionModifier() } returns true
        }
        val object2: KoObjectDeclarationImpl = mockk {
            every { name } returns "Companion"
            every { hasCompanionModifier() } returns true

        }
        val object3: KoObjectDeclarationImpl = mockk {
            every { name } returns "Companion"
            every { hasCompanionModifier() } returns false

        }
        val objects = sequenceOf(object1, object2, object3)

        // when
        val sut = objects.withoutNamedCompanionObject()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }
}
