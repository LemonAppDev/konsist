package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceDeclarationProviderListExtTest {
    @Test
    fun `sourceDeclarations returns declarations from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration3: KoExternalDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2, sourceDeclaration3)
    }

    @Test
    fun `sourceClasses returns classes from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoClassDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceClasses

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceObjects returns objects from all declarations`() {
        // given
        val sourceDeclaration1: KoObjectDeclaration = mockk()
        val sourceDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceObjects

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceInterfaces returns interfaces from all declarations`() {
        // given
        val sourceDeclaration1: KoInterfaceDeclaration = mockk()
        val sourceDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceInterfaces

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceTypeAliases returns type aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoTypeAliasDeclaration = mockk()
        val sourceDeclaration2: KoTypeAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceTypeAliases

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceImportAliases returns import aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoImportAliasDeclaration = mockk()
        val sourceDeclaration2: KoImportAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceImportAliases

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceKotlinTypes returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceKotlinTypes

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceFunctionTypes returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoFunctionTypeDeclaration = mockk()
        val sourceDeclaration2: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceFunctionTypes

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `sourceExternalTypes returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceDeclaration2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceExternalTypes

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `withSourceDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceDeclaration } returns sourceDeclaration2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSourceClass() returns declaration with source class`() {
        // given
        val sourceDeclaration: KoClassDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceClass()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceClass{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceClass1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val sourceClass2: KoClassDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceClass1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceClass2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceClass { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceClass() returns declaration without source class`() {
        // given
        val sourceDeclaration: KoClassDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceClass()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceClass{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceClass1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val sourceClass2: KoClassDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceClass1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns sourceClass2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceClass { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceObject() returns declaration with source object`() {
        // given
        val sourceDeclaration: KoObjectDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceObject()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceObject{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceObject1: KoObjectDeclaration = mockk {
            every { name } returns name1
        }
        val sourceObject2: KoObjectDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceObject1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceObject2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceObject { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceObject() returns declaration without source object`() {
        // given
        val sourceDeclaration: KoObjectDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceObject()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceObject{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceObject1: KoObjectDeclaration = mockk {
            every { name } returns name1
        }
        val sourceObject2: KoObjectDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceObject1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns sourceObject2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceObject } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceObject { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceInterface() returns declaration with source interface`() {
        // given
        val sourceDeclaration: KoInterfaceDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceInterface()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceInterface{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceInterface1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val sourceInterface2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceInterface1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceInterface2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceInterface { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceInterface() returns declaration without source interface`() {
        // given
        val sourceDeclaration: KoInterfaceDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceInterface()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceInterface{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceInterface1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val sourceInterface2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceInterface1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns sourceInterface2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceInterface } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceInterface { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceTypeAlias() returns declaration with source type alias`() {
        // given
        val sourceDeclaration: KoTypeAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceTypeAlias()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceTypeAlias{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeAlias1: KoTypeAliasDeclaration = mockk {
            every { name } returns name1
        }
        val sourceTypeAlias2: KoTypeAliasDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceTypeAlias1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceTypeAlias2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceTypeAlias { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceTypeAlias() returns declaration without source type alias`() {
        // given
        val sourceDeclaration: KoTypeAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceTypeAlias()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceTypeAlias{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeAlias1: KoTypeAliasDeclaration = mockk {
            every { name } returns name1
        }
        val sourceTypeAlias2: KoTypeAliasDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceTypeAlias1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns sourceTypeAlias2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceTypeAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceTypeAlias { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceImportAlias() returns declaration with source import alias`() {
        // given
        val sourceDeclaration: KoImportAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceImportAlias()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceImportAlias{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceImportAlias1: KoImportAliasDeclaration = mockk {
            every { name } returns name1
        }
        val sourceImportAlias2: KoImportAliasDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceImportAlias1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceImportAlias2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceImportAlias { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceImportAlias() returns declaration without source import alias`() {
        // given
        val sourceDeclaration: KoImportAliasDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceImportAlias()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceImportAlias{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceImportAlias1: KoImportAliasDeclaration = mockk {
            every { name } returns name1
        }
        val sourceImportAlias2: KoImportAliasDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceImportAlias1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns sourceImportAlias2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceImportAlias } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceImportAlias { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceKotlinType() returns declaration with source kotlin type`() {
        // given
        val sourceDeclaration: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceKotlinType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceKotlinType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinType1: KoKotlinTypeDeclaration = mockk {
            every { name } returns name1
        }
        val sourceKotlinType2: KoKotlinTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceKotlinType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceKotlinType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceKotlinType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceKotlinType() returns declaration without source kotlin type`() {
        // given
        val sourceDeclaration: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceKotlinType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceKotlinType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinType1: KoKotlinTypeDeclaration = mockk {
            every { name } returns name1
        }
        val sourceKotlinType2: KoKotlinTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceKotlinType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns sourceKotlinType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceKotlinType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceKotlinType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceFunctionType() returns declaration with source function type`() {
        // given
        val sourceDeclaration: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceFunctionType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceFunctionType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceFunctionType1: KoFunctionTypeDeclaration = mockk {
            every { name } returns name1
        }
        val sourceFunctionType2: KoFunctionTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceFunctionType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceFunctionType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceFunctionType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceFunctionType() returns declaration without source function type`() {
        // given
        val sourceDeclaration: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceFunctionType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceFunctionType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceFunctionType1: KoFunctionTypeDeclaration = mockk {
            every { name } returns name1
        }
        val sourceFunctionType2: KoFunctionTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceFunctionType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns sourceFunctionType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceFunctionType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceFunctionType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSourceExternalType() returns declaration with source external type`() {
        // given
        val sourceDeclaration: KoExternalDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceExternalType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceExternalType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceExternalType1: KoExternalDeclaration = mockk {
            every { name } returns name1
        }
        val sourceExternalType2: KoExternalDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceExternalType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceExternalType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceExternalType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceExternalType() returns declaration without source external type`() {
        // given
        val sourceDeclaration: KoExternalDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceDeclaration
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns null
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceExternalType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceExternalType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceExternalType1: KoExternalDeclaration = mockk {
            every { name } returns name1
        }
        val sourceExternalType2: KoExternalDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceExternalType1
        }
        val declaration2: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns sourceExternalType2
        }
        val declaration3: KoSourceDeclarationProvider = mockk {
            every { sourceExternalType } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceExternalType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }
}
