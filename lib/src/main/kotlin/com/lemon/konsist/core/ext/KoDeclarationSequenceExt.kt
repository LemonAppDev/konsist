package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoDeclaration

fun Sequence<KoDeclaration>.withPublicModifier() = filter { it.hasPublicModifier() }

fun Sequence<KoDeclaration>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

fun Sequence<KoDeclaration>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

fun Sequence<KoDeclaration>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

fun Sequence<KoDeclaration>.withPrivateModifier() = filter { it.hasPrivateModifier() }

fun Sequence<KoDeclaration>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

fun Sequence<KoDeclaration>.withProtectedModifier() = filter { it.hasProtectedModifier() }

fun Sequence<KoDeclaration>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

fun Sequence<KoDeclaration>.withInternalModifier() = filter { it.hasInternalModifier() }

fun Sequence<KoDeclaration>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

fun Sequence<KoDeclaration>.withTopLevel() = filter { it.isTopLevel() }

fun Sequence<KoDeclaration>.withoutTopLevel() = filterNot { it.isTopLevel() }