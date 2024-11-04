package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerTest {
    @Test
    fun `throws an exception when layer name is blank`() {
        // given
        val sut = { Layer("", "package") }

        // then
        sut shouldThrow IllegalArgumentException::class withMessage
                "name is blank"
    }

    @Test
    fun `throws an exception when layer rootPackage is blank`() {
        // given
        val sut = { Layer("name", "") }

        // then
        sut shouldThrow IllegalArgumentException::class withMessage
                "rootPackage is blank"
    }

    @Test
    fun `throws an exception when layer is defined by package without two dots at the end`() {
        // given
        val sut = { Layer("Domain", "package") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. To include subpackages, " +
                "the definition must end with '..'. Current definition: package"
    }

    @Test
    fun `throws an exception when layer is defined by package without two dots at the end with dots as wildcard`() {
        // given
        val sut = { Layer("Domain", "package..feature") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. To include subpackages, " +
                "the definition must end with '..'. Current definition: package..feature"
    }

    @Test
    fun `throws an exception when layer is defined by package with three dots at the end`() {
        // given
        val sut = { Layer("Domain", "package...") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. To include subpackages, " +
                "the definition must end with '..'. Current definition: package..."
    }

    @Test
    fun `throws an exception when layer is defined by package with dots without two dots at the end`() {
        // given
        val sut = { Layer("Domain", "first.package") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. To include subpackages, " +
                "the definition must end with '..'. Current definition: first.package"
    }

    @Test
    fun `throws an exception when layer is defined by package containing dots with more than two dots at the end`() {
        // given
        val sut = { Layer("Domain", "first.second..third_p.package....") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. To include subpackages, " +
                "the definition must end with '..'. Current definition: first.second..third_p.package...."
    }

    @Test
    fun `throws an exception when package is just dots`() {
        // given
        val sut = { Layer("Domain", "..") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. Package name cannot be empty. Current definition: .."
    }

    @Test
    fun `throws an exception when package starts with dot`() {
        // given
        val sut = { Layer("Domain", ".package..") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. Package cannot start with a dot. Current definition: .package.."
    }

    @Test
    fun `throws an exception when package segment starts with number`() {
        // given
        val sut = { Layer("Domain", "1package..") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. Invalid package segment '1package' at position 1. " +
                "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
                "Current definition: 1package.."
    }

    @Test
    fun `throws an exception when package segment contains uppercase letters`() {
        // given
        val sut = { Layer("Domain", "Package..") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
                "Invalid package definition for layer 'Domain'. Invalid package segment 'Package' at position 1. " +
                "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
                "Current definition: Package.."
    }

    @Test
    fun `do not throw an exception when the package ends with two dots`() {
        // given
        val sut = { Layer("Domain", "first.second.package..") }

        // then
        sut shouldNotThrow KoPreconditionFailedException::class
    }

    @Test
    fun `do not throw an exception when the package ends with two dots and there are another two dots used as wildcard`() {
        // given
        val sut = { Layer("Domain", "first.second..package..") }

        // then
        sut shouldNotThrow KoPreconditionFailedException::class
    }

    @Test
    fun `do not throw an exception when package segment contains underscore`() {
        // given
        val sut = { Layer("Domain", "first_package..") }

        // then
        sut shouldNotThrow KoPreconditionFailedException::class
    }

    @Test
    fun `do not throw an exception when package segment contains numbers after letters`() {
        // given
        val sut = { Layer("Domain", "package123..") }

        // then
        sut shouldNotThrow KoPreconditionFailedException::class
    }
}
