package com.konsistcore.core.declaration

import com.konsistcore.ext.isInternal
import com.konsistcore.ext.isPrivate
import com.konsistcore.ext.isProtected
import com.konsistcore.ext.isPublic
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

class KoSecondaryConstructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
) : KoBaseDeclaration(ktSecondaryConstructor) {
    val isPublic by lazy { ktSecondaryConstructor.modifierList.isPublic() }

    val isPrivate by lazy { ktSecondaryConstructor.modifierList.isPrivate() }

    val isProtected by lazy { ktSecondaryConstructor.modifierList.isProtected() }

    val isInternal by lazy { ktSecondaryConstructor.modifierList.isInternal() }
}
