package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtProperty

class KoProperty(private val ktProperty: KtProperty) : KoDeclaration(ktProperty)
