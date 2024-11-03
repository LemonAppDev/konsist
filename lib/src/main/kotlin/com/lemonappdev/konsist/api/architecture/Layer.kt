package com.lemonappdev.konsist.api.architecture

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param rootPackage The root package that defines this layer. Must follow Java package naming conventions and end with '..'
 * Requirements:
 * - Must end with '..' to indicate inclusion of subpackages
 * - Package segments must be lowercase alphanumeric (first character must be a letter)
 * - Segments must be separated by single dots
 * - Cannot start with a dot
 * - Must be a valid Java package name before the ending '..'
 *
 * @throws IllegalArgumentException if the name is blank or package name doesn't meet the requirements
 */
data class Layer(
    internal val name: String,
    internal val rootPackage: String,
) {
    init {
        require(name.isNotBlank()) { "name is blank" }
        require(rootPackage.isNotBlank()) { "rootPackage is blank" }

        validatePackageName()
    }

    private fun validatePackageName() {
        // Check ends with ..
        require(rootPackage.endsWith("..")) {
            "Invalid rootPackage definition for layer '$name'. " +
                    "Package must end with '..'. Current definition: $rootPackage"
        }

        val packageWithoutDoubleDot = rootPackage.removeSuffix("..")

        // Empty package (just ..) is not valid
        require(packageWithoutDoubleDot.isNotEmpty()) {
            "Invalid rootPackage definition for layer '$name'. " +
                    "Package name cannot be empty. Current definition: $rootPackage"
        }

        // Check for starting dot
        require(!packageWithoutDoubleDot.startsWith(".")) {
            "Invalid rootPackage definition for layer '$name'. " +
                    "Package cannot start with a dot. Current definition: $rootPackage"
        }

        // Validate each package segment
        val segments = packageWithoutDoubleDot.split(".")
        segments.forEachIndexed { index, segment ->
            require(segment.matches(REGEX_VALID_PACKAGE_SEGMENT)) {
                "Invalid rootPackage definition for layer '$name'. " +
                        "Invalid package segment '${segment}' at position ${index + 1}. " +
                        "Package segments must start with a lowercase letter and contain only lowercase letters, numbers, or underscores. " +
                        "Current definition: $rootPackage"
            }
        }
    }

    companion object {
        /**
         * Regex for valid Java package segment.
         * - Must start with a lowercase letter
         * - Can contain lowercase letters, numbers, and underscores
         */
        private val REGEX_VALID_PACKAGE_SEGMENT = Regex("^[a-z][a-z0-9_]*$")
    }
}
