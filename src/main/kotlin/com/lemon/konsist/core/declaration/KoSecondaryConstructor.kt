package com.lemon.konsist.core.declaration

import com.lemon.konsist.ext.isInternal
import com.lemon.konsist.ext.isPrivate
import com.lemon.konsist.ext.isProtected
import com.lemon.konsist.ext.isPublic
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

class KoSecondaryConstructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
) : KoBaseDeclaration(ktSecondaryConstructor) {
    val isPublic by lazy { ktSecondaryConstructor.modifierList.isPublic() }

    val isPrivate by lazy { ktSecondaryConstructor.modifierList.isPrivate() }

    val isProtected by lazy { ktSecondaryConstructor.modifierList.isProtected() }

    val isInternal by lazy { ktSecondaryConstructor.modifierList.isInternal() }

    val name by lazy { ktSecondaryConstructor.name }
}
