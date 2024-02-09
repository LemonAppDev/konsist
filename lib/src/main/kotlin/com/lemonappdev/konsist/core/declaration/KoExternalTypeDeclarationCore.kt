package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoExternalParentCache
import com.lemonappdev.konsist.core.cache.KoExternalTypeCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtUserType

internal class KoExternalTypeDeclarationCore(name: String, private val ktUserType: KtUserType) :
    KoExternalTypeDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val name: String by lazy { ktUserType.text }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoExternalTypeCache = KoExternalTypeCache

        internal fun getInstance(
            name: String,
            ktUserType: KtUserType,
        ): KoExternalTypeDeclaration =
            cache.getOrCreateInstance(name, ktUserType) {
                KoExternalTypeDeclarationCore(
                    name,
                    ktUserType,
                )
            }
    }
}
