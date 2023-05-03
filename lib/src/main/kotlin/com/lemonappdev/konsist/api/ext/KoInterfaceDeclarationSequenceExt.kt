package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl

fun Sequence<KoInterfaceDeclarationImpl>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclarationImpl>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoInterfaceDeclarationImpl>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoInterfaceDeclarationImpl>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }
