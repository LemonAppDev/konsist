package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ListExtTest {
    @Test
    fun `indexOfFirstInstance() returns -1 when none declaration is instance of KoBaseDeclaration`() {
        // given
        val declaration1: KoNameProvider = mockk()
        val declaration2: KoNameProvider = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfFirstInstance<KoBaseDeclaration>()

        // then
        sut shouldBeEqualTo -1
    }

    @Test
    fun `indexOfFirstInstance() returns index of first declaration which is instance of KoBaseDeclaration`() {
        // given
        val declaration1: KoInterfaceDeclaration = mockk()
        val declaration2: KoInterfaceDeclaration = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfFirstInstance<KoBaseDeclaration>()

        // then
        sut shouldBeEqualTo 0
    }

    @Test
    fun `indexOfFirstInstance() returns index of first declaration which is instance of KoInterfaceDeclaration`() {
        // given
        val declaration1: KoInterfaceDeclaration = mockk()
        val declaration2: KoInterfaceDeclaration = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfFirstInstance<KoInterfaceDeclaration>()

        // then
        sut shouldBeEqualTo 0
    }

    @Test
    fun `indexOfLastInstance() returns -1 when none declaration is instance of KoBaseDeclaration`() {
        // given
        val declaration1: KoNameProvider = mockk()
        val declaration2: KoNameProvider = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfLastInstance<KoBaseDeclaration>()

        // then
        sut shouldBeEqualTo -1
    }

    @Test
    fun `indexOfLastInstance() returns index of last declaration which is instance of KoBaseDeclaration`() {
        // given
        val declaration1: KoInterfaceDeclaration = mockk()
        val declaration2: KoInterfaceDeclaration = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfLastInstance<KoBaseDeclaration>()

        // then
        sut shouldBeEqualTo 1
    }

    @Test
    fun `indexOfLastInstance() returns index of last declaration which is instance of KoInterfaceDeclaration`() {
        // given
        val declaration1: KoInterfaceDeclaration = mockk()
        val declaration2: KoInterfaceDeclaration = mockk()

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.indexOfLastInstance<KoInterfaceDeclaration>()

        // then
        sut shouldBeEqualTo 1
    }
}
