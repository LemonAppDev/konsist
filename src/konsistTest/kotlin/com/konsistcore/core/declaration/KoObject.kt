package com.konsistcore.core.declaration

import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoObject(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclaration(ktObjectDeclaration)
