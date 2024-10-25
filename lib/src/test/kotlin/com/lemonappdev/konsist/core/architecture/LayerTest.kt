package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerTest {
    private val sut = Layer(
        name = "name",
        rootPackage = "com.example..",
    )

    @Test
    fun `create Layer with valid parameters should succeed`() {
        // given
        val name = "name"
        val rootPackage = "com.example.."

        // when
        val result = Layer(
            name = name,
            rootPackage = rootPackage,
        )

        // Then
        name shouldBeEqualTo result.name
        rootPackage shouldBeEqualTo result.rootPackage
    }

    @Test
    fun `create Layer with blank name should throw exception`() {
        // when
        val func = { Layer(name = " ", rootPackage = "com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "name is blank"
    }

    @Test
    fun `create Layer with blank rootPackage should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = " ") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "rootPackage is blank"
    }

    @Test
    fun `create Layer with rootPackage starting with dot should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = ".com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
                "Invalid rootPackage definition for layer 'name'. Package names cannot start with a single dot. " +
                "Current definition: .com.example.."
    }

    @Test
    fun `create Layer with rootPackage not ending with two dots should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.example") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
                "Invalid rootPackage definition for layer 'name'. To include subpackages, the definition must " +
                "end with '..'. Current definition: com.example"
    }

    @Test
    fun `create Layer with rootPackage containing more than two consecutive dots should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com..example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
                "Invalid rootPackage definition for layer 'name'. Package names cannot contain more than two " +
                "consecutive dots. Current definition: com..example.."
    }
}
