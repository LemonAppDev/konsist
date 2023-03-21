package com.lemon.konsist.core.declaration

import com.lemon.konsist.ext.isInternal
import com.lemon.konsist.ext.isPrivate
import com.lemon.konsist.ext.isProtected
import com.lemon.konsist.ext.isPublic
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructor(
    private val ktPrimaryConstructor: KtPrimaryConstructor,
    private val parameters: List<KtParameter>,
    private val modifierList: KtModifierList?,
) : KoBaseDeclaration(ktPrimaryConstructor) {
    val isPublic by lazy { modifierList.isPublic() }

    val isPrivate by lazy { modifierList.isPrivate() }

    val isProtected by lazy { modifierList.isProtected() }

    val isInternal by lazy { modifierList.isInternal() }

    fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name
}
