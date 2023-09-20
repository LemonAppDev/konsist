package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc return tag.
 */
interface KoKDocReturnTagProvider : KoBaseProvider {
    /**
     * The `@return` tag.
     */
    val returnTag: KoKDocTagDeclaration?
}
