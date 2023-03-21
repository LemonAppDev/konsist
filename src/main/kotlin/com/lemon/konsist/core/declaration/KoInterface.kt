package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtClass

class KoInterface(private val ktClass: KtClass) : KoComplexDeclaration(ktClass)
