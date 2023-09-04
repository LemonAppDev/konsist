package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.core.declaration.KoArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoEnumConstantDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoArgumentProviderCore : KoArgumentProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    override val numArguments: Int
        get() = arguments.size

    override fun hasArguments(): Boolean = arguments.isNotEmpty()
}
