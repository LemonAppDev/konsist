package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

fun Sequence<KoInterfaceDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoInterfaceDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }
