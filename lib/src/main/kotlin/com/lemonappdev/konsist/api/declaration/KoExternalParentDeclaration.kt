package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin external parent declaration - declaration which is not defined in the project (e.g. defined
 * in the library). it may be a class or interface.
 */
interface KoExternalParentDeclaration : KoParentDeclaration
