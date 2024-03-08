package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoLocationProvider
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

internal interface KoLocationProviderCore :
    KoLocationProvider,
    KoTextProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val location: String
        get() {
            val lineAndColumn =
                psiElement
                    .getTextWithLocation()
                    .substringAfterLast("' at (")
                    .substringBefore(") in")
                    .split(",")
                    .toMutableList()
                    .filterNot { it.isBlank() }

            val line = lineAndColumn[0]
            val column = lineAndColumn[1]
            return "$path:$line:$column"
        }

    override val locationWithText: String
        get() = "Location: $location \nDeclaration:\n$text"
}
