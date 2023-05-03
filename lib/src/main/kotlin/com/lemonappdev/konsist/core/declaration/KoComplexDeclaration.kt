package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider

interface KoComplexDeclaration :
    KoDeclaration,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {
    fun representsType(name: String): Boolean
}
