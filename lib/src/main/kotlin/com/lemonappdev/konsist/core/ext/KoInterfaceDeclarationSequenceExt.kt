package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclaration

fun Sequence<KoInterfaceDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoInterfaceDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }
