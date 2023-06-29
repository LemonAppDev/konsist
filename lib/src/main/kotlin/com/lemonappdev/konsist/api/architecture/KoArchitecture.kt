package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.KoArchitectureAssertionImpl

/**
 * A singleton object representing the `KoArchitecture` that implements the [KoArchitectureAssertion] interface.
 *
 * This object provides a way to access the functionalities defined in the [KoArchitectureAssertion] interface.
 * It serves as the entry point for working with architecture assertions.
 *
 * @see KoArchitectureAssertion
 */
object KoArchitecture : KoArchitectureAssertion by KoArchitectureAssertionImpl()
