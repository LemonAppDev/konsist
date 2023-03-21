package com.konsistcore.core.declaration

import com.konsistcore.ext.isInternal
import com.konsistcore.ext.isPrivate
import com.konsistcore.ext.isProtected
import com.konsistcore.ext.isPublic
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
