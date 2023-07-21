package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withParentClass
import com.lemonappdev.konsist.api.ext.sequence.withParentClassOf
import com.lemonappdev.konsist.api.ext.sequence.withoutParentClass
import com.lemonappdev.konsist.api.ext.sequence.withoutParentClassOf
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForParentClassSequenceExtTest {
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
    fun `withParentClass(name) returns class with given parent class name`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withoutParentClass(name) returns class without given parent class name`() {
        // given
        val name = "SampleName"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
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
    fun `withParentClassOf(KClass) returns class with given parent class`() {
        // given
        val name1 = "SampleClass1"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentClassOf(SampleClass1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withoutParentClassOf(KClass) returns class without given parent class`() {
        // given
        val name1 = "SampleClass1"
        val class1: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasParentClass(name1) } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentClassOf(SampleClass1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns class without any of given parent classes`() {
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
