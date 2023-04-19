package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoClass
import com.lemonappdev.konsist.core.declaration.KoParent
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleInterface1
import com.lemonappdev.konsist.testdata.SampleInterface2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
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
    fun `withActualModifier() returns class1 with actual modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasActualModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasActualModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutActualModifier() returns class2 without actual modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasActualModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasActualModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withExpectModifier() returns class1 with expect modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasExpectModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasExpectModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutExpectModifier() returns class2 without expect modifier`() {
        // given
        val class1: KoClass = mockk {
            every { hasExpectModifier() } returns true
        }
        val class2: KoClass = mockk {
            every { hasExpectModifier() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutExpectModifier()

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
    fun `withParent() returns class1 with parent`() {
        // given
        val class1: KoClass = mockk {
            every { hasParent() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParent()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParent() returns class2 without parent`() {
        // given
        val class1: KoClass = mockk {
            every { hasParent() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParent()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentOf() returns SampleClass`() {
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
        val sut = classes.withParentOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentOf() returns OtherClass`() {
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
        val sut = classes.withoutParentOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParents(name) returns class1 with given parent`() {
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
        val sut = classes.withParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents(name) returns class2 without given parent`() {
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
        val sut = classes.withoutParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParents(String) returns class1 with parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParent(name1) } returns true
            every { hasParent(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents(String) returns class3 without parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParent(name1) } returns true
            every { hasParent(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParents(String) returns class1 and class2 which have at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParent(name1) } returns true
            every { hasParent(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParent(name1) } returns false
            every { hasParent(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParents(KClass) returns class1 with parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParents(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents(KClass) returns class3 without parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParents(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParents(KClass) returns class1 and class2 which have at least one of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParents(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentInterfaces(KClass) returns class1 with parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentInterfaces(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaces(KClass) returns class3 without parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentInterfaces(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParentInterfaces(KClass) returns class1 and class2 which have at least one of given parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParent = mockk {
            every { name } returns name1
        }
        val parent2: KoParent = mockk {
            every { name } returns name2
        }
        val parent3: KoParent = mockk {
            every { name } returns name3
        }

        val class1: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClass = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParentInterfaces(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentInterface() returns class1 with parent interface`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentInterface() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterface()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterface() returns class2 without parent interface`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentInterface() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterface()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaceOf() returns SampleInterface`() {
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
        val sut = classes.withParentInterfaceOf<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaceOf() returns OtherInterface`() {
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
        val sut = classes.withoutParentInterfaceOf<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaces(name) returns class1 with given parent interface`() {
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
        val sut = classes.withParentInterfaces(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaces(name) returns class2 without given parent interface`() {
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
        val sut = classes.withoutParentInterfaces(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaces() returns class1 with parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParentInterface(name1) } returns true
            every { hasParentInterface(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaces() returns class3 without parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParentInterface(name1) } returns true
            every { hasParentInterface(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParentInterfaces() returns class1 and class2 which have at least one of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClass = mockk {
            every { hasParentInterface(name1) } returns true
            every { hasParentInterface(name2) } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns true
        }
        val class3: KoClass = mockk {
            every { hasParentInterface(name1) } returns false
            every { hasParentInterface(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentClass() returns class1 with parent class`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClass() returns class2 without parent class`() {
        // given
        val class1: KoClass = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClass = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClass()

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
    fun `withParentClassOf() returns SampleClass`() {
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
        val sut = classes.withParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClassOf() returns OtherClass`() {
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
        val sut = classes.withoutParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }
}
