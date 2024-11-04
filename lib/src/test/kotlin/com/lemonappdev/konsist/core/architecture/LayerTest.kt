package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerTest {
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
    fun `create Layer with package not ending in double dots should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.example") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Package must end with '..'. Current definition: com.example"
    }

    @Test
    fun `create Layer with package starting with dot should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = ".com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Package cannot start with a dot. Current definition: .com.example.."
    }

    @Test
    fun `create Layer with only double dots should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Package name cannot be empty. Current definition: .."
    }

    @Test
    fun `create Layer with uppercase package segment should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "Com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Invalid package segment 'Com' at position 1. " +
            "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
            "Current definition: Com.example.."
    }

    @Test
    fun `create Layer with segment starting with number should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.1example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Invalid package segment '1example' at position 2. " +
            "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
            "Current definition: com.1example.."
    }

    @Test
    fun `create Layer with special characters in segment should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.example$#..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Invalid package segment 'example$#' at position 2. " +
            "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
            "Current definition: com.example$#.."
    }

    @Test
    fun `create Layer with consecutive dots in middle should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com..example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid rootPackage definition for layer 'name'. Invalid package segment '' at position 2. " +
            "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
            "Current definition: com..example.."
    }

    @Test
    fun `create Layer with valid package name should not throw exception`() {
        // given
        val validPackages =
            listOf(
                "com.example..",
                "example..",
                "com.example.test..",
                "com.example1.test2..",
                "com.example_test..",
            )

        // then
        validPackages.forEach { packageName ->
            Layer(name = "name", rootPackage = packageName)
        }
    }
}