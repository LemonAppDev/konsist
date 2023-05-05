package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleInterface1
import com.lemonappdev.konsist.testdata.SampleInterface2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationSequenceExtTest {
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

    @Test
    fun `withParents() returns class with any parent`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParents()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents() returns class without any parent`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParents()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentOf() returns class with SampleClass parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentOf() returns class without SampleClass parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParents(name) returns class with given parent`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents(name) returns class without given parent`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParents(String) returns class with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents(name1, name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents(name1, name2) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParents(String) returns class without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents(name1, name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents(name1, name2) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSomeParents(String) returns classes with at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParents(name1) } returns true
            every { hasParents(name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentsOf(KClass) returns class with all of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentsOf(KClass) returns class without any of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParentsOf(KClass) returns classes with at least one of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentInterfacesOf(KClass) returns classes with all given parent interfaces`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfacesOf(KClass) returns class without any of given parent interfaces`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParentInterfacesOf(KClass) returns classes with at least one of given parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclarationImpl = mockk {
            every { name } returns name3
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentInterface() returns class with any parent interface`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterfaces()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterface() returns class without any parent interface`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterfaces()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaceOf() returns class with SampleInterface parent interface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterfaceOf<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaceOf() returns class without SampleInterface parent interface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterfaceOf<SampleInterface>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaces(name) returns class with given parent interface`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterfaces(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaces(name) returns class without given parent interface`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterfaces(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentInterfaces() returns class with all of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1, name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1, name2) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentInterfaces() returns class without any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1, name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1, name2) } returns false
        }

        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSomeParentInterfaces() returns classes with at least one of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1) } returns true
            every { hasParentInterfaces(name2) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withSomeParentInterfaces(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withParentClass() returns class with any parent class`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClass() returns class without any parent class`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentClass(name) returns classes with one of given parent class names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentClass(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withoutParentClass(name) returns class without any of given parent class names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentClass(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withParentClassOf() returns class with SampleClass parent class`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentClass } returns parent2
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutParentClassOf() returns class without SampleClass parent class`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentClass } returns parent2
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withParentClassOf(KClass) returns classes with one of given parent classes`() {
        // given
        val name1 = "SampleClass1"
        val name2 = "SampleClass2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns parents without any of given parent classes`() {
        // given
        val name1 = "SampleClass1"
        val name2 = "SampleClass2"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val class3: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val classes = sequenceOf(class1, class2, class3)

        // when
        val sut = classes.withoutParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }
}
