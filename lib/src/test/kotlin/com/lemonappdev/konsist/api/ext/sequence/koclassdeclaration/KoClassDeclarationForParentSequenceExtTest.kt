package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAllParents
import com.lemonappdev.konsist.api.ext.sequence.withAllParentsOf
import com.lemonappdev.konsist.api.ext.sequence.withParentOf
import com.lemonappdev.konsist.api.ext.sequence.withParents
import com.lemonappdev.konsist.api.ext.sequence.withSomeParents
import com.lemonappdev.konsist.api.ext.sequence.withSomeParentsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAllParents
import com.lemonappdev.konsist.api.ext.sequence.withoutAllParentsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutParentOf
import com.lemonappdev.konsist.api.ext.sequence.withoutParents
import com.lemonappdev.konsist.api.ext.sequence.withoutSomeParents
import com.lemonappdev.konsist.api.ext.sequence.withoutSomeParentsOf
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForParentSequenceExtTest {
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
    fun `withAllParents(name) returns class with given parent`() {
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
        val sut = classes.withAllParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAllParents(name) returns class without given parent`() {
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
        val sut = classes.withoutAllParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withAllParents(String) returns class with all given parents`() {
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
        val sut = classes.withAllParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAllParents(String) returns class without any of given parents`() {
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
        val sut = classes.withoutAllParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withSomeParents(String) returns class with given parent`() {
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
        val sut = classes.withSomeParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withoutSomeParents(String) returns class without given parent`() {
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
        val sut = classes.withoutSomeParents(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withoutSomeParents(String) returns classes without at least one of given parents`() {
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
        val sut = classes.withoutSomeParents(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withAllParentsOf(KClass) returns class with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withAllParentsOf(SampleClass::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withAllParentsOf(KClass) returns class with all of given parents`() {
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
        val sut = classes.withAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns class without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutAllParentsOf(SampleClass::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns class without any of given parents`() {
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
        val sut = classes.withoutAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }

    @Test
    fun `withSomeParentsOf(KClass) returns class with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSomeParentsOf(SampleClass::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withoutSomeParentsOf(KClass) returns class without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parents } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutSomeParentsOf(SampleClass::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withoutSomeParentsOf(KClass) returns classes without at least one of given parents`() {
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
        val sut = classes.withoutSomeParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class3)
    }
}
