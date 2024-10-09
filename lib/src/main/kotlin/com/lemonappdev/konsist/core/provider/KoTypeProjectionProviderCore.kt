package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeProjectionProvider
import com.lemonappdev.konsist.core.declaration.type.KoStarProjectionDeclarationCore.ktTypeProjection
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeProjection

internal interface KoTypeProjectionProviderCore :
    KoTypeProjectionProvider,
    KoBaseProviderCore
