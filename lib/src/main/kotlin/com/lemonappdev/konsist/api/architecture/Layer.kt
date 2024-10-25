package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param rootPackage The root package that defines this layer. The layer contains all classes from this package and its
 * subpackages. The root package definition must end with two dots, e.g., `com.app.domain..`.
 *
 * @throws KoPreconditionFailedException if:
 * - The [rootPackage] starts with a single dot.
 * - The [rootPackage] must end with '..' (when including subpackages).
 * - The [rootPackage] contains more than two consecutive dots.
 */
data class Layer(
    internal val name: String,
    internal val rootPackage: String,
) {
    init {
        require(name.isNotBlank()) { "name is blank" }
        require(rootPackage.isNotBlank()) { "rootPackage is blank" }
        requirePackageNameEndsWithTwoDots {
            "Invalid rootPackage definition for layer '$name'. " +
                    "To include subpackages, the definition must end with '..'. Current definition: $rootPackage"
        }
        requirePackageNameHasNoSingleDotAtTheBeginning {
            "Invalid rootPackage definition for layer '$name'. " +
                    "Package names cannot start with a single dot. Current definition: $rootPackage"
        }
        requirePackageNameHasNoMultipleDots {
            "Invalid rootPackage definition for layer '$name'. " +
                    "Package names cannot contain more than two consecutive dots. Current definition: $rootPackage"
        }
    }

    private fun requirePackageNameHasNoSingleDotAtTheBeginning(lazyMessage: () -> String) {
        if (rootPackage.matches(REGEX_PACKAGE_NAME_WITH_SINGLE_DOT_AT_THE_BEGINNING)) {
            throw IllegalArgumentException(lazyMessage())
        }
    }

    private fun requirePackageNameEndsWithTwoDots(lazyMessage: () -> String) {
        if (!rootPackage.matches(REGEX_PACKAGE_NAME_ENDS_TWO_DOTS)) {
            throw IllegalArgumentException(lazyMessage())
        }
    }

    private fun requirePackageNameHasNoMultipleDots(lazyMessage: () -> String) {
        if (rootPackage.matches(REGEX_PACKAGE_NAME_WITH_MULTIPLE_DOTS)) {
            throw IllegalArgumentException(lazyMessage())
        }
    }

    companion object {
        /**
         * Regex to match package names ending with exactly two dots.
         * This pattern ensures the package name has a valid format before the two dots.
         *
         * Pattern explanation:
         * .*         = Matches zero or more of any character (allows for any valid package name structure)
         * \\w+       = One or more word characters (alphanumeric or underscore)
         * \\.{2}     = Exactly two dots (escaped)
         * $          = End of string
         *
         * Examples of matches (valid package names):
         * - "com.example.."
         * - "example.."
         * - "com.example.test.."
         * - "somepackage.."
         *
         * Won't match:
         * - "com.example"
         * - "com.example."
         * - "com.example..."
         * - "com..example"
         * - ".."
         *
         * Note: This regex ensures that there is at least one word character
         * before the two dots at the end, preventing matches like ".."
         */
        internal val REGEX_PACKAGE_NAME_ENDS_TWO_DOTS = Regex(".*\\w+\\.{2}$")

        /**
         * Regex to match invalid package names that start with a single dot (optionally preceded by whitespace).
         * This regex is used to detect invalid package names to throw appropriate exceptions.
         *
         * Pattern explanation:
         * ^          = Matches beginning of string
         * \\s*       = Zero or more whitespace characters
         * \\.        = Single dot (escaped)
         * (?:        = Start of non-capturing group
         *   [^.]     = Any character that is not a dot
         *   |        = OR
         *   \\..*    = A dot followed by any characters
         * )*         = End of group, repeat zero or more times
         * $          = End of string
         *
         * Examples of matches (invalid package names):
         * - ".example"
         * - " .example"
         * - ".com.example"
         * - ".com.example.."
         * - "\t.example"
         * - "\n.example"
         *
         * Won't match (valid cases):
         * - "example"
         * - "..example"
         * - "com.example"
         * - " ..example"
         */
        internal val REGEX_PACKAGE_NAME_WITH_SINGLE_DOT_AT_THE_BEGINNING = Regex("^\\s*\\.[^.].*|^\\s*\\.\\..*$")

        /**
         * Regex to match invalid package names containing multiple instances of consecutive dots.
         *
         * The regex matches:
         * - Strings containing multiple instances of ".." anywhere in the string
         * - Strings containing three or more consecutive dots
         *
         * Pattern explanation:
         * .*          = Matches any character (except newline) zero or more times
         * \\.{2,}     = Matches two or more consecutive dots
         * .*          = Matches any character (except newline) zero or more times
         * |           = OR
         * .*          = Matches any character (except newline) zero or more times
         * \\.{3,}     = Matches three or more consecutive dots
         * .*          = Matches any character (except newline) zero or more times
         *
         * Examples of matches (invalid package names):
         * - "com..example..test"
         * - "com..example.test.."
         * - "com...example"
         * - "com..test..example.."
         *
         * Won't match:
         * - "com.example.test"
         * - "com.example.test.."
         * - "com.example"
         */
        internal val REGEX_PACKAGE_NAME_WITH_MULTIPLE_DOTS = Regex(".*\\.{2,}.*\\.{2,}.*|.*\\.{3,}.*")
    }
}
