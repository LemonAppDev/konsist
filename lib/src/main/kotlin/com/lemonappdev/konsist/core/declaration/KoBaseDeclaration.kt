package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import java.io.File

/**
 * Base declaration
 */
open class KoBaseDeclaration(private val ktElement: KtElement): KoPsiDeclaration(ktElement) {
    /**
     * KoFile containing the declaration
     */
    val containingFile by lazy { KoFile.getInstance(ktElement.containingKtFile) }
}
