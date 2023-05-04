package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

/**
 * Sequence containing all declarations that have 'actual' modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

/**
 * Sequence containing all declarations that don't have 'actual' modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all declarations that have 'expect' modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that don't have 'expect' modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }
