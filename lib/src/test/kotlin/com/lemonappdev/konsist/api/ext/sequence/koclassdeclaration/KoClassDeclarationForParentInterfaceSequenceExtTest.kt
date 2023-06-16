package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withParentInterfaceOf
import com.lemonappdev.konsist.api.ext.sequence.withParentInterfaces
import com.lemonappdev.konsist.api.ext.sequence.withParentInterfacesOf
import com.lemonappdev.konsist.api.ext.sequence.withSomeParentInterfaces
import com.lemonappdev.konsist.api.ext.sequence.withSomeParentInterfacesOf
import com.lemonappdev.konsist.api.ext.sequence.withoutParentInterfaceOf
import com.lemonappdev.konsist.api.ext.sequence.withoutParentInterfaces
import com.lemonappdev.konsist.api.ext.sequence.withoutParentInterfacesOf
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleInterface1
import com.lemonappdev.konsist.testdata.SampleInterface2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForParentInterfaceSequenceExtTest {
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
    fun `withSomeParentInterfaces() returns class with given parent interface`() {
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
        val sut = classes.withSomeParentInterfaces(name)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withParentInterfacesOf(KClass) returns class with given parent interface`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withParentInterfacesOf(SampleInterface1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
    fun `withoutParentInterfacesOf(KClass) returns class without given parent interface`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutParentInterfacesOf(SampleInterface1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
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
    fun `withSomeParentInterfacesOf(KClass) returns class with given parent`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val class1: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withSomeParentInterfacesOf(SampleInterface1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
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
}
