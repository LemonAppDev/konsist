package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.core.declaration.KoParent
import com.lemon.konsist.testdata.SampleClass
import com.lemon.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassSequenceExtTest {
    @Test
    fun `withEnumModifier() returns class1 with enum modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasEnumModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasEnumModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutEnumModifier() returns class2 without enum modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasEnumModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasEnumModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutEnumModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSealedModifier() returns class1 with sealed modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasSealedModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasSealedModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSealedModifier() returns class2 without sealed modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasSealedModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasSealedModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSealedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withInnerModifier() returns class1 with inner modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasInnerModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasInnerModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutInnerModifier() returns class2 without inner modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasInnerModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasInnerModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutInnerModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withValueModifier() returns class1 with value modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasValueModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasValueModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutValueModifier() returns class2 without value modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasValueModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasValueModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutValueModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnnotationModifier() returns class1 with annotation modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasAnnotationModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasAnnotationModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnnotationModifier() returns class2 without annotation modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasAnnotationModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasAnnotationModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnnotationModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withDataModifier() returns class1 with data modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasDataModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasDataModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutDataModifier() returns class2 without data modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasDataModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasDataModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAbstractModifier() returns class1 with abstract modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasAbstractModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasAbstractModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAbstractModifier() returns class2 without abstract modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasAbstractModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasAbstractModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAbstractModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withOpenModifier() returns class1 with open modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasOpenModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasOpenModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutOpenModifier() returns class2 without open modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasOpenModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasOpenModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutOpenModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withFinalModifier() returns class1 with final modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasFinalModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasFinalModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutFinalModifier() returns class2 without final modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasFinalModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasFinalModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutFinalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withExplicitPrimaryConstructor() returns class1 with primary constructor`() {
        // given
        val class1: KoClass = mockk {
            every { hasExplicitPrimaryConstructor() } returns true
        }
        val class2: KoClass = mockk {
            every { hasExplicitPrimaryConstructor() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withExplicitPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutExplicitPrimaryConstructor() returns class2 without primary constructor`() {
        // given
        val class1: KoClass = mockk {
            every { hasExplicitPrimaryConstructor() } returns true
        }
        val class2: KoClass = mockk {
            every { hasExplicitPrimaryConstructor() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutExplicitPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSecondaryConstructors() returns class1 with secondary constructor`() {
        // given
        val class1: KoClass = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val class2: KoClass = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns class2 without secondary constructor`() {
        // given
        val class1: KoClass = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val class2: KoClass = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnyParent() returns class1 with parent`() {
        // given
        val class1: KoClass = mockk {
            every { hasParent() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnyParent()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnyParent() returns class2 without parent`() {
        // given
        val class1: KoClass = mockk {
            every { hasParent() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent() } returns false
        }
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
        val class1: KoClass = mockk {
            every { hasParent(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent(name) } returns false
        }
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
        val class1: KoClass = mockk {
            every { hasParent(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParent(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParent() with KClass syntax returns SampleClass`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parents } returns listOf(parent1)
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParent<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParent() with KClass syntax returns OtherClass`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parents } returns listOf(parent1)
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParent<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnyParentInterface() returns class1 with parent interface`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentInterface() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnyParentInterface()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnyParentInterface() returns class2 without parent interface`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentInterface() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnyParentInterface()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterface(name) returns class1 with given parent interface`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk {
            every { hasParentInterface(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterface(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterface(name) returns class2 without given parent interface`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk {
            every { hasParentInterface(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterface(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterface() with KClass syntax returns SampleInterface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterface<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterface() with KClass syntax returns OtherInterface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterface<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAnyParentClass() returns class1 with parent class`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAnyParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAnyParentClass() returns class2 without parent class`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAnyParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentClass(name) returns class1 with given parent class`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk {
            every { hasParentClass(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClass(name) returns class2 without given parent class`() {
        // given
        val name = "SampleName"
        val class1: KoClass = mockk {
            every { hasParentClass(name) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentClass() with KClass syntax returns SampleClass`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parentClass } returns parent2
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClass<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClass() with KClass syntax returns OtherClass`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val class1: KoClass = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val class2: KoClass = mockk {
            every { parentClass } returns parent2
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClass<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }
}
