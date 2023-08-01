package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoParentDeclarationProviderListExtTest {
    @Test
    fun `withParentDeclarations() returns declaration with any parent`() {
        // given
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations() } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentDeclarations() returns declaration without any parent`() {
        // given
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations() } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentDeclarationOf() returns declaration with SampleClass parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentDeclarationOf<SampleClass>()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentDeclarationOf() returns declaration without SampleClass parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1)
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentDeclarationOf<SampleClass>()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentDeclarations(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentDeclarations(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentDeclarations(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentDeclarations(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentDeclarations(String) returns declaration with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1, name2) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentDeclarations(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentDeclarations(String) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1, name2) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentDeclarations(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeParentDeclarations(String) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParentDeclarations(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentDeclarations(String) returns declarations with at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns true
            every { hasParentDeclarations(name2) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns false
            every { hasParentDeclarations(name2) } returns true
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns false
            every { hasParentDeclarations(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParentDeclarations(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentDeclarations(String) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParentDeclarations(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentDeclarations(String) returns declarations without at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns true
            every { hasParentDeclarations(name2) } returns true
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns false
            every { hasParentDeclarations(name2) } returns true
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { hasParentDeclarations(name1) } returns false
            every { hasParentDeclarations(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentDeclarations(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAllParentDeclarationsOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentDeclarationsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentDeclarationsOf(KClass) returns declaration with all of given parents`() {
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

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAllParentDeclarationsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentDeclarationsOf(KClass) returns declaration without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentDeclarationsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentDeclarationsOf(KClass) returns declaration without any of given parents`() {
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

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAllParentDeclarationsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeParentDeclarationsOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParentDeclarationsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentDeclarationsOf(KClass) returns declarations with at least one of given parents`() {
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

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParentDeclarationsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentDeclarationsOf(KClass) returns declaration without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParentDeclarationsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentDeclarationsOf(KClass) returns declarations without at least one of given parents`() {
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

        val declaration1: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentDeclarationProvider = mockk {
            every { parentDeclarations } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentDeclarationsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
