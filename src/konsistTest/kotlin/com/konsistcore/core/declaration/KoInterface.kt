package com.konsistcore.core.declaration

import org.jetbrains.kotlin.psi.KtClass

class KoInterface(private val ktClass: KtClass) : KoComplexDeclaration(ktClass)
