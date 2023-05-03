package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.core.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationSequenceExtTest {
    @Test
    fun `withType(String) returns complex declarations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutType(String) returns complex declaration without any given type`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    @Test
    fun `withTypeOf(KClass) returns complex declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns complex declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    //  'every { representsType<SampleClass>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withTypeOf() with KClass syntax returns SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
    }

    @Test
    fun `withoutTypeOf() with KClass syntax returns complex declaration without SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withoutTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
    }

    @Test
    fun `declarations() returns declarations of all complex declarations`() {
        // given
        val class1: KoClassDeclaration = mockk()
        val function1: KoFunctionDeclaration = mockk()
        val class2: KoClassDeclaration = mockk()
        val interface1: KoInterfaceDeclaration = mockk()
        val property1: KoPropertyDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { declarations(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(class1, function1)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { declarations(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(class2, interface1)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { declarations(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(property1)
        }
        val complexDeclaration4: KoComplexDeclaration = mockk {
            every { declarations(modifiers, includeNested = true, includeLocal = false) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(
            complexDeclaration1,
            complexDeclaration2,
            complexDeclaration3,
            complexDeclaration4,
        )

        // when
        val sut = complexDeclarations.declarations(modifiers, includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, function1, class2, interface1, property1)
    }

    @Test
    fun `classes() returns classes of all complex declarations`() {
        // given
        val class1: KoClassDeclaration = mockk()
        val class2: KoClassDeclaration = mockk()
        val class3: KoClassDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { classes(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(class1, class2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { classes(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(class3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { classes(modifiers, includeNested = true, includeLocal = false) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.classes(modifiers, includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(class1, class2, class3)
    }

    @Test
    fun `interfaces() returns interfaces of all complex declarations`() {
        // given
        val interface1: KoInterfaceDeclaration = mockk()
        val interface2: KoInterfaceDeclaration = mockk()
        val interface3: KoInterfaceDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { interfaces(modifiers, includeNested = true) } returns sequenceOf(interface1, interface2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { interfaces(modifiers, includeNested = true) } returns sequenceOf(interface3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { interfaces(modifiers, includeNested = true) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.interfaces(modifiers, includeNested = true)

        // then
        sut.toList() shouldBeEqualTo listOf(interface1, interface2, interface3)
    }

    @Test
    fun `objects() returns objects of all complex declarations`() {
        // given
        val object1: KoObjectDeclaration = mockk()
        val object2: KoObjectDeclaration = mockk()
        val object3: KoObjectDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { objects(modifiers, includeNested = true) } returns sequenceOf(object1, object2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { objects(modifiers, includeNested = true) } returns sequenceOf(object3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { objects(modifiers, includeNested = true) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.objects(modifiers, includeNested = true)

        // then
        sut.toList() shouldBeEqualTo listOf(object1, object2, object3)
    }

    @Test
    fun `companionObjects() returns companionObjects of all complex declarations`() {
        // given
        val companionObject1: KoCompanionObjectDeclaration = mockk()
        val companionObject2: KoCompanionObjectDeclaration = mockk()
        val companionObject3: KoCompanionObjectDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { companionObjects(modifiers, includeNested = true) } returns sequenceOf(companionObject1, companionObject2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { companionObjects(modifiers, includeNested = true) } returns sequenceOf(companionObject3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { companionObjects(modifiers, includeNested = true) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.companionObjects(modifiers, includeNested = true)

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject1, companionObject2, companionObject3)
    }

    @Test
    fun `properties() returns properties of all complex declarations`() {
        // given
        val property1: KoPropertyDeclaration = mockk()
        val property2: KoPropertyDeclaration = mockk()
        val property3: KoPropertyDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { properties(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(property1, property2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { properties(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(property3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { properties(modifiers, includeNested = true, includeLocal = false) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.properties(modifiers, includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2, property3)
    }

    @Test
    fun `functions() returns functions of all complex declarations`() {
        // given
        val function1: KoFunctionDeclaration = mockk()
        val function2: KoFunctionDeclaration = mockk()
        val function3: KoFunctionDeclaration = mockk()
        val modifiers = listOf(OPEN, PUBLIC)
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { functions(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(function1, function2)
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { functions(modifiers, includeNested = true, includeLocal = false) } returns sequenceOf(function3)
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { functions(modifiers, includeNested = true, includeLocal = false) } returns emptySequence()
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.functions(modifiers, includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2, function3)
    }
}
