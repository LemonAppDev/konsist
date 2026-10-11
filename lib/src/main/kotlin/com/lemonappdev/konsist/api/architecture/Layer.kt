package com.lemonappdev.konsist.api.architecture

/**
 * Represents an architectural layer within a software system.
 *
 * An architectural layer is defined by a name and a package path pattern that determines which code belongs to it.
 * The package pattern supports wildcards using ".." to include all subpackages at that level.
 *
 * Usage:
 * ```kotlin
 * // Standard layer definitions (wildcards at the end)
 * val domainLayer = Layer("Domain", "com.example.domain..")
 * val presentationLayer = Layer("Presentation", "com.example.presentation..")
 *
 * // Wildcard layer definitions (wildcards at the start and at the end)
 * val specificDomainLayer = Layer("Domain", "..domain..")  // matches any package containing a "domain" segment
 *
 * // Exact package layer definition (no wildcard at the end)
 * val coreLayer = Layer("Core", "com.example.core")  // matches only the "com.example.core" package, not its subpackages
 *
 * // Layer definition with excluded packages
 * val uiLayer = Layer("UI", "com.example.ui..", excludedPackages = setOf("com.example.ui.viewmodel.."))
 * ```
 *
 * Package Pattern Rules:
 * 1. Basic Structure
 *    - Ends with ".." to include subpackages, otherwise matches only the exact package
 *    - Cannot be empty (just "..")
 *    - Can start with ".." for wildcard matching
 *
 * 2. Package Segments
 *    - Must start with a lowercase letter
 *    - Can contain: letters, numbers, underscores
 *
 * 3. Wildcard Usage
 *    - ".." is allowed at start, middle, or end of pattern
 *    - No consecutive dots except for wildcard notation
 *
 * @property name Layer identifier (non-blank)
 * @property rootPackage Package pattern defining layer scope (must follow pattern rules)
 * @property excludedPackages Package patterns excluded from the layer scope (must follow pattern rules)
 * @throws IllegalArgumentException If name is blank, rootPackage or excludedPackages violate pattern rules or
 * excludedPackages contain rootPackage
 */
@Suppress("detekt.ThrowsCount")
data class Layer
    @JvmOverloads
    constructor(
        internal val name: String,
        internal val rootPackage: String,
        internal val excludedPackages: Set<String> = emptySet(),
    ) {
        init {
            require(name.isNotBlank()) { "name is blank" }
            require(rootPackage.isNotBlank()) { "rootPackage is blank" }
            validatePackageDefinition(rootPackage)

            excludedPackages.forEach { excludedPackage ->
                require(excludedPackage.isNotBlank()) { "excludedPackages contain blank package" }
                validatePackageDefinition(excludedPackage)
            }

            require(rootPackage !in excludedPackages) {
                "Invalid excludedPackages definition for layer '$name'. " +
                    "Layer cannot exclude its rootPackage. Current definition: $rootPackage"
            }
        }

        private fun validatePackageDefinition(packageDefinition: String): Unit {
            if (packageDefinition == "..") {
                throw IllegalArgumentException(
                    "Invalid rootPackage definition for layer '$name'. " +
                        "Package name cannot be empty. Current definition: $packageDefinition",
                )
            }

            // Check if ends with exactly '..' or with package segment
            if (!endsWithExactlyTwoDots(packageDefinition) && packageDefinition.endsWith(".")) {
                throw IllegalArgumentException(
                    buildPackageErrorMessage(packageDefinition),
                )
            }

            // Remove the ending '..' for further validation
            val packageWithoutEndingDots = packageDefinition.removeSuffix("..")

            // Empty package (just ..) is not valid
            if (packageWithoutEndingDots.isEmpty()) {
                throw IllegalArgumentException(
                    "Invalid package definition for layer '$name'. " +
                        "Package name cannot be empty. Current definition: $packageDefinition",
                )
            }

            // Special handling for packages starting with '..'
            val effectivePackage =
                if (packageWithoutEndingDots.startsWith("..")) {
                    packageWithoutEndingDots.substring(2)
                } else {
                    packageWithoutEndingDots
                }

            // Check for starting dot (but not ..)
            if (effectivePackage.startsWith(".") && !packageWithoutEndingDots.startsWith("..")) {
                throw IllegalArgumentException(
                    "Invalid package definition for layer '$name'. " +
                        "Package cannot start with a dot. Current definition: $packageDefinition",
                )
            }

            // Split and validate segments
            val segments =
                effectivePackage
                    .split(".")
                    .filter { it.isNotEmpty() }

            // Validate each segment
            segments.forEachIndexed { index, segment ->
                if (!segment.matches(REGEX_VALID_PACKAGE_SEGMENT)) {
                    throw IllegalArgumentException(
                        "Invalid package definition for layer '$name'. " +
                            "Invalid package segment '$segment' at position ${index + 1}. " +
                            "Package segments must start with a lowercase letter and contain only " +
                            "letters, numbers, or underscores. Current definition: $packageDefinition",
                    )
                }
            }
        }

        private fun endsWithExactlyTwoDots(packageDefinition: String): Boolean {
            val lastIndex = packageDefinition.length - 1
            return lastIndex >= 1 &&
                packageDefinition[lastIndex] == '.' &&
                packageDefinition[lastIndex - 1] == '.' &&
                (lastIndex < 2 || packageDefinition[lastIndex - 2] != '.')
        }

        private fun buildPackageErrorMessage(packageDefinition: String): String =
            "Invalid package definition for layer '$name'. To include subpackages, " +
                "the definition must end with '..', to include only the package, the definition must end with " +
                "a package segment. Current definition: $packageDefinition"

        private companion object {
            private val REGEX_VALID_PACKAGE_SEGMENT = Regex("^[a-z][a-zA-Z0-9_]*$")
        }
    }
