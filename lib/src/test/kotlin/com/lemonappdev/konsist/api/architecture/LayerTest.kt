package com.lemonappdev.konsist.api.architecture

import org.amshove.kluent.shouldNotThrow
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
            "Invalid package definition for layer 'name'. To include subpackages, the definition must end with '..'. " +
            "Current definition: com.example"
    }

    @Test
    fun `create Layer with package starting with dot should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = ".com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid package definition for layer 'name'. Package cannot start with a dot. Current definition: .com.example.."
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
    fun `create Layer with Pascal case package segment should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "Com.example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid package definition for layer 'name'. Invalid package segment 'Com' at position 1. Package segments must start with " +
            "a lowercase letter and contain only lowercase letters, numbers, or underscores. Current definition: Com.example.."
    }

    @Test
    fun `create Layer with segment starting with number should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.1example..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid package definition for layer 'name'. Invalid package segment '1example' at position 2. Package segments " +
            "must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
            "Current definition: com.1example.."
    }

    @Test
    fun `create Layer with special characters in segment should throw exception`() {
        // when
        val func = { Layer(name = "name", rootPackage = "com.example$#..") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "Invalid package definition for layer 'name'. Invalid package segment 'example\$#' at position 2. Package segments " +
            "must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. Current definition: " +
            "com.example\$#.."
    }

    @Test
    fun `create Layer with package starting with two dots should not throw exception`() {
        // when
        val func = {
            Layer("Domain", "..domain..")
        }

        // then
        func shouldNotThrow IllegalArgumentException::class
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
                "com.exampleTest..",
            )

        // then
        validPackages.forEach { packageName ->
            Layer(name = "name", rootPackage = packageName)
        }
    }
}
