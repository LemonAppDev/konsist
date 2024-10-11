package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoStarProjectionProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoSourceDeclarationCore :
    KoSourceDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoTypeDeclarationProviderCore,
    KoTypeProviderCore,
    KoStarProjectionProviderCore {
    /*
    Remove in version 0.18.0
    */
    override val ktTypeReference: KtTypeReference?
        get() = null

    /*
    Remove in version 0.18.0
    */
    override val ktNameReferenceExpression: KtNameReferenceExpression?
        get() = null

    /*
    Remove in version 0.18.0
    */
    override val ktTypeProjection: KtTypeProjection?
        get() = null
    }
